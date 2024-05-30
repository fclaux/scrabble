package scrabble;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.Letters;
import scrabble.model.Rack;
import scrabble.model.Tile;

class RackTest {
	private Rack rack;
    private Tile tileA;
    private Tile tileB;

    @BeforeEach
    public void setUp() {
        rack = new Rack();
        tileA = new Tile(Letters.A);
        tileB = new Tile(Letters.B);
    }

    @Test
    void test_add_tile() {
        rack.addTile(tileA);
        List<Tile> tiles = rack.tiles();
        assertTrue(tiles.contains(tileA));
    }

    @Test
    void test_remove_tile() {
        rack.addTile(tileA);
        rack.addTile(tileB);
        Tile removedTile = rack.removeTile(0);
        assertEquals(tileA, removedTile);
        assertFalse(rack.tiles().contains(tileA));
    }

    @Test
    void test_remove_tile_invalid_index_upper_than_size() {
        rack.addTile(tileA);
        Tile removedTile = rack.removeTile(5);
        assertNull(removedTile);
    }
    
    @Test
    void test_remove_tile_invalid_index_lower_than_zero() {
        rack.addTile(tileA);
        Tile removedTile = rack.removeTile(-1);
        assertNull(removedTile);
    }

    @Test
    void test_swap_tiles() {
        rack.addTile(tileA);
        rack.addTile(tileB);
        rack.swapTiles(0, 1);
        List<Tile> tiles = rack.tiles();
        assertEquals(tileB, tiles.get(0));
        assertEquals(tileA, tiles.get(1));
    }

    @Test
    void test_swap_tiles_invalid_first_index_upper_than_size() {
        rack.addTile(tileA);
        rack.addTile(tileB);
        rack.swapTiles(2, 1);
        List<Tile> tiles = rack.tiles();
        assertEquals(tileA, tiles.get(0));
        assertEquals(tileB, tiles.get(1));
    }
    
    @Test
    void test_swap_tiles_invalid_first_index_lower_than_zero() {
        rack.addTile(tileA);
        rack.addTile(tileB);
        rack.swapTiles(-1, 1);
        List<Tile> tiles = rack.tiles();
        assertEquals(tileA, tiles.get(0));
        assertEquals(tileB, tiles.get(1));
    }
    
    @Test
    void test_swap_tiles_invalid_second_index_upper_than_size() {
        rack.addTile(tileA);
        rack.addTile(tileB);
        rack.swapTiles(0, 2);
        List<Tile> tiles = rack.tiles();
        assertEquals(tileA, tiles.get(0));
        assertEquals(tileB, tiles.get(1));
    }
    
    @Test
    void test_swap_tiles_invalid_second_index_lower_than_zero() {
        rack.addTile(tileA);
        rack.addTile(tileB);
        rack.swapTiles(0, -1);
        List<Tile> tiles = rack.tiles();
        assertEquals(tileA, tiles.get(0));
        assertEquals(tileB, tiles.get(1));
    }

    @Test
    void test_size() {
        assertEquals(0, rack.size());
        rack.addTile(tileA);
        assertEquals(1, rack.size());
        assertFalse(rack.isFull());
    }

    @Test
    void test_is_empty() {
        assertTrue(rack.isEmpty());
        rack.addTile(tileA);
        assertFalse(rack.isEmpty());
    }

    @Test
    void test_is_full() {
        for (int i = 0; i < Rack.MAX_TILES; i++) {
            rack.addTile(new Tile(Letters.values()[i]));
        }
        assertTrue(rack.isFull());
        rack.addTile(new Tile(Letters.JOKER));
        assertEquals(Rack.MAX_TILES, rack.tiles().size());
    }
}
