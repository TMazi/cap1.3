package gameOfLife;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for generating new board for
 * next iteration during game of life
 * @author TMAZUREK
 *
 */

public class BoardChangerImpl implements BoardChanger {

	boolean[][] startBoard;
	private int changeNumber;

	public BoardChangerImpl(boolean[][] startBoard) {
		changeNumber = 0;
		this.startBoard = startBoard;
	}

	/**
	 * Method that takes current state of board and generates
	 * new board through changing cells state for new one.
	 * @return board after iteration
	 */
	
	@Override
	public boolean[][] giveNewBoard() {
		boolean[][] transformedBoard = startBoard;
		List<Tuple> toDie = findThoseToDie();
		List<Tuple> toRevive = findThoseToRevive();
		changeNumber += toRevive.size();
		changeNumber -= toDie.size();
		for (Tuple tup : toDie) {
			transformedBoard[tup.getX()][tup.getY()] = false;
		}
		for (Tuple tup : toRevive) {
			transformedBoard[tup.getX()][tup.getY()] = true;
		}
		return transformedBoard;

	}

	private List<Tuple> findThoseToRevive() {

		List<Tuple> revivedCells = new ArrayList<Tuple>();
		for (int i = 0; i < startBoard.length; i++) {
			for (int j = 0; j < startBoard[i].length; j++) {
				if (!startBoard[i][j] && shouldRevive(i, j))
					revivedCells.add(new Tuple(i, j));
			}
		}
		return revivedCells;
	}

	private List<Tuple> findThoseToDie() {

		List<Tuple> dyingCells = new ArrayList<Tuple>();
		for (int i = 0; i < startBoard.length; i++) {
			for (int j = 0; j < startBoard[i].length; j++) {
				if (startBoard[i][j] && shouldDie(i, j))
					dyingCells.add(new Tuple(i, j));
			}
		}
		return dyingCells;

	}

	private boolean shouldRevive(int i, int j) {
		int count = 0;
		count = checkNeighbours(i, j);
		return (count == 3);
	}

	private boolean shouldDie(int i, int j) {
		int count = 0;
		count = checkNeighbours(i, j);
		return (count < 2 || count > 3);
	}
	
	/**
	 * Method returning number of alive neighbors around
	 * the [i;j] cell
	 * @param i row of cell
	 * @param j	column of cell
	 * @return number of live neighbors
	 */
	
	private int checkNeighbours(int i, int j) {

		return checkThisNeighbour(i-1, j-1) + checkThisNeighbour(i-1, j) + checkThisNeighbour(i-1, j+1) +
				checkThisNeighbour(i, j-1) + checkThisNeighbour(i, j+1) + checkThisNeighbour(i+1, j) +
				checkThisNeighbour(i+1, j+1) + checkThisNeighbour(i+1, j-1); 
	}
	
	private int checkThisNeighbour(int i, int j) {
		int result = 0;
		try {
			if(startBoard[i][j])
				result++;
		}
		catch(ArrayIndexOutOfBoundsException e) {
			
		}
		return result;
	}

	@Override
	public int aliveChangeNumber() {
		return changeNumber;
	}

}