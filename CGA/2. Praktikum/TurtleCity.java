package praktikum.zwei;

import org.amcgala.TurtleMode;

/**
 * CGA Praktikumsaufgabe 2
 *
 * Straßenszene.
 *
 * @author  Dennis Meyer
 *          Dominik Schilling
 *
 */
public class TurtleCity extends TurtleMode{
	/**
	 * Höhe der Szene.
	 */
	private static int winHeight = 600;

	/**
	 * Breite der Szene.
	 */
	private static int winWidht = 800;

	/**
	 * Start.
	 */
	@Override
	public void turtleCommands() {
		reset();
		drawStreet();
		drawBuildings();
	}

	/**
	 * Legt den Startpunkt in die obere linke Ecke.
	 */
	public void reset(){
		up();
		turnLeft(180);
		move(winWidht/2);
		turnRight(90);
		move(winHeight/2);
		turnRight(90);
		down();
	}

	/**
	 * Zeichnet die statische Straße.
	 */
	public void drawStreet(){
		// Obere Begrenzung
		up();
		turnRight(90);
		move(400);
		turnLeft(90);
		down();
		move(winWidht);

		// Untere Begrenzung
		up();
		turnRight(90);
		move(100);
		turnRight(90);
		down();
		move(winWidht);

		// Zur Mitte
		up();
		turnRight(90);
		move(50);
		turnRight(90);

		// Zeichne Mittellinien
		int strichLaenge = 30;
		int gesamt = 0;
		while(gesamt < winWidht){
			move(strichLaenge);
			down();
			move(strichLaenge);
			turnLeft(90);
			move(1);
			turnLeft(90);
			move(strichLaenge);
			turnLeft(90);
			move(1);
			turnLeft(90);
			move(strichLaenge);
			up();
			gesamt += strichLaenge*2;
		}

		// Ausgangsposition Oben Links
		turnLeft(180);
		move(gesamt);
		turnRight(90);
		move(400);
		turnRight(90);
		down();
	}

	/**
	 * Häuser vorbereiten
	 *  * Anzahl
	 *  * Breite
	 *  * Abstand
	 */
	public void drawBuildings() {
		// Abstand zwischen Häuser
		int gap = random( 10,30 );

		// Anzahl der Häuser
		int anzahlHaeuser = random( 2, 5 );

		// Breite eines Hauses
		double hausBreite = (winWidht - ( (anzahlHaeuser + 1) * gap )) / anzahlHaeuser;

		// Ausgangsposition 1. Haus unten links
		up();
		turnRight(90);
		move(325);
		turnLeft(90);
		move(gap);

		// Jedes Haus zeichnen
		int i = anzahlHaeuser;
		while( i > 0) {
			// Soll das Haus gezeichnet werden?
			int show = 1;//random(0,4);
			if ( show > 0 ) {
				// Haushöhe bestimmen
				int hausHoehe = random(100, 320);

				drawBuilding(hausBreite, hausHoehe);

			}

			// Häuserabstand
			up();
			move(hausBreite+gap);
			i--;
		}
	}

	/**
	 * Ein Haus zeichen
	 * @param breite Breite des Hauses
	 * @param hoehe  Höhe des Hauses
	 */
	public void drawBuilding(double breite, double hoehe) {
		// Anzahl der Etagen pro Haus
		int anzahlEtagen = random(2,8);

		// Höhe einer Etage
		double etagenHoehe = (hoehe / anzahlEtagen);

		// Etagen zeichnen
		int i = anzahlEtagen;
		while( i > 0) {
			drawRectangle(etagenHoehe,breite);

			// Fenster zeichnen
			// (Nicht wenn erster Stock)
			if ( i != anzahlEtagen )
				drawWindows(etagenHoehe,breite);

			// Ausgangsposition nächsten Etage unten links
			up();
			turnRight(90);
			move(etagenHoehe);
			turnRight(90);

			i--;
		}

		// Ausgangsposition Haus unten links
		turnRight(90);
		move(hoehe);
		turnLeft(90);

		// Tür zeichnen
		drawDoor(etagenHoehe,breite);
	}

