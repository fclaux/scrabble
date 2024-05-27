package scrabble.model;

public class Player {
    private final String name;
    private int score;
    private Rack rack;
    
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.rack = new Rack();
    }
    
    public int score() {
        return score;
    }
    
    public void score(int score) {
        this.score = score;
    }
    
    public String name() {
        return name;
    }

    public Rack rack() {
        return this.rack;
    }
    
    public void addTileInRack(Tile tile) {
        this.rack.addTile(tile);
    }
    
    public void addScore(int additionalScore) {
        this.score = this.score + additionalScore;
    }
}
