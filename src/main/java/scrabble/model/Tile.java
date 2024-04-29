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
	    char[] subscriptDigits = new char[11];
	    for (int i = 0; i < 10; i++) {
	        subscriptDigits[i] = (char) (0x2080 + i);
	    }
	    subscriptDigits[10] = '\u2081';

	    int value = this.tile.getValue();
	    String subscript = String.valueOf(subscriptDigits[value % 10]);
	    if (value >= 10) {
	        subscript = String.valueOf(subscriptDigits[value / 10]) + subscript;
	    }

	    return this.tile + "" + subscript;
	}


	
		
}
