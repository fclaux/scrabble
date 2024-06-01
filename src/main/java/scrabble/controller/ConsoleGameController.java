package scrabble.controller;

import java.util.ArrayList;
import java.util.List;

import scrabble.gui.Console;
import scrabble.model.BagOfTiles;
import scrabble.model.Cell;
import scrabble.model.Direction;
import scrabble.model.GameBoard;
import scrabble.model.Letters;
import scrabble.model.Move;
import scrabble.model.Player;
import scrabble.model.Rack;
import scrabble.model.Tile;
import scrabble.util.EmptyBagException;
import scrabble.util.IndexOutOfBoardException;
import scrabble.util.ScoreCounter;
import scrabble.view.console.ConsoleGameBoardView;
import scrabble.view.console.ConsoleMenuView;
import scrabble.view.console.ConsoleRackView;
import scrabble.view.interfaces.GameBoardView;
import scrabble.view.interfaces.RackView;

public class ConsoleGameController {
    private Player player;
    private GameBoard gameBoard;
    private BagOfTiles bagOfTiles;
    private ConsoleMenuView menuView;
    private GameBoardView gameBoardView;
    private RackView rackView;
    private boolean firstExchange = true;
    private ScoreCounter scoreCounter;

    private static final int STOP_VALUE = 0;

    public ConsoleGameController() {
        this.player = new Player("Joueur 1");
        this.gameBoard = new GameBoard();
        this.bagOfTiles = new BagOfTiles();
        this.menuView = new ConsoleMenuView();
        this.gameBoardView = new ConsoleGameBoardView();
        this.rackView = new ConsoleRackView();
        this.scoreCounter = new ScoreCounter();
    }

    public void startGame() throws IndexOutOfBoardException {
        Player player1 = this.player;

        shuffleBagOfTiles();
        fillPlayerRack(player1);
        boolean running = true;
        while (running && !player1.rack().isEmpty()) {
            menuView.displayMenu(this.gameBoard, player1.rack());
            int choice = Console.askInt(1,3);
            switch (choice) {
                case 1:
                    play();
                    fillPlayerRack(player1);
                    break;
                case 2:
                    if (this.firstExchange) {
                        this.firstExchange = false;
                        exchangeTiles(player1);
                    } else {
                        Console.message("Vous avez déjà échangé des tuiles ce tour, choisissez une autre option", true);
                    }
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    Console.message("Choix invalide. Veuillez réessayer.", true);
            }
        }
        Console.message("Merci d'avoir joué !", true);
    }



    private void play() throws IndexOutOfBoardException {
        gameBoardView.display(gameBoard);

        List<Move> playerMoves = getPlayerMoves();
        
        applyMoves(playerMoves);
    }

