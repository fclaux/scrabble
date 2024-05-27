package scrabble;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Cell;
import scrabble.model.Effects;
import scrabble.model.Letters;
import scrabble.model.Tile;

class CellTest {
	
	private Cell cell;
	
	@BeforeEach
	void setUp() {
        cell = new Cell(Effects.NONE);
    }
	
	@Test
	void cell_is_empty_at_the_beginning() {
		assertTrue(cell.isEmpty());
	}
	
	@Test
	void add_and_remove_tile_in_cell() {
		Tile tile = new Tile(Letters.A);
		cell.tile(tile);
		assertFalse(cell.isEmpty());
		cell.removeTile();
		assertTrue(cell.isEmpty());
		cell.removeTile();
		assertTrue(cell.isEmpty());
	}
	
}
