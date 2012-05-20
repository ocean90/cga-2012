package sorting;

/**
 * Diese Klasse dient dazu, moeglichst einfach den Ablauf eines
 * Sortierverfahrens ueber Kontrollausgaben verstaendlich zu machen.
 */
public class Tracer {
	private static String prompt;
	private static boolean traceOn = false;

	/**
	 * Speichert den einleitenden Text fuer die Meldungen.
	 * Gleichzeitig wird mit dem 2. Parameter festgelegt, ob ueberhaupt
	 * Tracemeldungen geschrieben werden sollen.
	 *
	 * @param prompt Beschreibender Text
	 * @param traceOn true wenn Meldungen geschrieben werden sollen.
	 */
	public static void setPrompt(String prompt, boolean traceOn) {
		Tracer.prompt = prompt + ": ";
		Tracer.traceOn = traceOn;
	}

	/**
	 * Ausgabe eines gesamten Arrays.
	 *
	 * @param array auszugebendes Array
	 */
	public static void array(int[] array) {
		if (traceOn)
			interval(array, 0, array.length);
	}

	/**
	 * Ausgabe eines Arrays und Markierung eines Teilbereichs
	 *
	 * @param array auszugeendes Array
	 * @param lo Beginn des Teilbereichs
	 * @param hi Ende des Teilbereichs (letzter Index +1)
	 */
	public static void interval(int[] array, int lo, int hi) {
		if (traceOn)
			log(arrayToString(array, lo, hi));
	}

	/**
	 * Arrayausgabe bei einem direkten Sortierverfahren.
	 *
	 * @param array ausgebendes Array.
	 * @param mid markiert die Grenze zwischen sortiertem und unsortiertem
	 * Bereich.
	 */
	public static void direct(int[] array, int mid) {
		if (traceOn)
			log(arrayToString(array, mid));
	}

	/**
	 * Arrayausgabe fuer eines der rekursiven Sortierverfahren.
	 *
	 * @param array auszugebendes Array
	 * @param lo untere Bereichsgrenze
	 * @param mid Grenze der rekursiven Unterteilung
	 * @param hi obere Bereichsgrenze (letzter Index + 1)
	 */
	public static void recursive(int[] array, int lo, int mid, int hi) {
		if (traceOn)
			log(arrayToString(array, lo, mid, hi));
	}



	/**
	 * Stellt den Sortierzustand eines Feldes durch einen String dar.
	 * Das gesamte Feld wird ausgegeben. Es ist markiert wie weit
	 * der Teilsortierte Bereich reicht.
	 * Fuer lineare Algorithmen.
	 * @param a Feld.
	 * @param mid Position der Feldaufteilung.
	 * @return Stringdarstellung fuer das Feld.
	 */
	private static String arrayToString(int a[], int mid) {
		verify(0 <= mid && mid <= a.length);
		StringBuilder b = new StringBuilder(80);
		b.append("[ ");
		buildString(b, a, 0, mid);
		b.append("| ");
		buildString(b, a, mid, a.length);
		b.append(" ] ");
		return b.toString();
	}


	private static void verify(boolean condition) {
		if (!condition)
			throw new IllegalArgumentException();
	}

	/**
	 * Stellt den Sortierzustand eines Feldes durch einen String dar.
	 * @param a Feld.
	 * @param lo untere Bereichsgrenze.
	 * @param mid Position der Feldaufteilung.
	 * @param hi obere Bereichsgrenze.
	 * @return Stringdarstellung fuer das Feld.
	 */
	private static String arrayToString(int a[], int lo, int mid, int hi) {
		verify(0 <= lo && lo <= mid && mid <= hi && hi <= a.length);
		StringBuilder b = new StringBuilder(80);
		if (lo > a.length-1) lo = a.length-1;
		buildString(b, a, 0, lo);
		b.append("[ ");
		buildString(b, a, lo, mid);
		b.append("| ");
		buildString(b, a, mid, hi);
		if (hi < a.length) b.append("] ");
		buildString(b, a, hi, a.length);
		if (hi >= a.length) b.append(" ]");
		return b.toString();
	}

	/**
	 * Gibt einen Feldbereich aus.
	 * @param a Feld.
	 * @param lo untere Bereichsgrenze.
	 * @param hi obere Bereichsgrenze.
	 * @return Stringdarstellung fuer das Feld.
	 */
	private static String arrayToString(int a[], int lo, int hi) {
		verify(0 <= lo && lo <= hi && hi <= a.length);
		StringBuilder b = new StringBuilder(80);
		if (lo > a.length-1) lo = a.length-1;
		buildString(b, a, 0, lo);
		b.append("[ ");
		buildString(b, a, lo, hi);
		if (hi < a.length) b.append("] ");
		buildString(b, a, hi, a.length);
;       if (hi >= a.length) b.append(" ]");
		return b.toString();
	}

	private static void buildString(StringBuilder b, int[] a, int lo, int hi) {
		for (int i = lo; i < hi; i++) {
			b.append(String.valueOf(a[i]));
			if (hi - i > 1) b.append(", ");
		}
	}

	private static void log(String msg) {
		System.out.println(prompt + ": " + msg);
	}
}