    private void applyMoves(List<Move> playerMoves) throws IndexOutOfBoardException {
        if (isMovesValid(playerMoves)) {
            Console.message("Votre coup est valide ! ", true);
            this.firstExchange = true;
            addTilesToBoard(playerMoves);
            int score = scoreCounter.calculateScoreForMoves(playerMoves, gameBoard);
            player.addScore(score);
            Console.message("Score total :" + player.score(), true);
            Console.message("Score pour ce coup :" + score, true);
        } else {
            Console.message("Votre coup n'est pas valide ! ", true);
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

    private List<Move> getPlayerMoves() throws IndexOutOfBoardException {
        int row;
        int col;
        int nbLetters = 0;
        Rack rack = this.player.rack();
        List<Move> moves = new ArrayList<>();
        Console.title("Tour du joueur " + player.name());
        Tile tile = answerTile(rack);
        if (tile != null) {
            Console.message("Veuillez entrer la ligne : ", false);
            row = Console.askInt(1, GameBoard.SIZE_GRID);
            Console.message("Veuillez entrer la colonne : ", false);
            col = Console.askInt(1, GameBoard.SIZE_GRID);

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
        return Console.askDirection();

    }
    
    public int jumpOverATile(Cell cell, int nbLetters) {
         return nbLetters += 1;
        
    }
    
    private void placeATile(List<Move> moves, Direction direction, int row, int col, int nbLetters, Tile tile) throws IndexOutOfBoardException {
    	if (direction == Direction.HORIZONTAL) {
            Cell cell = gameBoard.cell(row, col + nbLetters);
            while(!cell.isEmpty()) {
                nbLetters = jumpOverATile(cell,nbLetters);
                cell = gameBoard.cell(row + nbLetters, col);
            }       
            
            moves.add(new Move(row, col + nbLetters, tile));

        } else {
        	
            Cell cell = gameBoard.cell(row + nbLetters, col);
            
            while(!cell.isEmpty()) {
                nbLetters = jumpOverATile(cell,nbLetters);
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
        this.rackView.display(rack);
        Console.message("Veuillez entrer l'indice de la tuile : ", false);
        int indice = Console.askInt(STOP_VALUE, rack.size());

        if (indice != STOP_VALUE) {
            Tile tile = rack.removeTile(indice - 1);

            if (tile.isJoker()) {
                char jokerLetter = Console.askJokerLetter();
                tile.letter(Letters.valueOf(String.valueOf(jokerLetter)));
            }
            return tile;
        }
        return null;
    }

    private boolean isMovesValid(List<Move> playerMoves) {
        boolean tileOnStars = false;
        boolean tileOnOccupiedCell = false;
        boolean allInSameRow = true;
        boolean allInSameCol = true;
        boolean haveNearTile = false;


        if (playerMoves.isEmpty()) {
            Console.message("vous devez jouer au moins 1 lettres si elle est adjacente à un mot déjà existant", true);
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
                    Console.message("Une cellule (" + row + " , " + col + ") est déjà occupée", true);
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
            Console.message("C'est le premier tour et vous ne jouez pas sur l'étoile", true);
            return false;
        }
        

        if (!isFirstMove() && !haveNearTile) {
            Console.message("C'est pas le premier tour et aucune tuile n'est connectée", true);
            return false;
        }

        
        if (isFirstMove() && playerMoves.size() == 1) {
        	Console.message("Vous devez placer au moin 2 tuiles lors du premier coup de la partie", true);
        	return false;
        }

        if (tileOnOccupiedCell) {
            Console.message("Vous voulez placer une tuile sur un espace occupé", true);
            return false;
        }

        if (!(allInSameRow || allInSameCol)) {
            Console.message("Les tuiles doivent être alignées dans le même sens, soit vers le bas, soit vers la droite", true);
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

    private void fillPlayerRack(Player player) {
        Rack playerRack = player.rack();
        try {
            while (!playerRack.isFull() && bagOfTiles.drawTile() != null) {
                Tile tile = bagOfTiles.drawTile();
                if (tile != null) {
                    playerRack.addTile(tile);
                }
            }
        } catch (EmptyBagException e) {
            e.printStackTrace();
        }
    }

    private void exchangeTiles(Player player) {
        Rack rack = player.rack();
        Integer input;
        do {
            Console.message("Rack de " + player.name() + " : ", false);
            this.rackView.display(rack);
            Console.message("Indice de la tuile à remplacer (" + STOP_VALUE + ") pour arrêter : ", false);
            input = Console.askInt(STOP_VALUE, rack.size());
            if (input != STOP_VALUE) {
                Tile tile = rack.removeTile(input - 1);
                this.bagOfTiles.add(tile);
            }
        } while ((input != STOP_VALUE) && (!rack.isEmpty()));
        fillPlayerRack(player);
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

        while ((isHorizontal ? col > 0 : row > 0) && 
               !gameBoard.cell(isHorizontal ? row : row - 1, isHorizontal ? col - 1 : col).isEmpty()) {
            if (isHorizontal) {
                col--;
            } else {
                row--;
            }
        }

        while ((isHorizontal ? col < GameBoard.SIZE_GRID : row < GameBoard.SIZE_GRID) && 
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
    
    
}