	/**
	 * Fenster pro Etage zeichnen.
	 *
	 * @param etagenHoehe Höhe der Etage
	 * @param etagenBreite Breite der Etage
	 */
	public void drawWindows( double etagenHoehe, double etagenBreite) {
		// Anzahl der Fenster pro Etage
		int anzahlFenster = random(1, 6);

		// Abstand zwischen Fenster und Fenster bzw Fenster und Wand
		double fensterGap = 10;

		// Höhe eines Fensters
		double fensterHoehe = etagenHoehe - 2 * fensterGap;

		// Breite eines Fensters
		double fensterBreite = (etagenBreite - ( fensterGap * (anzahlFenster +1))) / anzahlFenster;

		// Ausgangsposition 1. Fenster unten links
		up();
		turnRight(90);
		move(fensterGap);
		turnRight(90);
		move(fensterGap);

		// Fenster zeichnen
		int i = anzahlFenster;
		while ( i > 0 ) {
			// Soll das Fenster gezeichnet werden?
			int show = random(0,3);

			// Fenster zeichen, wenn show 1 oder 2
			if ( show > 0 ) {
				// 1. Rahmen
				drawRectangle(fensterHoehe, fensterBreite);

				// 2. Rahmen
				up();
				turnLeft(180);
				move(1);
				turnLeft(90);
				move(1);
				turnRight(90);
				drawRectangle(fensterHoehe-2, fensterBreite-2);
				up();
				move(1);
				turnLeft(90);
				move(1);
				turnLeft(90);
			}

			// Ausgangsposition nächstes Fenster unten links
			up();
			move(fensterBreite + fensterGap);

			i--;
		}

		// Ausgangsposition Etage unten links
		turnRight(90);
		move(fensterGap);
		turnRight(90);
		move(etagenBreite);
	}

	/**
	 * Zeichnet eine Tür für 1. Etage.
	 * Höhe = Etagenhöhe / 2
	 *
	 * @param etagenHoehe Höhe der Etage
	 * @param breiteHaus Breite der Etage
	 */
	public void drawDoor(double etagenHoehe, double breiteHaus) {
		// Breite der Tür bestimmemn
		int breiteTuer = random(25, 50);

		// Ausgangsposition Tür unten links
		up();
		move( breiteHaus/2 -(breiteTuer/2));

		// Tür zeichnen
		drawRectangle(etagenHoehe/2,breiteTuer);

		turnLeft(180);
		down();

		// Tür füllen
		int i = breiteTuer - 1;
		boolean gerade = false;
		while( i > 0 ) {
			// Nur wenn gerade = true,
			if ( ! gerade ) {
				move(1);
				turnLeft(90);
				move(etagenHoehe/2);
				turnRight(90);
				move(1);
				turnRight(90);
				move(etagenHoehe/2);
				turnLeft(90);
			}

			gerade = ! gerade;
			i--;
		}


		// Ausgangsposition Etage unten links
		up();
		turnLeft(180);
		move((breiteHaus/2) + breiteTuer/2);
		turnLeft(180);

	}

	/**
	 * Helfermethode zum Zeichnen eines Rechtecks.
	 *
	 * @param hoehe Höhe des Rechtecks
	 * @param breite Breite des Rechtecks
	 */
	private void drawRectangle(double hoehe, double breite) {
		down();
		turnLeft(90);
		move(hoehe);
		turnRight(90);
		move(breite);
		turnRight(90);
		move(hoehe);
		turnRight(90);
		move(breite);
	}

	/**
	 * Helfermethode für eine zufällige Zahl zwischen einem
	 * Maximum und Minimum.
	 *
	 * @param min Minimum
	 * @param max Maximum
	 * @return zufällig Zahl
	 */
	private int random(int min, int max) {
		return (int) (Math.random() * (max-min)	+ min);
	}

	/**
	 * Initialisieren.
	 */
	public static void main(String[] args) {
		new TurtleCity();
	}
}