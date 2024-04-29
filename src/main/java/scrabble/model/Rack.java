package scrabble.model;

import java.util.ArrayList;
import java.util.List;

public class Rack {
    private List<Tile> tiles;
    private static final int MAX_TILES = 7;

    public Rack() {
        tiles = new ArrayList<>(MAX_TILES);
    }
    
    public void addTile(Tile tile) {
        if (tiles.size() < MAX_TILES) {
            tiles.add(tile);
        }
    }

    public void removeTile(int index) {
        if (index >= 0 && index < tiles.size()) {
            tiles.remove(index);
        }
    }

    public void swapTiles(int index1, int index2) {
        if (index1 >= 0 && index1 < tiles.size() && index2 >= 0 && index2 < tiles.size()) {
            Tile temp = tiles.get(index1);
            tiles.set(index1, tiles.get(index2));
            tiles.set(index2, temp);
        }
    }
    
}