package scrabble.view.javafx;

import javafx.scene.layout.FlowPane;
import scrabble.model.Rack;

public class RackFX extends FlowPane{
	private Rack rack;
	
	public RackFX(Rack rack) {
        this.rack = rack;
        
        initialize();
    }
	
    public void initialize() {
    	for (int i=0; i<this.rack.size(); i++) {
    		TileFX tileFX = new TileFX(this.rack.tiles().get(i));
    		this.getChildren().add(tileFX);
        }
    }
    
    public void updateRack() {
    	this.getChildren().clear();    	
    	for (int i=0; i<this.rack.size(); i++) {
    		TileFX tileFX = new TileFX(this.rack.tiles().get(i));
    		this.getChildren().add(tileFX);
        }
    }
}
