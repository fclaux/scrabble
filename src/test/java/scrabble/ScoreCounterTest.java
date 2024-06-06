package scrabble;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrabble.model.GameBoard;
import scrabble.model.Letters;
import scrabble.model.Move;
import scrabble.model.Tile;
import scrabble.util.IndexOutOfBoardException;
import scrabble.util.ScoreCounter;

public class ScoreCounterTest {
	private ScoreCounter scoreCounter;
	private GameBoard gameBoard;
	private ArrayList<Move> motUn;
	private Tile tileA;
	private Tile tileJoker;
	
	
	@BeforeEach
    void set_up() {
        scoreCounter = new ScoreCounter();
        gameBoard = new GameBoard();
		motUn = new ArrayList<Move>();
        
        
        Tile tileA = new Tile(Letters.A);
        Tile tileQ = new Tile(Letters.Q);
        Tile tileB = new Tile(Letters.B);
        Tile tileJoker = new Tile(Letters.JOKER);
        
        Move moveUn = new Move(8, 8, tileA);
        Move moveDeux = new Move(8,9,tileQ);
        Move moveTrois = new Move(8,10,tileB);
        Move moveQuatre = new Move(8,11,tileJoker);

        
		motUn.add(moveUn);
		motUn.add(moveDeux);
		motUn.add(moveTrois);
		motUn.add(moveQuatre);


		
		gameBoard.addTile(tileA, 8, 8);
		gameBoard.addTile(tileQ, 8, 9);
		gameBoard.addTile(tileB, 8, 10);
		gameBoard.addTile(tileJoker, 8, 11);



		

    };
	
	@Test
	void calculateScoreForMovesTestWithoutJoker() throws IndexOutOfBoardException {		
		assertEquals(24,scoreCounter.calculateScoreForMoves(motUn, gameBoard));
	
	}
	
	@Test
	void addScoreForScrabble() throws IndexOutOfBoardException {
		ArrayList<Move> motDeux = new ArrayList<Move>();
		for(int i=0; i<7 ;i++) {
			Move move = new Move(8+i,11,tileA);
			motDeux.add(move);
			gameBoard.addTile(tileA, 8+i, 11);
		}
		assertEquals(74,scoreCounter.calculateScoreForMoves(motDeux, gameBoard));
	}
	
	@Test
	void checkIfJokerValueIsZero() throws IndexOutOfBoardException{
		ArrayList<Move> doubleJoker = new ArrayList<Move>();
		Move move = new Move(9,11,tileJoker);
		doubleJoker.add(move);
		gameBoard.addTile(tileJoker, 9, 11);
		
		assertEquals(0,scoreCounter.calculateScoreForMoves(doubleJoker, gameBoard));
	}
	
	@Test
	void tileOnLetterDoubleCellTest() throws IndexOutOfBoardException{
		ArrayList<Move> motTrois = new ArrayList<Move>();
        Tile tileA = new Tile(Letters.A);

		Move move = new Move(7,9,tileA);
		motTrois.add(move);
		gameBoard.addTile(tileA, 7, 9);
		assertEquals(10,scoreCounter.calculateScoreForMoves(motTrois, gameBoard));
	}
	
	@Test
	void tileOnLetterTrippleCellTest() throws IndexOutOfBoardException{
		ArrayList<Move> motQuatre = new ArrayList<Move>();
        Tile tileA = new Tile(Letters.A);

		Move move = new Move(6,10,tileA);
		motQuatre.add(move);
		gameBoard.addTile(tileA, 6, 10);
		
		move = new Move(7,10,tileA);
		motQuatre.add(move);
		gameBoard.addTile(tileA, 7, 10);
		
		
		assertEquals(7,scoreCounter.calculateScoreForMoves(motQuatre, gameBoard));
	}
	
	@Test
	void tileOnWordDoubleCellTest() throws IndexOutOfBoardException{
		ArrayList<Move> motQuatre = new ArrayList<Move>();
        Tile tileA = new Tile(Letters.A);
		
		for(int i=0; i<3 ;i++) {
			Move move = new Move(4+i,11,tileA);
			motQuatre.add(move);
			gameBoard.addTile(tileA, 4+i, 11);
		}
		
		
		assertEquals(6,scoreCounter.calculateScoreForMoves(motQuatre, gameBoard));
	}
	
	@Test
	void tileOnWordTrippleCellTest() throws IndexOutOfBoardException{
		ArrayList<Move> motQuatre = new ArrayList<Move>();
        Tile tileA = new Tile(Letters.A);
		
		for(int i=0; i<3 ;i++) {
			Move move = new Move(8+i,15,tileA);
			motQuatre.add(move);
			gameBoard.addTile(tileA, 8+i, 15);
		}
		
		
		assertEquals(9,scoreCounter.calculateScoreForMoves(motQuatre, gameBoard));
	}
}
