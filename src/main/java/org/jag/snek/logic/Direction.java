package org.jag.snek.logic;

/**
 * Top left corner is 0, 0
 * I am at x = 5, y = 6
 */
public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    public final Coordinate pos;

    Direction(int dx, int dy) {
        pos = new Coordinate(dx, dy);
    }
}
