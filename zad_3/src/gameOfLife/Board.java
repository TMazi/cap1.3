package gameOfLife;

public interface Board {
	
	void display();
	void transform();
	boolean[][] getBoard();
	public int getAliveCount();
	public int getIteration();

}
