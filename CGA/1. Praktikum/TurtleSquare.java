package praktikum.eins;

import java.util.Scanner;

import org.amcgala.TurtleMode;

/**
 * Ein Beispiel für die Verwendung der Turtleklasse und der Funktionalitäten, die sie bietet.
 *
 * @author Robert Giacinto
 */
public class TurtleSquare extends TurtleMode {
	private static double anzahl = 0;
	private static Scanner in = new Scanner(System.in);

	@Override
	public void turtleCommands() {
		double length = 300;

		gotoStart(length);

		while(length > 50){
			newSquare(length);
			gap(25);
			length -= 50;
			turnLeft(5);
		}
	}

	private void gap(double length) {
		up();
		move(length);
		turnRight(90);
		move(length);
		turnLeft(90);
		down();
	}

	private void gotoStart(double width){
		up();
		turnLeft(180);
		move(width/2);
		turnRight(90);
		move(width/2);
		turnRight(90);
		down();
	}

	private void newSquare(double length) {
		for (int i = 0; i < anzahl; i++) {
			move(length);
			turnRight(360/anzahl);
		}
	}

	public static void main(String[] args) {
		System.out.print("Anzahl Ecken: ");
		anzahl = in.nextDouble();

		new TurtleSquare();
	}
}
