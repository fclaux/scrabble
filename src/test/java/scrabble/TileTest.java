package scrabble;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.*;

public class TileTest {

	Tile tile;
	
	@BeforeEach
	void setUp() {
		tile = new Tile(Letters.M);
	}
	
	@Test
	void get_and_set_Letter() {
		Letters tile_letters_before;
		Letters tile_letters_after;
		
		tile_letters_before = tile.letter();
		tile.setLetter(Letters.F);
		tile_letters_after = tile.letter();
		assertThat(tile_letters_before).isEqualTo(Letters.M);
		assertThat(tile_letters_after).isNotEqualTo(tile_letters_before);
	}
}
