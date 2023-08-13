package org.jag.snek.logic;

import java.util.ArrayList;
import java.util.List;

public class Snake extends Tile {
    private final List<SnakeTail> tails = new ArrayList<>();
    private Direction direction = Direction.RIGHT;
    private boolean alive = true;

    public Snake(Coordinate position) {
        super(position);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<SnakeTail> getTails() {
        return tails;
    }

    public void initialise() {
        tails.add(new SnakeTail(this.getPosition().add(Direction.LEFT)));
        tails.add(new SnakeTail(this.getPosition()));
        tails.add(new SnakeTail(this.getPosition()));
        tails.add(new SnakeTail(this.getPosition()));
    }

    public boolean isAlive() {
        //this.
        return alive;
    }

    private void moveSnakeHead() {
        this.setPosition(this.getPosition().add(direction));
    }

    private void moveSnakeTail(Coordinate lastPos, boolean hasEaten) {
        if (!hasEaten) {
            tails.remove(0);
        }
        tails.add(new SnakeTail(lastPos));
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
        Coordinate lastPos = getPosition();
        moveSnakeHead();
        boolean hasEaten = CheckForFood(field);
        moveSnakeTail(lastPos, hasEaten);
        checkSnakeAlive();
    }

    private void checkSnakeAlive() {
        List<SnakeTail> tails = getTails();
        for (int i = 0; i < tails.size(); i++) {
            if (this.getPosition().equals(tails.get(i).getPosition())) {
                alive = false;
                break;
            }
        }
    }

    private boolean CheckForFood(PlayField field) {
        Coordinate currentPos = getPosition();
        boolean hasEaten = false;

//        field.getSnacks().fori then press TAB
//        field.getSnacks().for then press TAB

        for (int i = 0; i < field.getSnacks().size(); i++) {
            Snack snack = field.getSnacks().get(i);
            if (currentPos.equals(snack.getPosition())) {
                hasEaten = true;
                field.getSnacks().remove(i);
                break;
            }
        }
        return hasEaten;
    }

    public int getSize() {
        return tails.size() + 1;
    }
}