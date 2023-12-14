package org.jag.webserver;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import org.jag.snek.logic.Direction;
import org.jag.snek.logic.SnakeGame;
import org.jag.snek.logic.SnakeGameBuilder;
import org.jag.webserver.dto.SnakeGameDTO;
import org.jag.webserver.dto.StartSnakeGameRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * /snake/start
 * /snake/update
 * /snake/direction
 **/
@RestController("/api/snake")
public class SnakeGameController {
    private static final Logger logger = LoggerFactory.getLogger(SnakeGameController.class);
    private final Map<String, SnakeGameWrapper> games = new HashMap<>();

    public SnakeGameController() {

        // Clean the caches every 5 minutes
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "Cleanup thread");
            t.setDaemon(true);
            return t;
        });
        executorService.scheduleAtFixedRate(() -> {
            games.entrySet().removeIf(entry -> {
                if (entry.getValue().isStale()) {
                    logger.info("Removing game: {}", entry.getKey());
                    return true;
                }
                return false;
            });
        }, 0, 5, TimeUnit.MINUTES);
    }

    @Operation(description = "Starts the snake game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully started the game"),
    })
    @GetMapping(value = "/start", produces = "application/json")
    public ResponseEntity<SnakeGameDTO> startGame(@RequestBody(required = false) StartSnakeGameRequest options) {
        String id = UUID.randomUUID().toString();
        SnakeGame game;
        if (options == null) {
            game = new SnakeGameBuilder().build();
        } else {
            game = options.toGame();
        }
        logger.info("Starting new game with id: {}", id);
        SnakeGameWrapper wrapper = new SnakeGameWrapper(id, game);
        //Remove game when finished, to free up memory
        wrapper.setOnComplete(() -> games.remove(id));
        games.put(id, wrapper);
        return ResponseEntity.ok(wrapper.getGameDTO());
    }

    @Operation(description = "Advances the game by one frame")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully advanced the game"),
            @ApiResponse(responseCode = "404", description = "When the game-id does not exist"),
    })
    @PostMapping(value = "/update", produces = "application/json")
    public ResponseEntity<SnakeGameDTO> updateGame(@RequestParam @NotNull String id) {
        SnakeGameWrapper game = games.get(id);

        if (Objects.isNull(game)) {
            return ResponseEntity.notFound().build();
        }

        game.update();
        return ResponseEntity.ok(game.getGameDTO()); //status code 200 "ok"

    }

    @Operation(description = "Changes the direction of the snake. Does *not* advance the game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Changed the direction of the Snake"),
            @ApiResponse(responseCode = "404", description = "When the game-id does not exist"),
            @ApiResponse(responseCode = "400", description = "When no valid direction is given"),
    })
    @PostMapping(value = "/direction", produces = "application/json")
    public ResponseEntity<SnakeGameDTO> changeDirection(@RequestParam @NotNull String id, @RequestParam @NotNull Direction direction) {
        SnakeGameWrapper game = games.get(id);

        if (Objects.isNull(game)) {
            return ResponseEntity.notFound().build();//status code 404 "not found"
        }

        if (Objects.isNull(direction)) {
            return ResponseEntity.badRequest().build(); //400
        }

        game.setDirection(direction);

        return ResponseEntity.ok(game.getGameDTO());
    }
}
