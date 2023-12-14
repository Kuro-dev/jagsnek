package org.jag.webserver.dto;

import io.swagger.v3.oas.annotations.Parameter;
import org.jag.snek.logic.SnakeGame;
import org.jag.snek.logic.SnakeGameBuilder;
import org.springdoc.core.annotations.ParameterObject;

import java.util.Optional;

@ParameterObject
public class StartSnakeGameRequest {

    @Parameter(description = "The start position for the snake")
    private StartSnakeRequestCoordinate startPos = null;
    @Parameter(description = "The initial size of the snake", example = "1")
    private Integer initialSize = null;
    @Parameter(description = "The Width of the game-world (in tiles)", example = "200")
    private Integer width = null;
    @Parameter(description = "The Height of the game-world (in tiles)", example = "200")
    private Integer height = null;
    @Parameter(description = "The amount of food present at the same time in the game.", example = "1")
    private Integer snackAmount = null;
    @Parameter(description = "The chance that any given snack will be poisoned, inverting player movement. " +
            "Set to 0 or below to disable the mechanic.", example = "5")
    private Integer snackPoisonChance = null;
    @Parameter(description = "The amount of cycles the snake will be poisoned after eating poisoned food", example = "5")
    private Integer poisonDuration = null;

    public Integer getPoisonDuration() {
        return poisonDuration;
    }

    public void setPoisonDuration(Integer poisonDuration) {
        this.poisonDuration = poisonDuration;
    }

    public StartSnakeRequestCoordinate getStartPos() {
        return startPos;
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

    public Integer getSnackPoisonChance() {
        return snackPoisonChance;
    }

    public void setSnackPoisonChance(Integer snackPoisonChance) {
        this.snackPoisonChance = snackPoisonChance;
    }

    public void setSnackPoisonChance(int snackPoisonChance) {
        this.snackPoisonChance = snackPoisonChance;
    }

    public SnakeGame toGame() {
        SnakeGameBuilder builder = new SnakeGameBuilder();
        Optional.ofNullable(this.initialSize).ifPresent(builder::setInitialSize);
        Optional.ofNullable(this.startPos).ifPresent(c -> builder.setStartingPosition(c.toCoordinate()));
        Optional.ofNullable(this.width).ifPresent(builder::fieldWidth);
        Optional.ofNullable(this.height).ifPresent(builder::fieldHeight);
        Optional.ofNullable(this.snackAmount).ifPresent(builder::setSnackAmount);
        Optional.ofNullable(snackPoisonChance).ifPresent(builder::setSnackPoisonChance);
        Optional.ofNullable(poisonDuration).ifPresent(builder::setPoisonDuration);
        return builder.build();
    }
}
