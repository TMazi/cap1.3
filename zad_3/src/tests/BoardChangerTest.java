package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import gameOfLife.BoardChangerImpl;

public class BoardChangerTest {

	boolean[][] startBoard;
	boolean[][] expectedBoard;

	@Before
	public void setup() {

		startBoard = new boolean[4][4];
		startBoard[0][2] = true;
		startBoard[0][3] = true;
		startBoard[2][0] = true;
		startBoard[1][3] = true; 

	}

	@Test
	public void shouldReturnRightTransformedBoard() {

		// given
		expectedBoard = new boolean[4][4];
		expectedBoard[0][2] = true;
		expectedBoard[0][3] = true;
		expectedBoard[1][2] = true;
		expectedBoard[1][3] = true; 
		BoardChangerImpl changer = new BoardChangerImpl(startBoard);

		// when
		boolean[][] result = new boolean[4][4];
		result = changer.giveNewBoard();

		// then
		assertTrue(compareArrays(result, expectedBoard));

	}
	
	@Test
	public void shouldReturnNumberOfChangedCellsZero() {
		
		//given
		BoardChangerImpl changer = new BoardChangerImpl(startBoard);

		//when
		changer.giveNewBoard();
		int result = changer.aliveChangeNumber();
		
		assertEquals(0, result);
	}
	
	private boolean compareArrays(boolean[][] array1, boolean[][]array2) {
		for(int i = 0; i < array1.length; i++) {
			for(int j = 0; j < array1[i].length; j++) {
				if(array1[i][j] != array2[i][j])
					return false;
			}
		}
		return true;
	}

}
