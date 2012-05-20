package sorting;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;

/**
 * Diese Klasse dient dazu, Sortieralgorithmen zu untersuchen.
 */
public final class TestOfSortingAlgorithms {
	private TestOfSortingAlgorithms() {}

	/**
	 * Dokumentiert die implementierten Sortieralgorithmen, indem die
	 * dort angegebenen Log-Meldungen ausgegeben werden.
	 *
	 * @param array Beispielfeld
	 */
	public static void traceOf(Set<Algorithm> algorithms, int[] array) {
		for (Algorithm a : algorithms) {
			int[] testArray = array.clone();
			Tracer.setPrompt(a.toString(), true);
			a.sort(testArray);
			System.out.println();
		}

	}

	/**
	 * Fuehrt fuer alle definierten Sortierverfahren und alle angegebenen
	 * Feldgroessen eine Laufzeitmessung durch.
	 * Die Sortierverfahren ergeben sich aus der Enum-Klasse Algorithm.
	 *
	 * @param sizes die Feldgroessen, fuer die die Laufzeit ermittelt werden
	 *               soll.
	 */
	public static void performanceOf(Set<Algorithm> algorithms,
			int[] sizes) {
		Tracer.setPrompt(null, false);
		for (int size : sizes) {
			System.out.printf("Zeiten für n = %d%n", size);
			int[] array = randomArray(size);
			for (Algorithm a : algorithms) {
				try {
					System.out.printf("    %s: ", a);
					double time = TestOfSortingAlgorithms.performanceMeasurement(a, array);
					System.out.printf("%.6f%n", time);
				} catch (IllegalStateException e) {
					System.out.println("ist falsch");
				}
			}
		}
	}

	/**
	 * Fuehrt eine Laufzeitmessung fuer das angegebene Sortierverfahren und
	 * das uebergebene Array durch.
	 *
	 * @param algo Sortierverfahren
	 * @param zu sortierendes Feld
	 * @return Laufzeit in Sekunden
	 */
	private static double performanceMeasurement(Algorithm algo, int[] array) {
		Tracer.setPrompt(null, false);
		double time = firstCheck(algo, array);
		int repetitions = Math.max(1, (int) (1.0 / (time+1e-6)));
		if (repetitions == 1)
			return time;
		return repeatedSorting(algo, array, repetitions);
	}

	/**
	 * Ruft einmal das Sortieren auf, prueft ob das Ergebnis korrekt ist und
	 * ermittelt die Laufzeit. Die Laufzeit dient spaeter als Grundlage fuer
	 * die fuer eine genaue Messung noetige Anzahl von Wiederholungen.
	 *
	 * @param algo Sortierverfahren
	 * @param array zu sortierendes Feld
	 * @return Laufzeit
	 * @throws IllegalStateException wenn der Algorithmus falsch ist
	 */
	private static double firstCheck(Algorithm algo, int[] array) {
		int[] testArray = array.clone();
		long startTime = System.nanoTime();
		algo.sort(testArray);
		long endTime = System.nanoTime();
		boolean isCorrect = Arrays.equals(sortedArray(array), testArray);
		if (! isCorrect)
			throw new IllegalStateException("der Algorithmus ist falsch!");
		return 1e-9 * (endTime - startTime);
	}

	/**
	 * Fuehrt eine Performancemessung aus. Damit die Messung bei kurzen
	 * Laufzeiten etwas genauer wird, wird das Sortieren wiederholt ausgefuehrt
	 * und die Gesamtzeit ermittelt.
	 *
	 * @param algo Sortierverfahren.
	 * @param array zu sortierendes Feld
	 * @param repetitions Anzahl der Wiederholungen
	 * @return Zeit pro Sortieren in sek.
	 */
	private static double repeatedSorting(Algorithm algo, int[] array,
			int repetitions) {
		long total = 0;
		for (int i = 0; i < repetitions; i++) {
			int[] testArray = array.clone();
			long startTime = System.nanoTime();
			algo.sort(testArray);
			long endTime = System.nanoTime();
			total += endTime - startTime;
		}
		return 1e-9 * total / repetitions;
	}

	/**
	 * Erstellt mittels der Java-Bibliothek eine sortierte Kopie eines
	 * Arrays.
	 *
	 * @param array Array
	 * @return sortiertes Array
	 */
	private static int[] sortedArray(int[] array) {
		int[] toBeSorted = array.clone();
		Arrays.sort(toBeSorted);
		return toBeSorted;
	}

	private static Random random = new Random();
	/**
	 * Gibt ein mit Zufallszahlen belegtes Array zurueck.
	 *
	 * @param size Arraygoresse
	 * @return Array mit Zufallszahlen.
	 */
	private static int[] randomArray(int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++)
			array[i] = random.nextInt(size*2) - size;
		return array;
	}
}