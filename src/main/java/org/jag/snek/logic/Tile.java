package org.jag.snek.logic;

import java.util.Objects;

/**
 * try to think of questions you can ask all the different parts of tile.
 * different tiles are:
 * - snake head tile
 * - Snake tail tile
 * - blank tile
 * - Food Tile
 * <p>
 * They are all a "Tile"
 */
public class Tile {
    private Coordinate position;

    public Tile(Coordinate position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return Objects.equals(position, tile.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    public Coordinate getPosition() {
        return position;
    }

    protected void setPosition(Coordinate position) {
        this.position = position;
    }
}
