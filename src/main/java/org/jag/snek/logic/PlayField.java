package org.jag.snek.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayField {
    private static Random RNG = new Random();
    ;
    private final Snake snek;
    //available spaces
    private final int width;
    private final int height;
    private final List<Snack> snacks = new ArrayList<>();
    /**
     * The preferred amount of snacks that should be on the map at any given point
     */
    private final int preferredSnackAmount;

    public PlayField(int width, int height, Snake snek) {
        this(width, height, snek, 1);
    }

    public PlayField(int width, int height, Snake snek, int preferredSnackAmount) {
        this.width = width;
        this.height = height;
        this.snek = snek;
        this.preferredSnackAmount = preferredSnackAmount;
    }

    public static Random getRNG() {
        if (RNG == null)
            RNG = new Random();
        return RNG;
    }

    public static void setRNG(Random rng) {
        RNG = rng;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Snake getSnek() {
        return snek;
    }

    public void prepare() {
        snek.initialise();
        for (int i = 0; i < preferredSnackAmount; i++) {
            Snack snack = new Snack(getRandomFreePosition());
            snacks.add(snack);
        }
    }

    public List<Snack> getSnacks() {
        return snacks;
    }

    public void completeCycle() {
        getSnek().completeCycle(this);
        //Check the state of the field
        final int actual = snacks.size();
        if (preferredSnackAmount > actual) {
            Snack snack = new Snack(getRandomFreePosition());
            snacks.add(snack);
        }
    }

    /**
     * Conditions:
     * NOT overlap with snake head or tail
     * NOT overlap with other snacks
     */
    private Coordinate getRandomFreePosition() {
        return getRandomFreePosition(0);
    }

    /**
     * Conditions:
     * NOT overlap with snake head or tail
     * NOT overlap with other snacks
     */
    private Coordinate getRandomFreePosition(int recursion) {

        Random rng = getRNG();
        int randomX = rng.nextInt(this.width);
        int randomY = rng.nextInt(this.height);
        //TODO optimise this for less CPU usage,
        // remove recursion and consider including a win-condition for the game to end
        // when the snake reached the size of the entire play field.
        Coordinate coordinate = new Coordinate(randomX, randomY);
        if (recursion >= 15) {
            return coordinate;
        }
        Snake snake = this.getSnek();
        List<Coordinate> tailPositions = snek.getTails().stream().map(Tile::getPosition).toList();
        List<Coordinate> snackPositions = snacks.stream().map(Tile::getPosition).toList();
        if (coordinate.equals(snake.getPosition())) {
            //not okay
            return getRandomFreePosition(++recursion);
        }
        //the coordinate does NOT overlap with the snakes head
        else if (tailPositions.contains(coordinate)) {
            return getRandomFreePosition(++recursion);
            //not okay
        } else if (snackPositions.contains(coordinate)) {
            return getRandomFreePosition(++recursion);
            //not okay
        }
        //everything is okay
        return coordinate;
    }
}
