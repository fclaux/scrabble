package scrabble.model;

public class Cell {
	private Tile tile;
	private final Effects effect;
	private boolean isOccupied;
	
	public Cell (Effects effect) {
		this.tile=null;
		this.effect=effect;
		this.isOccupied=false;
	}

	public Tile getTile() {
		return tile;
	}

	public Effects getEffect() {
		return effect;
	}

	public boolean isOccupied() {
		return isOccupied;
	}
}
