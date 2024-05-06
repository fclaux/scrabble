package scrabble.model;

public class Tile {
	
	
	protected Letters letter;
	
	public Tile(Letters letter) {
		this.letter = letter;
	}

	public Letters getLetter() {
		return letter;
	}

	public void setLetter(Letters letter) {
		this.letter = letter;
	}
	
	public String display() {
	    char[] subscriptDigits = new char[11];
	    for (int i = 0; i < 10; i++) {
	        subscriptDigits[i] = (char) (0x2080 + i);
	    }
	    subscriptDigits[10] = '\u2081';

	    int value = this.letter.getValue();
	    String subscript = String.valueOf(subscriptDigits[value % 10]);
	    if (value >= 10) {
	        subscript = String.valueOf(subscriptDigits[value / 10]) + subscript;
	    }

	    return this.letter + "" + subscript;
	}


	
		
}
