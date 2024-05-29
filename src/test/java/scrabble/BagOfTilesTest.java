package scrabble;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.BagOfTiles;
import scrabble.model.Letters;
import scrabble.model.Tile;

public class BagOfTilesTest {
	
	private BagOfTiles bagOfTiles;

	@BeforeEach
	void setUp() {
		bagOfTiles = new BagOfTiles();
	}

	@Test
	void get_tile_and_remainingTilesCount() {
		List<Tile> listOfTiles = new ArrayList<>();
		listOfTiles.addAll(bagOfTiles.tiles());
		assertEquals(102, listOfTiles.size());
		assertEquals(102, bagOfTiles.remainingTilesCount());
	}
}
