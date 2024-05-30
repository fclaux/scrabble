package scrabble;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import scrabble.model.Letters;
import scrabble.model.Move;
import scrabble.model.Tile;

class MoveTest {

    private Tile tileA;

    @Test
    void test_move_creation() {
    	tileA = new Tile(Letters.A);
        Move move = new Move(1, 2, tileA);
        assertEquals(1, move.row());
        assertEquals(2, move.col());
        assertEquals(tileA, move.tile());
    }
}
