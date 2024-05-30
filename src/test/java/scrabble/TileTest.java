package scrabble;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.*;

class TileTest {

	Tile tile;
	
	@BeforeEach
	void set_up() {
		tile = new Tile(Letters.M);
	}
	
	@Test
	void get_and_set_Letter() {
		Letters tile_letters_before;
		Letters tile_letters_after;
		
		tile_letters_before = tile.letter();
		tile.letter(Letters.F);
		tile_letters_after = tile.letter();
		assertThat(tile_letters_before).isEqualTo(Letters.M);
		assertThat(tile_letters_after).isNotEqualTo(tile_letters_before);
	}
	
	@Test
	void is_not_a_joker() {
		assertFalse(tile.isJoker());
	}
	
	@Test
	void is_a_joker() {
		Tile joker = new Tile(Letters.JOKER);
		assertTrue(joker.isJoker());
	}
	
	@Test
    void test_equals_and_hash_code() {
        Tile tile1 = new Tile(Letters.A);
        Tile tile2 = new Tile(Letters.A);
        Tile tile3 = new Tile(Letters.B);
        assertTrue(tile1.equals(tile1));

        assertTrue(tile1.equals(tile2));
        assertEquals(tile1.hashCode(), tile2.hashCode());

        assertFalse(tile1.equals(tile3));
        assertNotEquals(tile1.hashCode(), tile3.hashCode());

        Tile joker1 = new Tile(Letters.JOKER);
        Tile joker2 = new Tile(Letters.JOKER);
        assertTrue(joker1.equals(joker2));
        assertEquals(joker1.hashCode(), joker2.hashCode());

        assertFalse(tile1.equals("A"));
        assertFalse(tile1.equals(null));
    }
}
