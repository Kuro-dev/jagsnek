package org.jag.webserver;

import org.jag.snek.logic.Direction;
import org.jag.snek.logic.SnakeGame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * /snake/start
 * /snake/update
 * /snake/direction
 **/
@RestController("/api/snake")
public class SnakeGameController {
    private final Map<String, SnakeGame> games = new HashMap<>();


    @GetMapping("/start")
    public ResponseEntity<SnakeGameDTO> startGame() {
        String id = UUID.randomUUID().toString();
        SnakeGame game = SnakeGame.newDefaultGame();
        games.put(id, game);

        SnakeGameDTO snakeGameDTO = SnakeGameDTO.ofGame(id, game);

        return ResponseEntity.ok(snakeGameDTO);
    }

    @PostMapping("/update")
    public ResponseEntity<SnakeGameDTO> updateGame(@RequestParam String id) {
        SnakeGame game = games.get(id);

        if (Objects.isNull(game)) {
            return ResponseEntity.notFound().build();
        }

        game.completeCycle();
        SnakeGameDTO snakeGameDTO = SnakeGameDTO.ofGame(id, game);
        return ResponseEntity.ok(snakeGameDTO); //status code 200 "ok"

    }

    @PostMapping("/direction")
    public ResponseEntity<SnakeGameDTO> changeDirection(@RequestParam String id, @RequestParam Direction direction) {
        SnakeGame game = games.get(id);

        if (Objects.isNull(game)) {
            return ResponseEntity.notFound().build();//status code 404 "not found"
        }

        if (Objects.isNull(direction)){
            return ResponseEntity.badRequest().build(); //400
        }

        game.changeDirection(direction);

        SnakeGameDTO snakeGameDTO = SnakeGameDTO.ofGame(id, game);
        return ResponseEntity.ok(snakeGameDTO);


    }
}
