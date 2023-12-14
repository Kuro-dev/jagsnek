package org.jag.webserver.dto;

import org.jag.snek.logic.Coordinate;
import org.jag.snek.logic.Snack;
import org.jag.snek.logic.SnakeGame;

import java.util.List;

public class SnakeGameDTO {
    private final String id;
    private final Coordinate snakeHead;

    private final List<Coordinate> snakeTails;

    private final List<Snack> snacks;

    private final int width;
    private final int height;

    private SnakeGameDTO(String id, Coordinate snakeHead, List<Coordinate> snakeTails, List<Snack> snacks, int width, int height) {
        this.id = id;
        this.snakeHead = snakeHead;
        this.snakeTails = snakeTails;
        this.snacks = snacks;
        this.width = width;
        this.height = height;
    }

    public static SnakeGameDTO ofGame(String id, SnakeGame game) {
        Coordinate snakeHead = game.getSnakeHead();
        List<Coordinate> snakeTails = game.getSnakeTails();
        List<Snack> snacks = game.getSnacks();
        int width = game.getWidth();
        int height = game.getheight();
        return new SnakeGameDTO(id, snakeHead, snakeTails, snacks, width, height);
    }

    public String getId() {
        return id;
    }

    public Coordinate getSnakeHead() {
        return snakeHead;
    }

    public List<Coordinate> getSnakeTails() {
        return snakeTails;
    }

    public List<Snack> getSnacks() {
        return snacks;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
//map the snake game data
}
