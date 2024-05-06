package scrabble.model;

import java.util.Collections;
import java.util.List;

import scrabble.util.EmptyBagException;

public class Player {
	private final String name;
	private int score;
	private Rack rack;
	
	public Player(String name) {
		this.name = name;
		this.score = 0;
		this.rack = new Rack();
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}

	public Rack getRack() {
		return this.rack;
	}
	
	public void draw(BagOfTiles bagOfTiles) {
		while(this.rack.getTiles().size()<Rack.MAX_TILES) {
			try {
				this.rack.addTile(bagOfTiles.drawTile());
			} catch (EmptyBagException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void exchangeTiles(BagOfTiles bagOfTiles, List<Integer> indices) {
		Collections.sort(indices, Collections.reverseOrder());
		for (int index : indices) {
            if (index >= 0 && index < Rack.MAX_TILES) {
            	bagOfTiles.add(this.rack.removeTile(index));
            }
        }
		this.draw(bagOfTiles);
		bagOfTiles.shuffle();
	}
}
