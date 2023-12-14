package org.jag.snek.logic;

import java.util.List;

public class SnakeGame implements Runnable {

    //TODO (things to keep track of): 1. score, element of the snake, directions, generating food, head of the snake
    // the snake (head and element) directions
    // game logic (score, generating food, the playing field)

    private final PlayField playfield;

    public SnakeGame(PlayField playfield) {
        this.playfield = playfield;
    }

    public static SnakeGame newDefaultGame() {
        Snake snake = new Snake(new Coordinate(100, 100));
        snake.initialise();
        PlayField field = new PlayField(200, 200, snake);
        return new SnakeGame(field);
    }

    public void changeDirection(Direction direction) {
        playfield.getSnek().setDirection(direction);
    }

    public boolean isSnakeAlive() {
        return playfield.getSnek().isAlive();
    }

    @Override
    public void run() {
        while (playfield.getSnek().isAlive()) {
            playfield.completeCycle();
        }
        System.out.println("OH NO, you died!");
    }

    public void completeCycle() {
        playfield.completeCycle();
    }

    public Coordinate getSnakeHead() {
        return playfield.getSnek().getPosition();
    }

    public List<Coordinate> getSnakeTails() {
        return playfield.getSnek().getTails().stream().map(SnakeTail::getPosition).toList();
    }

    public List<Coordinate> getSnacks() {
        return playfield.getSnacks().stream().map(Snack::getPosition).toList();
    }

    public int getWidth() {
        return playfield.getWidth();
    }

    public int getheight() {
        return playfield.getHeight();
    }


    //TODO: field
}
