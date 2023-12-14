package org.jag.snek.logic;

public class SnakeGameBuilder {
    Coordinate startPos = new Coordinate(100, 100);
    private int initialSize = 1;
    private int width = 200;
    private int height = 200;
    private int snackAmount = 1;

    public SnakeGameBuilder setStartingPosition(int x, int y) {
        return setStartingPosition(new Coordinate(x, y));
    }

    public SnakeGameBuilder setStartingPosition(Coordinate startingPosition) {
        startPos = startingPosition;
        return this;
    }

    public SnakeGameBuilder setInitialSize(int initialSize) {
        this.initialSize = initialSize;
        return this;
    }

    public SnakeGameBuilder setSnackAmount(int snacks) {
        this.snackAmount = snacks;
        return this;
    }


    public SnakeGame build() {
        Snake snake = new Snake(startPos);
        for (int i = 0; i < initialSize; i++) {
            snake.getTails().add(new SnakeTail(startPos));
        }
        PlayField field = new PlayField(width, height, snake, snackAmount);
        field.prepare();
        return new SnakeGame(field);
    }

    public SnakeGameBuilder fieldWidth(int width) {
        this.width = width;
        return this;
    }

    public SnakeGameBuilder fieldHeight(int height) {
        this.height = height;
        return this;
    }
}
