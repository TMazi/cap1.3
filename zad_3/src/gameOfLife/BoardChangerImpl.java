package gameOfLife;

import java.util.ArrayList;
import java.util.List;

public class BoardChangerImpl implements BoardChanger {

	boolean[][] startBoard;

	public BoardChangerImpl(boolean[][] startBoard) {
		this.startBoard = startBoard;
	}

	@Override
	public boolean[][] giveNewBoard() {
		boolean[][] result = startBoard;
		List<Tuple> toDie = findThoseToDie();
		List<Tuple> toRevive = findThoseToRevive();
		for (Tuple tup : toDie) {
			result[tup.getX()][tup.getY()] = false;
		}
		for (Tuple tup : toRevive) {
			result[tup.getX()][tup.getY()] = true;
		}
		return result;

	}

	private List<Tuple> findThoseToRevive() {

		List<Tuple> result = new ArrayList<Tuple>();
		for (int i = 0; i < startBoard.length; i++) {
			for (int j = 0; j < startBoard[i].length; j++) {
				if (!startBoard[i][j] && shouldRevive(i, j))
					result.add(new Tuple(i, j));
			}
		}
		return result;
	}

	private List<Tuple> findThoseToDie() {

		List<Tuple> result = new ArrayList<Tuple>();
		for (int i = 0; i < startBoard.length; i++) {
			for (int j = 0; j < startBoard[i].length; j++) {
				if (startBoard[i][j] && shouldDie(i, j))
					result.add(new Tuple(i, j));
			}
		}
		return result;

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

	private int checkNeighbours(int i, int j) {
		int count = 0;
		if (i == 0) {
			if (j == 0) {
				Tuple[] neighbours = { new Tuple(i + 1, j), new Tuple(i, j + 1), new Tuple(i + 1, j + 1) };
				count = countAliveNeighbours(neighbours);
			} else if (j == startBoard[i].length - 1) {
				Tuple[] neighbours = { new Tuple(i + 1, j), new Tuple(i, j - 1), new Tuple(i + 1, j - 1) };
				count = countAliveNeighbours(neighbours);
			} else {
				Tuple[] neighbours = { new Tuple(i, j + 1), new Tuple(i, j - 1), new Tuple(i + 1, j),
						new Tuple(i + 1, j + 1), new Tuple(i + 1, j - 1) };
				count = countAliveNeighbours(neighbours);
			}
		} else if (i == startBoard.length - 1) {
			if (j == 0) {
				Tuple[] neighbours = { new Tuple(i - 1, j), new Tuple(i, j + 1), new Tuple(i - 1, j + 1) };
				count = countAliveNeighbours(neighbours);
			} else if (j == startBoard[i].length - 1) {
				Tuple[] neighbours = { new Tuple(i - 1, j), new Tuple(i, j - 1), new Tuple(i - 1, j - 1) };
				count = countAliveNeighbours(neighbours);
			} else {
				Tuple[] neighbours = { new Tuple(i - 1, j + 1), new Tuple(i - 1, j), new Tuple(i - 1, j - 1),
						new Tuple(i, j - 1), new Tuple(i, j + 1) };
				count = countAliveNeighbours(neighbours);
			}
		} else if (j == 0) {
			Tuple[] neighbours = { new Tuple(i - 1, j), new Tuple(i + 1, j), new Tuple(i - 1, j + 1),
					new Tuple(i + 1, j + 1), new Tuple(i, j + 1) };
			count = countAliveNeighbours(neighbours);
		} else if (j == startBoard[i].length - 1) {
			Tuple[] neighbours = { new Tuple(i - 1, j), new Tuple(i + 1, j), new Tuple(i - 1, j - 1),
					new Tuple(i + 1, j - 1), new Tuple(i, j - 1) };
			count = countAliveNeighbours(neighbours);
		} else {
			Tuple[] neighbours = { new Tuple(i - 1, j + 1), new Tuple(i + 1, j + 1), new Tuple(i, j + 1),
					new Tuple(i + 1, j - 1), new Tuple(i + 1, j), new Tuple(i - 1, j), new Tuple(i, j - 1),
					new Tuple(i - 1, j - 1) };
			count = countAliveNeighbours(neighbours);
		}
		return count;
	}

	private int countAliveNeighbours(Tuple[] neighbours) {
		int count = 0;
		for (Tuple tup : neighbours) {
			if (startBoard[tup.getX()][tup.getY()])
				count++;
		}
		return count;
	}

}