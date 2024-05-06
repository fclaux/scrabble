package scrabble.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BagOfTiles {
    private List<Tile> tiles;

    public BagOfTiles() {
        this.tiles = new ArrayList<>();
        initializeTiles();
    }

    private void initializeTiles() {
        for (int i = 0; i < 15;i++) {
        	tiles.add(new Tile(Letters.E));	
        }
    	for (int i = 0; i < 9; i++) {
            tiles.add(new Tile(Letters.A));
        }
    	for (int i = 0; i < 8; i++) {
            tiles.add(new Tile(Letters.I));
        }
    	for (int i = 0; i < 6; i++) {
            tiles.add(new Tile(Letters.N));
            tiles.add(new Tile(Letters.O));
            tiles.add(new Tile(Letters.R));
            tiles.add(new Tile(Letters.S));
            tiles.add(new Tile(Letters.T));
            tiles.add(new Tile(Letters.U));
        }
    	
    	for (int i = 0; i < 5; i++) {
            tiles.add(new Tile(Letters.L));
    	}
    	
    	for (int i = 0; i < 3; i++) {
            tiles.add(new Tile(Letters.D));
            tiles.add(new Tile(Letters.M));
    	}
    	
    	for (int i = 0; i < 2; i++) {
            tiles.add(new Tile(Letters.G));
            tiles.add(new Tile(Letters.B));
            tiles.add(new Tile(Letters.C));
            tiles.add(new Tile(Letters.P));
            tiles.add(new Tile(Letters.F));
            tiles.add(new Tile(Letters.H));
            tiles.add(new Tile(Letters.V));
    	}

        tiles.add(new Tile(Letters.J));
        tiles.add(new Tile(Letters.Q));
        tiles.add(new Tile(Letters.U));
        tiles.add(new Tile(Letters.Y));
        tiles.add(new Tile(Letters.K));
        tiles.add(new Tile(Letters.W));
        tiles.add(new Tile(Letters.X));
        tiles.add(new Tile(Letters.Z));
        tiles.add(new Tile(Letters.JOKER));
    }
    
    public List<Tile> getTiles() {
		return tiles;
	}
    
    public Tile drawTile() {
		if (!tiles.isEmpty()) {
			return tiles.remove(0);
		}
		return null;
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

    public int getRemainingTilesCount() {
        return tiles.size();
    }
  
    public void shuffle() {
    	Collections.shuffle(tiles);
    }
}
