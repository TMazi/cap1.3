package gameOfLife;

import java.util.Scanner;

/*
 * Class used to show example working of game of life
 */

public class Runner {

	final static int DEFAULT_DIMENSION = 40;
	final static float DEFAULT_LIFE_CHANCE = 0.3F;
	static Scanner sc;

	public static void main(String[] args) {

		Board board = new BoardImpl(DEFAULT_DIMENSION, DEFAULT_LIFE_CHANCE);

		System.out.println("Wybierz tryb: ");
		System.out.println("1) Automatyczny");
		System.out.println("2) Manualny");
		sc = new Scanner(System.in);
		String result = sc.nextLine();
		board.display();

		if (result.equals("1")) {
			while (true) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				board.transform();
				board.display();
				
			}
		} else if (result.equals("2")) {
			while (true) {
				if (sc.nextLine().equals("")) {
					board.transform();
					board.display();

				}
			}
		}
	}

}
