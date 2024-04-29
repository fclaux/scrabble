package scrabble.model;

import java.util.ArrayList;
import java.util.List;

public class Rack {
    private List<Tile> tiles;
    private static final int MAX_TILES = 7;

    public Rack() {
        tiles = new ArrayList<>(MAX_TILES);
    }
    
}