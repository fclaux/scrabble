package scrabble.model;

import java.util.Collections;
import java.util.List;

import scrabble.gui.Console;
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
    
    public void fillRack(BagOfTiles bagOfTiles) {
        while(this.rack.getTiles().size() < Rack.MAX_TILES) {
            try {
                this.rack.addTile(bagOfTiles.drawTile());
            } catch (EmptyBagException e) {
                Console.message("Le sac est vide, vous ne pouvez plus piocher", true);
                break;
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
        this.fillRack(bagOfTiles);
        bagOfTiles.shuffle();
    }
    
    public void addTileInRack(Tile tile) {
        this.rack.addTile(tile);
    }
    
    public void addScore(int additionalScore) {
        this.score = this.score + additionalScore;
    }
}
