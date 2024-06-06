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
	private ArrayList<Move> wordOne;
	private Tile tileA;
	
	
	@BeforeEach
    void set_up() {
        scoreCounter = new ScoreCounter();
        gameBoard = new GameBoard();
		wordOne = new ArrayList<Move>();
        
        Tile tileA = new Tile(Letters.A);
        Tile tileQ = new Tile(Letters.Q);
        Tile tileB = new Tile(Letters.B);
        Tile tileJoker = new Tile(Letters.JOKER);
        
        Move moveOne = new Move(8, 8, tileA);
        Move moveTwo = new Move(8,9,tileQ);
        Move moveThree = new Move(8,10,tileB);
        Move moveFour = new Move(8,11,tileJoker);
        
		wordOne.add(moveOne);
		wordOne.add(moveTwo);
		wordOne.add(moveThree);
		wordOne.add(moveFour);
		
		gameBoard.addTile(tileA, 8, 8);
		gameBoard.addTile(tileQ, 8, 9);
		gameBoard.addTile(tileB, 8, 10);
		gameBoard.addTile(tileA, 8, 11);

    };
	
	@Test
	void calculateScoreForMovesWithoutJokerTest() throws IndexOutOfBoardException {		
		assertEquals(26,scoreCounter.calculateScoreForMoves(wordOne, gameBoard));
	}
	
	@Test
	void calculateScoreForMovesWithJokerTest() throws IndexOutOfBoardException{
        Tile tileJoker = new Tile(Letters.JOKER);
		Move moveJoker = new Move(8,7,tileJoker);

        wordOne.add(moveJoker);
        gameBoard.addTile(tileJoker, 8, 7);
        
        assertEquals(26,scoreCounter.calculateScoreForMoves(wordOne, gameBoard));
	}
	
	@Test
	void addScoreForMakeAScrabble() throws IndexOutOfBoardException {
		ArrayList<Move> word = new ArrayList<Move>();
		for(int i=0; i<7 ;i++) {
			Move move = new Move(8+i,11,tileA);
			word.add(move);
			gameBoard.addTile(tileA, 8+i, 11);
		}
		assertEquals(74,scoreCounter.calculateScoreForMoves(word, gameBoard));
	}
	
	@Test
	void checkIfJokerValueIsZero() throws IndexOutOfBoardException{
		ArrayList<Move> doubleJoker = new ArrayList<Move>();
        Tile tileJoker = new Tile(Letters.JOKER);
		
		Move move = new Move(9,11,tileJoker);
		doubleJoker.add(move);
		gameBoard.addTile(tileJoker, 9, 11);
		
		assertEquals(1,scoreCounter.calculateScoreForMoves(doubleJoker, gameBoard));
	}
	
	@Test
	void tileOnCellLetterDoubleTest() throws IndexOutOfBoardException{
		ArrayList<Move> word = new ArrayList<Move>();
        Tile tileA = new Tile(Letters.A);

		Move move = new Move(7,9,tileA);
		word.add(move);
		gameBoard.addTile(tileA, 7, 9);
		assertEquals(10,scoreCounter.calculateScoreForMoves(word, gameBoard));
	}
	
	@Test
	void tileOnCellLetterTrippleTest() throws IndexOutOfBoardException{
		ArrayList<Move> word = new ArrayList<Move>();
        Tile tileA = new Tile(Letters.A);

		Move move = new Move(6,10,tileA);
		word.add(move);
		gameBoard.addTile(tileA, 6, 10);
		
		move = new Move(7,10,tileA);
		word.add(move);
		gameBoard.addTile(tileA, 7, 10);
		
		
		assertEquals(7,scoreCounter.calculateScoreForMoves(word, gameBoard));
	}
	
	@Test
	void jokerOnCellWithLetterMultiplier() throws IndexOutOfBoardException {
        Tile tileJoker = new Tile(Letters.JOKER);
		
		Move moveJoker = new Move(8,12,tileJoker);
        wordOne.add(moveJoker);
        gameBoard.addTile(tileJoker, 8, 12);
        
        assertEquals(26,scoreCounter.calculateScoreForMoves(wordOne, gameBoard));
	}
	
	@Test
	void tileOnWordDoubleCellTest() throws IndexOutOfBoardException{
		ArrayList<Move> word = new ArrayList<Move>();
        Tile tileA = new Tile(Letters.A);
		
		for(int i=0; i<3 ;i++) {
			Move move = new Move(4+i,11,tileA);
			word.add(move);
			gameBoard.addTile(tileA, 4+i, 11);
		}
		
		
		assertEquals(6,scoreCounter.calculateScoreForMoves(word, gameBoard));
	}
	
	@Test
	void tileOnWordTrippleCellTest() throws IndexOutOfBoardException{
		ArrayList<Move> word = new ArrayList<Move>();
        Tile tileA = new Tile(Letters.A);
		
		for(int i=0; i<3 ;i++) {
			Move move = new Move(8+i,15,tileA);
			word.add(move);
			gameBoard.addTile(tileA, 8+i, 15);
		}
		assertEquals(9,scoreCounter.calculateScoreForMoves(word, gameBoard));
	}
	
	@Test
	void jokerOnWordCellEffect() throws IndexOutOfBoardException {
		ArrayList<Move> word = new ArrayList<Move>();
		Tile tileA = new Tile(Letters.A);
        Tile tileJoker = new Tile(Letters.JOKER);

		
		Move move = new Move(1,1,tileJoker);
		word.add(move);
		gameBoard.addTile(tileJoker, 1, 1);
		
		for(int i=0; i<2; i++) {
			move = new Move(1,2+i,tileA);
			word.add(move);
			gameBoard.addTile(tileA, 1, 2+i);
		}
		assertEquals(6,scoreCounter.calculateScoreForMoves(word, gameBoard));
	}
	
	
}
