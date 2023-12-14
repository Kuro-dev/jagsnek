package org.jag.webserver;

import org.jag.snek.logic.Direction;
import org.jag.snek.logic.SnakeGame;
import org.jag.webserver.dto.SnakeGameDTO;

import java.util.concurrent.TimeUnit;

public class SnakeGameWrapper {
    private final String id;
    private final SnakeGame game;
    private final long createdMillis;
    private long lastInteractionMillis;
    private Runnable onComplete;

    public SnakeGameWrapper(String id, SnakeGame game) {
        this.id = id;
        this.game = game;
        createdMillis = System.currentTimeMillis();
    }

    public void update() {
        game.completeCycle();
        lastInteractionMillis = System.currentTimeMillis();
        if (!game.isSnakeAlive()) {
            onComplete.run();
        }
    }

    public void setDirection(Direction dir) {
        game.changeDirection(dir);
        lastInteractionMillis = System.currentTimeMillis();
    }

    public SnakeGameDTO getGameDTO() {
        return SnakeGameDTO.ofGame(id, game);
    }

    public void setOnComplete(Runnable onComplete) {
        this.onComplete = onComplete;
    }

    /**
     * @return True if the game has not been interacted with for 5 or more minutes
     */
    public boolean isStale() {
        return TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - lastInteractionMillis) >= 5;
    }
}

