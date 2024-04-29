package scrabble.model;

public class Tile {
	
	protected Letters tile;
	
	public Tile(Letters tile) {
		this.tile = tile;
	}

	public Letters getTile() {
		return tile;
	}

	public void setTile(Letters tile) {
		this.tile = tile;
	}
	
	public String display() {
		return "Letter :" + this.tile + " Value :" + this.tile.getValue();
	}
	
		
}
