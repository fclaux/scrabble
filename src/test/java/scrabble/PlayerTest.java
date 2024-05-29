package scrabble;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.*;

public class PlayerTest {

	Player player;
	BagOfTiles bagOfTiles;
	
	@BeforeEach
	void setUp() {
		player = new Player("Florian");
		bagOfTiles = new BagOfTiles();
		bagOfTiles.shuffle();
	}
	
	@Test
	void get_and_set_score() {
		int player_score;
		
		player.score(130);
		player_score = player.score();
		assertThat(player_score).isEqualTo(130);
	}
	
	@Test
	void get_name() {
		String player_name;
		
		player_name = player.name();
		assertThat(player_name).isEqualTo("Florian");
	}
	
	
	@Test
	void draw_and_exchangeTiles() {
		//TODO
	}
}
