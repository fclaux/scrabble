package scrabble.model;

public class Cell {
	private Tile tile;
	private final Effects effect;
	
	public Cell (Effects effect) {
		this.tile=null;
		this.effect=effect;
	}

	public Tile getTile() {
		return tile;
	}

	public Effects getEffect() {
		return effect;
	}

	public boolean isEmpty() {
		return this.tile == null;
	}
	
	public void setTile(Tile tile) {
		this.tile=tile;
	}
	
	public void removeTile() {
		this.tile=null;
	}
}
