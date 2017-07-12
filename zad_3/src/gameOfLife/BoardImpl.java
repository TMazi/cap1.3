package gameOfLife;

import java.util.Random;

public class BoardImpl implements Board {

	private boolean[][] board;
	private float lifeChance;
	private BoardChanger bci;
	private int alivecount, iteration;


	public BoardImpl(int dimension, float lifeChance) {
		alivecount = 0;
		iteration = 0;
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
				if (rnd.nextFloat() <= lifeChance) {
					board[i][j] = true;
					alivecount++;
				}
				else
					board[i][j] = false;
			}
		}
	}
	
	public void transform() {
		bci = new BoardChangerImpl(board);
		board = bci.giveNewBoard();
		iteration++;
		alivecount += bci.aliveChangeNumber();
	}

	
	public void display() {
		System.out.print("Iteracja: " + iteration + "\nZywi: " + alivecount +"\n");
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
