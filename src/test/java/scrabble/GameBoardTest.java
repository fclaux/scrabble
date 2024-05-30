package scrabble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import scrabble.model.GameBoard;
import scrabble.model.Letters;
import scrabble.model.Tile;
import scrabble.util.IndexOutOfBoardException;

class GameBoardTest {
	private GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard();
    }

    @Test
    void test_add_tile() {
        Tile tile = new Tile(Letters.A);
        gameBoard.addTile(tile, 5, 5);
        assertEquals(tile, gameBoard.tile(5, 5));
    }

    @Test
    void test_get_cell_out_of_board() {
        assertDoesNotThrow(() -> gameBoard.cell(1, 1));
        assertThrows(IndexOutOfBoardException.class, () -> gameBoard.cell(16, 1));
        assertThrows(IndexOutOfBoardException.class, () -> gameBoard.cell(1, 16));
        assertThrows(IndexOutOfBoardException.class, () -> gameBoard.cell(0, 1));
        assertThrows(IndexOutOfBoardException.class, () -> gameBoard.cell(1, 0));
    }
}
