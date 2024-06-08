package scrabble.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import scrabble.model.*;
import scrabble.util.EmptyBagException;
import scrabble.util.IndexOutOfBoardException;
import scrabble.util.ScoreCounter;
import scrabble.view.javafx.*;

public class ScrabbleApplicationJavaFX extends Application {
	
	private Player player;
    private GameBoard gameBoard;
    private BagOfTiles bagOfTiles;
    private ScoreCounter scoreCounter;
    private PlayerFX playerFX;
    private GameBoardFX gameBoardFX;
    private RackFX rackFX;
    
    private IntegerProperty roundCounter;
    private IntegerProperty remainingTileInBag;
    private IntegerProperty scoreForMove;
    private Label titleLb;
    private Label roundCounterLb;
    private Label scoreMoveLb; 
    private Label remainingTileInBagLb;
    private Label errorMoveLb;
    private int lastExchangeTurn;
    
    private Button playBtn;
    private Button changeLetterBtn;
    private Button leaveBtn;
    
    private static final int SIZE_BTN_FX = 150;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		initialize(); 
		
		fillPlayerRack(player);
    	rackFX.updateRack();
        
        playBtn = new Button("Jouer un coup");
        playBtn.setPrefWidth(SIZE_BTN_FX);
        playBtn.setOnMouseClicked(event -> {
			try {
				play();
			} catch (IndexOutOfBoardException e) {
				e.printStackTrace();
			}
			fillPlayerRack(player);
			if (player.rack().isEmpty()) {
				alertForEndGame();
			}
			update();
		});
        
        changeLetterBtn = new Button("Changer de lettre");
        changeLetterBtn.setPrefWidth(SIZE_BTN_FX);
        changeLetterBtn.setOnMouseClicked(event ->  {
        	if (lastExchangeTurn!=roundCounter.get()) {
        		exchangeTiles(player);
        	} 
        	update();
        });
        
        leaveBtn = new Button("Quitter");
        leaveBtn.setPrefWidth(SIZE_BTN_FX);
        leaveBtn.setOnMouseClicked(event -> leaveApplicationWithAlert());
        
        HBox titleHBox = new HBox();
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().add(titleLb);
        
        VBox informationsVBox = new VBox();
        informationsVBox.setMinWidth(300);
        informationsVBox.setMaxWidth(300);
        informationsVBox.setSpacing(50);
        informationsVBox.setAlignment(Pos.CENTER_LEFT);
        informationsVBox.setPadding(new Insets(0, 0, 0, 50));
        informationsVBox.getChildren().addAll(roundCounterLb, playerFX, scoreMoveLb, remainingTileInBagLb);
        
        VBox playerActionsVBox = new VBox();
        playerActionsVBox.setMinWidth(300);
        playerActionsVBox.setMaxWidth(300);
        playerActionsVBox.setSpacing(50);
        playerActionsVBox.setAlignment(Pos.CENTER);
        playerActionsVBox.getChildren().addAll(playBtn, changeLetterBtn, leaveBtn);
        
        VBox rackVBox = new VBox();
        rackVBox.setSpacing(30);
        rackVBox.setAlignment(Pos.CENTER);
        rackVBox.setPadding(new Insets(0, 0, 20, 0));
        rackVBox.getChildren().addAll(errorMoveLb, rackFX);
        
		BorderPane root = new BorderPane();
		root.setCenter(gameBoardFX);	
		root.setTop(titleHBox);
		root.setLeft(informationsVBox);
		root.setRight(playerActionsVBox);
		root.setBottom(rackVBox);
		
		Scene scene = new Scene(root, 1200, 850);
		scene.getRoot().setStyle("-fx-background-color: linear-gradient(to bottom, #c8e6c9, #ffffff);");
		
