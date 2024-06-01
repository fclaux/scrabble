package scrabble.model;

public class Cell {
	private Tile tile;
	private Effects effect;
	
	public Cell (Effects effect) {
		this.tile=null;
		this.effect=effect;
	}

	public Tile tile() {
		return tile;
	}

	public Effects effect() {
		return effect;
	}

	public boolean isEmpty() {
		return this.tile == null;
	}
	
	public void tile(Tile tile) {
		this.tile=tile;
	}
	
	public void removeTile() {
		this.tile=null;
	}
	
	 public void setEffect(Effects effect) {
	        this.effect = effect;
	    }
}
