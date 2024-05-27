package scrabble.model;

import java.util.ArrayList;
import java.util.List;

public class Rack {
    private List<Tile> tiles;
	public static final int MAX_TILES = 7;

    public Rack() {
        tiles = new ArrayList<>(MAX_TILES);
    }
    
    public List<Tile> tiles() {
		return tiles;
	}

	public void addTile(Tile tile) {
        if (tiles.size() < MAX_TILES) {
            tiles.add(tile);
        }
    }

    public Tile removeTile(int index) {
    	Tile removedTile = null;
        if (index >= 0 && index < tiles.size()) {
        	removedTile = tiles.remove(index);
        }
        return removedTile;
    }

    public void swapTiles(int index1, int index2) {
        if (index1 >= 0 && index1 < tiles.size() && index2 >= 0 && index2 < tiles.size()) {
            Tile temp = tiles.get(index1);
            tiles.set(index1, tiles.get(index2));
            tiles.set(index2, temp);
        }
    }
    
    public int size () {
    	return this.tiles.size();
    }

	public boolean isEmpty() {
		return this.tiles.isEmpty();
	}
}