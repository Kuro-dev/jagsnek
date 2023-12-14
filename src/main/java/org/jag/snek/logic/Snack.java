package org.jag.snek.logic;

public class Snack extends Tile {
    private boolean isPoisoned = false;

    public Snack(Coordinate position) {
        super(position);
    }

    public boolean isPoisoned() {
        return isPoisoned;
    }

    public void setPoisoned(boolean poisoned) {
        isPoisoned = poisoned;
    }
}
