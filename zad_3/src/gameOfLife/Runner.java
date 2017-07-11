package gameOfLife;

import java.util.Scanner;

public class Runner {

	final static int DEFAULT_DIMENSION = 15;
	final static float DEFAULT_LIFE_CHANCE = 0.6F;
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
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				board.transform();
				System.out.println();
				board.display();
			}
		} else if (result.equals("2")) {
			while (true) {
				if (sc.nextLine().equals("")) {
					board.transform();
					System.out.println();
					board.display();
				}
			}
		}
	}

}
