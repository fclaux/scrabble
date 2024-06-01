package scrabble;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.BagOfTiles;
import scrabble.model.Letters;
import scrabble.model.Tile;
import scrabble.util.EmptyBagException;

class BagOfTilesTest {
	
	private BagOfTiles bagOfTiles;

	@BeforeEach
	void set_up() {
		bagOfTiles = new BagOfTiles();
	}

	@Test
	void get_tile_and_remainingTilesCount() {
		List<Tile> listOfTiles = new ArrayList<>();
		listOfTiles.addAll(bagOfTiles.tiles());
		assertEquals(102, listOfTiles.size());
		assertEquals(102, bagOfTiles.remainingTilesCount());
	}
	
	@Test
    void test_draw_tile() {
        assertFalse(bagOfTiles.isEmpty());
        int initialCount = bagOfTiles.remainingTilesCount();
        try {
            Tile tile = bagOfTiles.drawTile();
            assertNotNull(tile);
        } catch (EmptyBagException e) {
            fail("Exception not expected: " + e.getMessage());
        }
        assertEquals(initialCount - 1, bagOfTiles.remainingTilesCount());
    }
	
	@Test
    void test_draw_tile_empty_bag_exception() {
        while (!bagOfTiles.isEmpty()) {
            try {
            	bagOfTiles.drawTile();
            } catch (EmptyBagException e) {
                fail("Exception not expected: " + e.getMessage());
            }
        }

        assertThrows(EmptyBagException.class, () -> bagOfTiles.drawTile());
    }
	
	@Test
    void test_add_and_add_all() {
        int initialCount = bagOfTiles.remainingTilesCount();
        Tile tile = new Tile(Letters.J);
        bagOfTiles.add(tile);
        assertEquals(initialCount + 1, bagOfTiles.remainingTilesCount());

        int additionalCount = 5;
        initialCount = bagOfTiles.remainingTilesCount();
        List<Tile> tilesToAdd = Arrays.asList(new Tile(Letters.E), new Tile(Letters.A), new Tile(Letters.R),
                new Tile(Letters.T), new Tile(Letters.O));
        bagOfTiles.addAll(tilesToAdd);
        assertEquals(initialCount + additionalCount, bagOfTiles.remainingTilesCount());
    }

    @Test
    void test_is_empty() {
        assertFalse(bagOfTiles.isEmpty());

        while (!bagOfTiles.isEmpty()) {
            try {
            	bagOfTiles.drawTile();
            } catch (EmptyBagException e) {
                fail("Exception not expected: " + e.getMessage());
            }
        }

        assertTrue(bagOfTiles.isEmpty());
    }
    
    @Test
    void test_shuffle() {
        List<Tile> originalTiles = new ArrayList<>(bagOfTiles.tiles());

        bagOfTiles.shuffle();

        assertNotEquals(originalTiles, bagOfTiles.tiles());
    }
}
