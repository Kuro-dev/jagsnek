package org.jag.webserver.dto;

import io.swagger.v3.oas.annotations.Parameter;
import org.jag.snek.logic.Coordinate;
import org.jag.snek.logic.SnakeGame;
import org.jag.snek.logic.SnakeGameBuilder;

import java.util.Optional;

public class StartSnakeGameRequest {

    @Parameter(description = "The start position for the snake")
    private StartSnakeRequestCoordinate startPos = new StartSnakeRequestCoordinate(100, 100);

    @Parameter(description = "The initial size of the snake", example = "1")
    private Integer initialSize = 1;

    @Parameter(description = "The Width of the game-world", example = "200")
    private Integer width = 200;

    @Parameter(description = "The Height of the game-world", example = "200")
    private Integer height = 200;

    @Parameter(description = "The amount of food present at the same time in the game.", example = "1")
    private Integer snackAmount = 1;

    public Coordinate getStartPos() {
        return startPos.toCoordinate();
    }

    public void setStartPos(StartSnakeRequestCoordinate startPos) {
        this.startPos = startPos;
    }

    public Integer getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getSnackAmount() {
        return snackAmount;
    }

    public void setSnackAmount(Integer snackAmount) {
        this.snackAmount = snackAmount;
    }

    public SnakeGame toGame() {
        SnakeGameBuilder builder = new SnakeGameBuilder();
        Optional.ofNullable(this.initialSize).ifPresent(builder::setInitialSize);
        Optional.ofNullable(this.startPos).ifPresent(c -> builder.setStartingPosition(c.toCoordinate()));
        Optional.ofNullable(this.width).ifPresent(builder::fieldWidth);
        Optional.ofNullable(this.height).ifPresent(builder::fieldHeight);
        Optional.ofNullable(this.snackAmount).ifPresent(builder::setSnackAmount);
        return builder.build();
    }
}
