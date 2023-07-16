package org.jag.snek.logic;

import java.util.ArrayList;
import java.util.List;

public class PlayField {
    private final Snake snek;
    //available spaces
    private final int width;
    private final int height;
    private final List<Snack> snacks = new ArrayList<>();

    public PlayField(int width, int height, Snake snek) {
        this.width = width;
        this.height = height;
        this.snek = snek;
    }

    public Snake getSnek() {
        return snek;
    }

    public void prepare() {

    }

    public List<Snack> getSnacks() {
        return snacks;
    }
}
