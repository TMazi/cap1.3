package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import gameOfLife.Board;
import gameOfLife.BoardImpl;

public class BoardTest {

	@Test
	public void boardShouldBeAllDead() {

		// given
		Board board = new BoardImpl(4, 0);

		// when
		boolean result = false;
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[i].length; j++) {
				if (board.getBoard()[i][j])
					result = true;
			}
		}

		// then
		assertFalse(result);
	}

	@Test
	public void shouldBeAllAlive() {
		// given
		Board board = new BoardImpl(4, 1);

		// when
		boolean result = true;
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[i].length; j++) {
				if (!board.getBoard()[i][j])
					result = false;
			}
		}

		// then
		assertTrue(result);
	}

	@Test
	public void checkBoardAfterTwoIterations() {

		// given
		Board board = new BoardImpl(4, 0);

		// when
		board.transform();
		board.transform();

		// then
		assertTrue(board.getIteration() == 2);
	}

}
