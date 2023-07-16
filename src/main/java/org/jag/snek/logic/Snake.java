package org.jag.snek.logic;

import java.util.ArrayList;
import java.util.List;

public class Snake extends Tile {
    private final List<SnakeTail> tails = new ArrayList<>();
    private int size = 1;
    private Direction direction = Direction.RIGHT;

    public Snake(Coordinate position) {
        super(position);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<SnakeTail> getTails() {
        return tails;
    }

    public boolean isAlive() {
        //this.
        return true;
    }

    public void moveSnakeHead() {
        this.setPosition(this.getPosition().add(direction));
    }

    public void moveSnakeTail() {
        //move tail + body
        //  {}----->>

    }

    /**
     * A cycle consists of:
     * * move the snake
     * * move the tail
     * * test for food on the field
     * * update size if necessary
     * * update state (alive, dead) if necessary
     */
    public void completeCycle(PlayField field) {
        moveSnakeHead();
    }

    public int getSize() {
        return size;
    }
}