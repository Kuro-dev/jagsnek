package org.jag.snek;

import org.jag.snek.logic.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {
    PlayField field;
    Snake snake;

    @BeforeEach
    public void prepare() {
        snake = new Snake(new Coordinate(4, 5));
        snake.initialise();
        field = new PlayField(10, 10, snake);
        field.prepare();
    }


    @Test
    public void testLengthStaysTheSame() {

        snake.setDirection(Direction.DOWN);
        snake.completeCycle(field);
        assertEquals(new Coordinate(4, 6), snake.getPosition());
        assertEquals(1, snake.getTails().size(), "The size of the snake should not change");
        assertEquals(2, snake.getSize(), "The size of the snake should not change");

        snake.setDirection(Direction.LEFT);
        snake.completeCycle(field);
        assertEquals(new Coordinate(3, 6), snake.getPosition());
        assertEquals(1, snake.getTails().size(), "The size of the snake should not change");
        assertEquals(2, snake.getSize(), "The size of the snake should not change");

        snake.setDirection(Direction.RIGHT);
        snake.completeCycle(field);
        assertEquals(new Coordinate(4, 6), snake.getPosition());
        assertEquals(1, snake.getTails().size(), "The size of the snake should not change");
        assertEquals(2, snake.getSize(), "The size of the snake should not change");

        snake.setDirection(Direction.UP);
        snake.completeCycle(field);
        assertEquals(new Coordinate(4, 5), snake.getPosition());
        assertEquals(1, snake.getTails().size(), "The size of the snake should not change");
        assertEquals(2, snake.getSize(), "The size of the snake should not change");
    }

    @Test
    public void snakeDiesWhenRunningIntoItselfTest() {
        snake.getTails().add(new SnakeTail(new Coordinate(5, 5)));
        snake.getTails().add(new SnakeTail(new Coordinate(5, 5)));
        snake.setDirection(Direction.RIGHT);
        assertTrue(snake.isAlive(), "Snake should still be alive");
        snake.completeCycle(field);
        assertFalse(snake.isAlive(), "Snake should run into a Tail");
    }

    @Test
    public void snakeGrowsWhenEatingFood() {
        //setup
        field.getSnacks().add(new Snack(new Coordinate(5, 5)));
        snake.setDirection(Direction.RIGHT);

        //check initial state
        assertEquals(1, snake.getTails().size());
        assertEquals(2, snake.getSize());
        //do a cycle
        snake.completeCycle(field);
        //check expected state
        assertEquals(new Coordinate(5, 5), snake.getPosition());
        assertEquals(3, snake.getSize(), "The snake should grow in size after landing on the snack tile");
        assertEquals(2, snake.getTails().size(), "The snake should grow in size after landing on the snack tile");
        assertEquals(0, field.getSnacks().size(), "The snack should be deleted when eaten");
        assertTrue(snake.getTails().contains(new SnakeTail(new Coordinate(4, 5))), "The snake must be attached to its tail");
    }
}
