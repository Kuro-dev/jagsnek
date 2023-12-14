package org.jag.snek;

import org.jag.snek.logic.Coordinate;
import org.jag.snek.logic.PlayField;
import org.jag.snek.logic.Snake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayFieldTests {

    private PlayField field;

    @BeforeEach
    public void prepare() {
        Snake snake = new Snake(new Coordinate(5, 5));
        field = new PlayField(10, 10, snake, 1);
        field.prepare();
    }

    @Test
    public void testSnacksArePutOnTheField() {
        assertEquals(1, field.getSnacks().size());
        field.completeCycle();
        assertEquals(1, field.getSnacks().size());
        field.completeCycle();
        assertEquals(1, field.getSnacks().size());
    }
}
