package org.jag.webserver.dto;

import io.swagger.v3.oas.annotations.Parameter;
import org.jag.snek.logic.Coordinate;

public record StartSnakeRequestCoordinate(
        @Parameter(example = "100") int x,
        @Parameter(example = "100") int y) {

    public Coordinate toCoordinate() {
        return new Coordinate(x, y);
    }
}
