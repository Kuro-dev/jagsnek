package org.jag.snek.logic;

public class SnakeGame implements Runnable {

    //TODO (things to keep track of): 1. score, element of the snake, directions, generating food, head of the snake
    // the snake (head and element) directions
    // game logic (score, generating food, the playing field)

    private final PlayField playfield;

    public SnakeGame(PlayField playfield) {
        this.playfield = playfield;
    }

    @Override
    public void run() {
        while (playfield.getSnek().isAlive()) {
           playfield.completeCycle();
        }
       System.out.println("OH NO, you died!");
    }


    //TODO: field
}
