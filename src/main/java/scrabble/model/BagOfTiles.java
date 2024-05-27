package scrabble.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import scrabble.util.EmptyBagException;

public class BagOfTiles {
    private List<Tile> tiles;

    public BagOfTiles() {
        this.tiles = new ArrayList<>();
        initializeTiles();
    }

    private void initializeTiles() {
    	EnumMap<Letters, Integer> letterFrequency = new EnumMap<>(Letters.class);
        letterFrequency.put(Letters.E, 15);
        letterFrequency.put(Letters.A, 9);
        letterFrequency.put(Letters.I, 8);
        letterFrequency.put(Letters.N, 6);
        letterFrequency.put(Letters.O, 6);
        letterFrequency.put(Letters.R, 6);
        letterFrequency.put(Letters.S, 6);
        letterFrequency.put(Letters.T, 6);
        letterFrequency.put(Letters.U, 6);
        letterFrequency.put(Letters.L, 5);
        letterFrequency.put(Letters.D, 3);
        letterFrequency.put(Letters.M, 3);
        letterFrequency.put(Letters.G, 2);
        letterFrequency.put(Letters.B, 2);
        letterFrequency.put(Letters.C, 2);
        letterFrequency.put(Letters.P, 2);
        letterFrequency.put(Letters.F, 2);
        letterFrequency.put(Letters.H, 2);
        letterFrequency.put(Letters.V, 2);
        letterFrequency.put(Letters.J, 1);
        letterFrequency.put(Letters.Q, 1);
        letterFrequency.put(Letters.Y, 1);
        letterFrequency.put(Letters.K, 1);
        letterFrequency.put(Letters.W, 1);
        letterFrequency.put(Letters.X, 1);
        letterFrequency.put(Letters.Z, 1);
        letterFrequency.put(Letters.JOKER, 2);

        for (Map.Entry<Letters, Integer> entry : letterFrequency.entrySet()) {
            Letters letter = entry.getKey();
            int frequency = entry.getValue();
            for (int i = 0; i < frequency; i++) {
                tiles.add(new Tile(letter));
            }
        }
    }
    
    public List<Tile> tiles() {
		return tiles;
	}
    
    public Tile drawTile() throws EmptyBagException {
		if (tiles.isEmpty()) {
			throw new EmptyBagException("Can't draw a tile, the bag is empty");
		}
		return tiles.remove(0);
    }
    
    public void add(Tile tile) {
		this.tiles.add(tile);
	}
	
	public void addAll(Collection<Tile> tiles) {
		this.tiles.addAll(tiles);
	}
	
    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    public int remainingTilesCount() {
        return tiles.size();
    }
  
    public void shuffle() {
    	Collections.shuffle(tiles);
    }
    
}
