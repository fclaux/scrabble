package scrabble;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import scrabble.model.Direction;

class DirectionTest {
	@Test
    void test_direction_values() {
        Direction[] expectedValues = { Direction.HORIZONTAL, Direction.VERTICAL };

        Direction[] actualValues = Direction.values();
        assertArrayEquals(expectedValues, actualValues);
    }

    @Test
    void test_direction_valueOf() {
        assertEquals(Direction.HORIZONTAL, Direction.valueOf("HORIZONTAL"));
        assertEquals(Direction.VERTICAL, Direction.valueOf("VERTICAL"));
    }

    @Test
    void test_direction_valueOf_invalid() {
    	assertThrows(IllegalArgumentException.class, () -> {
    	    Direction.valueOf("INVALID");
    	});
    }
}
