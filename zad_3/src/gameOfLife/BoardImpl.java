package gameOfLife;

import java.util.Random;

public class BoardImpl implements Board {

	private boolean[][] board;
	private float lifeChance;
	private BoardChanger bci;


	public BoardImpl(int dimension, float lifeChance) {
		this.lifeChance = lifeChance;
		board = new boolean[dimension][dimension];
		initialize();
	}
	
	public boolean[][] getBoard() {
		return board;
	}
	
	private void initialize() {
		Random rnd = new Random();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (rnd.nextFloat() > lifeChance)
					board[i][j] = true;
				else
					board[i][j] = false;
			}
		}
	}
	
	public void transform() {
		bci = new BoardChangerImpl(board);
		board = bci.giveNewBoard();
	}

	
	public void display() {
		for(int i = 0; i < board.length; i++) {
			System.out.print("[ ");
			for(int j = 0; j < board[i].length; j++ ) {
				if(board[i][j])
					System.out.print("L ");
				else
					System.out.print("  ");
			}
			System.out.print("]\n");
		}
	}
}
