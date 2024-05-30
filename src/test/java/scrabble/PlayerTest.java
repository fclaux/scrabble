package scrabble;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.*;

class PlayerTest {

	private Player player;

    @BeforeEach
    void set_up() {
        player = new Player("Alice");
    }

    @Test
    void test_initial_score() {
        assertEquals(0, player.score());
    }

    @Test
    void test_set_name() {
        assertEquals("Alice", player.name());
    }

    @Test
    void test_add_tile_in_rack() {
        assertEquals(0, player.rack().size());
        Tile tile = new Tile(Letters.A);
        player.addTileInRack(tile);
        assertEquals(1, player.rack().size());
        assertTrue(player.rack().tiles().contains(tile));
    }

    @Test
    void test_add_score() {
        assertEquals(0, player.score());
        player.addScore(10);
        assertEquals(10, player.score());
        player.addScore(20);
        assertEquals(30, player.score());
    }
    
    @Test
    void test_set_score() {
        assertEquals(0, player.score());
        player.score(30);
        assertEquals(30, player.score());
    }
}