		primaryStage.setTitle("Scrabble en JavaFX");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void leaveApplicationWithAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("Quitter l'application");
		alert.setContentText("Êtes-vous sûr de vouloir quitter l'application?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			Platform.exit();
		}
	}

	private void alertForEndGame() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Fin de partie");
		alert.setContentText("La partie est terminée, aucun jeton n'est disponible !");
		
		alert.show();
		playBtn.setDisable(true);
		lastExchangeTurn = roundCounter.get();
		errorMoveLb.setText("La partie est terminé !");
	}
	
	private void update() {
		updateButtonChangeLetterState();
		playerFX.updatePlayer();
		try {
			gameBoardFX.updateBoard();
		} catch (IndexOutOfBoardException e) {
			e.printStackTrace();
		}
		rackFX.updateRack();
	}

	private void updateButtonChangeLetterState() {
        changeLetterBtn.setDisable(lastExchangeTurn == roundCounter.get());
    }
	
	private void initialize() throws IndexOutOfBoardException {
		gameBoard = new GameBoard();
		player = new Player("Joueur1");
		bagOfTiles = new BagOfTiles();
		scoreCounter = new ScoreCounter();
		roundCounter = new SimpleIntegerProperty(1);
		remainingTileInBag = new SimpleIntegerProperty(bagOfTiles.remainingTilesCount());
		roundCounterLb = new Label();
		roundCounterLb.textProperty().bind(Bindings.concat("Tour : ",roundCounter.asString()));
		scoreForMove = new SimpleIntegerProperty(0);
		scoreMoveLb = new Label();
		scoreMoveLb.textProperty().bind(Bindings.concat("Score du coup : ",scoreForMove.asString()));
		remainingTileInBagLb = new Label();
		remainingTileInBagLb.textProperty().bind(Bindings.concat("Tuiles restantes : ",remainingTileInBag.asString()));
		errorMoveLb = new Label();
		errorMoveLb.setTextFill(Color.RED);
		errorMoveLb.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		playerFX = new PlayerFX(player);
		rackFX = new RackFX(player.rack());
		rackFX.setAlignment(Pos.CENTER);
		gameBoardFX = new GameBoardFX(gameBoard);
		gameBoardFX.setAlignment(Pos.CENTER);
		titleLb = new Label("Scrabble");
		titleLb.setFont(Font.font("Arial", FontWeight.BOLD, 48));
		titleLb.setTextFill(Color.DARKGREEN);
		lastExchangeTurn = -1;
		shuffleBagOfTiles();
	}
	
	private void fillPlayerRack(Player player) {
        Rack playerRack = player.rack();
        try {
            while (!playerRack.isFull() && !bagOfTiles.isEmpty()) {
                Tile tile = bagOfTiles.drawTile();
                if (tile != null) {
                    playerRack.addTile(tile);
                }
            }
        } catch (EmptyBagException e) {
            e.printStackTrace();
        }
        remainingTileInBag.set(bagOfTiles.remainingTilesCount());
    }
	
	private void play() throws IndexOutOfBoardException{
        List<Move> playerMoves = getPlayerMoves();
        
        applyMoves(playerMoves);
    }
	
	private void applyMoves(List<Move> playerMoves) throws IndexOutOfBoardException {
        if (isMovesValid(playerMoves)) {
            addTilesToBoard(playerMoves);
            int score = scoreCounter.calculateScoreForMoves(playerMoves, gameBoard);
            player.addScore(score);
            scoreForMove.set(score);
            roundCounter.set(roundCounter.get()+1);
            errorMoveLb.setText("");
        } else {
        	errorMoveLb.setText("Votre coup n'est pas valide !");
            returnTilesToRack(playerMoves);
        }
    }
	
	private void addTilesToBoard(List<Move> playerMoves) {
        for (Move move : playerMoves) {
            Tile tile = move.tile();
            int row = move.row();
            int col = move.col();
            this.gameBoard.addTile(tile, row, col);
        }
    }

    private void returnTilesToRack(List<Move> playerMoves) {
        for (Move move : playerMoves) {
            Tile tile = move.tile();
            if (tile.isJoker()) {
                tile.letter(Letters.JOKER);
            }
            this.player.addTileInRack(tile);
        }
    }
    
    private int[] askForRowAndColumn() {
    	int[] defaultValue = {1,1};
        Dialog<int[]> dialog = new Dialog<>();
        dialog.setTitle("Choisir Ligne et Colonne");
        dialog.setHeaderText("Veuillez choisir une ligne et une colonne");

        ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        List<Integer> choices = new ArrayList<>();
        for (int i = 1; i <= GameBoard.SIZE_GRID; i++) {
            choices.add(i);
        }

        ComboBox<Integer> rowComboBox = new ComboBox<>(FXCollections.observableArrayList(choices));
        rowComboBox.setValue(1);
        ComboBox<Integer> colComboBox = new ComboBox<>(FXCollections.observableArrayList(choices));
        colComboBox.setValue(1);

        grid.add(new Label("Veuillez entrer la ligne (1 - " + GameBoard.SIZE_GRID + "):"),0,0);
        grid.add(rowComboBox, 1, 0);
        grid.add(new Label("Veuillez entrer la colonne (1 - " + GameBoard.SIZE_GRID + "):"),0,1);
        grid.add(colComboBox, 1, 1);

        dialog.getDialogPane().setContent(grid);
        
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.setOnCloseRequest(WindowEvent::consume);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return new int[]{rowComboBox.getValue(), colComboBox.getValue()};
            }
            return null;
        });

        Optional<int[]> result = dialog.showAndWait();
        return result.orElse(defaultValue);
    }

    private List<Move> getPlayerMoves() throws IndexOutOfBoardException {
        int row;
        int col;
        int nbLetters = 0;
        Rack rack = this.player.rack();
        List<Move> moves = new ArrayList<>();
        
        Tile tile = answerTile(rack);
        if (tile != null) {
        	int[] coordinates = askForRowAndColumn();
    		row = coordinates[0];
            col = coordinates[1];

            moves.add(new Move(row, col, tile));
            nbLetters += 1;
            
            tile = answerTile(rack);
            if (tile != null) {
                placeAWord(rack,tile,nbLetters,moves,row,col);
            }     
        }
        return moves;
    }
    
    private Direction askForDirection() {
        List<Direction> choices = FXCollections.observableArrayList(Direction.HORIZONTAL, Direction.VERTICAL);

        ChoiceDialog<Direction> dialog = new ChoiceDialog<>(Direction.HORIZONTAL, choices);
        dialog.setTitle("Choisir la direction");
        dialog.setHeaderText("Sélectionnez une direction");
        dialog.setContentText("Direction:");

        Optional<Direction> result = dialog.showAndWait();
        return result.orElse(Direction.HORIZONTAL);
    }
    
    private void placeATile(List<Move> moves, Direction direction, int row, int col, int nbLetters, Tile tile) throws IndexOutOfBoardException {
    	if (direction == Direction.HORIZONTAL) {
            Cell cell = gameBoard.cell(row, col + nbLetters);
            while(!cell.isEmpty()) {
                nbLetters += 1;
                cell = gameBoard.cell(row + nbLetters, col);
            }       
            moves.add(new Move(row, col + nbLetters, tile));
        } else {
            Cell cell = gameBoard.cell(row + nbLetters, col);
            while(!cell.isEmpty()) {
                nbLetters +=1;
                cell = gameBoard.cell(row + nbLetters, col);
            }
            moves.add(new Move(row + nbLetters, col, tile));
        }
    }
    
    private void placeAWord(Rack rack,Tile tile ,int nbLetters,List<Move> moves, int row, int col) throws IndexOutOfBoardException{
    	Direction direction = null;
        boolean firstOccurrence = true;
    	
    	while (tile != null && !rack.isEmpty()) {
            if (firstOccurrence) {
            	direction = askForDirection();
            	placeATile(moves,direction,row,col,nbLetters,tile);
            	nbLetters = nbLetters + 1;
                firstOccurrence = false;
            }
            tile = answerTile(rack);
            if (tile != null) {
            	placeATile(moves,direction,row,col,nbLetters,tile);
            	nbLetters += 1;
            }
        }
    }
    

    private Tile answerTile(Rack rack) {

    	Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Choix du jeton");
        dialog.setHeaderText("Sélectionnez l'indice du jeton à poser");

        ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        VBox tileList = new VBox();
        tileList.setSpacing(10);

        ToggleGroup group = new ToggleGroup();
        List<RadioButton> radioButtons = new ArrayList<>();
        
        for (int i = 1; i <= rack.size(); i++) {
            RadioButton radioButton = new RadioButton(formatTile(rack.tile(i-1)));
            radioButton.setToggleGroup(group);
            tileList.getChildren().add(radioButton);
            radioButtons.add(radioButton);
        }

        dialog.getDialogPane().setContent(tileList);

        dialog.setResultConverter(new Callback<ButtonType, Integer>() {
            @Override
            public Integer call(ButtonType dialogButton) {
            	int selectedIndice = 0;
                if (dialogButton == okButtonType) {
                	Toggle selectedToggle = group.getSelectedToggle();
                	if (selectedToggle != null) {
                        RadioButton selectedRadioButton = (RadioButton) selectedToggle;
                        return radioButtons.indexOf(selectedRadioButton)+1;
                    }
                    return selectedIndice;
                }
                return null;
            }
        });

        Optional<Integer> result = dialog.showAndWait();

        if (result.isPresent()) {
        	Integer selectedIndice = result.get();
        	Tile tile= rack.removeTile(selectedIndice-1);
        	if (tile != null) {
        		if (tile.isJoker()) {
	        		askLetterForJoker(tile);
	        	}
        	}	
        	return tile;
        }
        return null;
    }
    
    private void askLetterForJoker(Tile tile) {
    	List<Character> choices = new ArrayList<>();
    	
    	for (char letter = 'A'; letter <= 'Z'; letter++) {
    		choices.add(letter);
        }

        ChoiceDialog<Character> dialog = new ChoiceDialog<>('A', choices);
        dialog.setTitle("Choisir la lettre que remplace le Joker");
        dialog.setHeaderText("Choisissez la lettre");
        dialog.setContentText("Lettre :");

        Optional<Character> result = dialog.showAndWait();
        result.ifPresent(selectedLetter -> 
            tile.letter(Letters.valueOf(String.valueOf(selectedLetter)))
        );
    }

    private boolean isMovesValid(List<Move> playerMoves) {
        boolean tileOnStars = false;
        boolean tileOnOccupiedCell = false;
        boolean allInSameRow = true;
        boolean allInSameCol = true;
        boolean haveNearTile = false;

        if (playerMoves.isEmpty()) {
            return false;
        }
        
        int firstRow = playerMoves.get(0).row();
        int firstCol = playerMoves.get(0).col();

        for (Move move : playerMoves) {
            int row = move.row();
            int col = move.col();

            if (row != firstRow) {
                allInSameRow = false;
            }
            if (col != firstCol) {
                allInSameCol = false;
            }

            try {
                if (col == GameBoard.MIDDLE_CASE && row == GameBoard.MIDDLE_CASE) {
                    tileOnStars = true;
                }

                if (!this.gameBoard.cell(row, col).isEmpty()) {
                    tileOnOccupiedCell = true;
                }
                
                if (isAdjacentToExistingWord(row, col)) {
                        haveNearTile = true;
                }
            } catch (IndexOutOfBoardException e) {
                e.printStackTrace();
            }
        }

        if (isFirstMove() && !tileOnStars) {
            return false;
        } 

        if (!isFirstMove() && !haveNearTile) {
            return false;
        }
        
        if (isFirstMove() && playerMoves.size() == 1) {
        	return false;
        }

        if (tileOnOccupiedCell) {
            return false;
        }

        if (!(allInSameRow || allInSameCol)) {
            return false;
        }

        return true;
    }

    private boolean isFirstMove() {
        for (int i = 1; i <= GameBoard.SIZE_GRID; i++) {
            for (int j = 1; j <= GameBoard.SIZE_GRID; j++) {
                try {
                    if (!gameBoard.cell(i, j).isEmpty()) {
                        return false;
                    }
                } catch (IndexOutOfBoardException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    private void shuffleBagOfTiles() {
        this.bagOfTiles.shuffle();
    }

    private void exchangeTiles(Player player) {
        Rack rack = player.rack();
        
        Dialog<List<Integer>> dialog = new Dialog<>();
        dialog.setTitle("Choix des jetons à changer");
        dialog.setHeaderText("Sélectionnez les indices des jetons à échanger");

        ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        VBox choicesVb = new VBox();
        choicesVb.setSpacing(10);

        List<CheckBox> checkBoxes = new ArrayList<>();
        for (int i = 1; i <= rack.size(); i++) {
            CheckBox checkBox = new CheckBox(formatTile(rack.tile(i-1)));
            checkBoxes.add(checkBox);
            choicesVb.getChildren().add(checkBox);
        }

        dialog.getDialogPane().setContent(choicesVb);

        dialog.setResultConverter(new Callback<ButtonType, List<Integer>>() {
            @Override
            public List<Integer> call(ButtonType dialogButton) {
                if (dialogButton == okButtonType) {
                    List<Integer> selectedIndices = new ArrayList<>();
                    for (int i = 0; i < checkBoxes.size(); i++) {
                        if (checkBoxes.get(i).isSelected()) {
                            selectedIndices.add(i);
                        }
                    }
                    return selectedIndices;
                }
                return Collections.emptyList();
            }
        });

        Optional<List<Integer>> result = dialog.showAndWait();

        if (result.isPresent()) {
        	List<Integer> selectedIndices = result.get();
        	Collections.sort(selectedIndices, Collections.reverseOrder());
        	for (Integer indice : selectedIndices) {
        		bagOfTiles.add(rack.removeTile(indice));
        	}
        	if (!selectedIndices.isEmpty()) {
        		lastExchangeTurn=roundCounter.get();
        	}
        }
        fillPlayerRack(player);
        rackFX.updateRack();
    }
    
    private boolean isAdjacentToExistingWord(int row, int col) {
        try {
            return (!gameBoard.cell(row - 1, col).isEmpty() || !gameBoard.cell(row + 1, col).isEmpty() ||
                    !gameBoard.cell(row, col - 1).isEmpty() || !gameBoard.cell(row, col + 1).isEmpty());
        } catch (IndexOutOfBoardException e) {
            return false;
        }
    }
    
    public static List<List<Cell>> findWordsFromMoves(List<Move> moves, GameBoard gameBoard) throws IndexOutOfBoardException {
        List<List<Cell>> words = new ArrayList<>();

        for (Move move : moves) {
            List<Cell> horizontalWord = getWord(move, gameBoard, true);
            if (horizontalWord.size() > 1 && !words.contains(horizontalWord)) {
                words.add(horizontalWord);
            }

            List<Cell> verticalWord = getWord(move, gameBoard, false);
            if (verticalWord.size() > 1 && !words.contains(verticalWord)) {
                words.add(verticalWord);
            }
        }

        return words;
    }

    private static List<Cell> getWord(Move move, GameBoard gameBoard, boolean isHorizontal) throws IndexOutOfBoardException {
        List<Cell> word = new ArrayList<>();
        int row = move.row();
        int col = move.col();

        while ((isHorizontal ? col > 1 : row > 1) && 
               !gameBoard.cell(isHorizontal ? row : row - 1, isHorizontal ? col - 1 : col).isEmpty()) {
            if (isHorizontal) {
                col--;
            } else {
                row--;
            }
        }

        while ((isHorizontal ? col <= GameBoard.SIZE_GRID : row <= GameBoard.SIZE_GRID) && 
               !gameBoard.cell(row, col).isEmpty()) {
            word.add(gameBoard.cell(row, col));
            if (isHorizontal) {
                col++;
            } else {
                row++;
            }
        }
        return word;
    }
    
    private String formatTile(Tile tile) {
        String[] subscriptDigits = {"\u2080", "\u2081", "\u2082", "\u2083", "\u2084", "\u2085", "\u2086", "\u2087", "\u2088", "\u2089", "\u2081\u2080"};
        if (tile.isJoker()) {
            return String.format("%-3s", tile.letter() + subscriptDigits[0]);
        } else {
            return String.format("%-3s", tile.letter() + subscriptDigits[tile.letter().value()]);
        }
    }
	
	public static void main(String[] args) {
		launch(args);
	}

}
