package scrabble.model;

public class Move {
	private int row;
    private int col;
    private Tile tile;

    public Move(int row, int col, Tile tile) {
        this.row = row;
        this.col = col;
        this.tile = tile;
    }

	public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    public Tile tile() {
        return tile;
    }
}
