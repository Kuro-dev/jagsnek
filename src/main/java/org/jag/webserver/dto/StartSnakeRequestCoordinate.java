package org.jag.webserver.dto;

import io.swagger.v3.oas.annotations.Parameter;
import org.jag.snek.logic.Coordinate;

public class StartSnakeRequestCoordinate {
    @Parameter(example = "100")
    private final int x;
    @Parameter(example = "100")
    private final int y;

    public StartSnakeRequestCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate toCoordinate() {
        return new Coordinate(x, y);
    }
}
