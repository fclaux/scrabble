package scrabble.application;

import javafx.application.Application;
import javafx.stage.Stage;
import scrabble.controller.JavaFXGameController;

public class ScrabbleApplicationJavaFX extends Application {
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		JavaFXGameController fxController = new JavaFXGameController(primaryStage); 
		fxController.startGame();
	}

	
	
	public static void main(String[] args) {
		launch(args);
	}

}
