package sorting;

/**
 * Generelle Schnittelstelle fuer das Sortieren von Feldern von ganzen
 * Zahlen.
 */
public interface IntSort {
	/**
	 * Sortiert uebergebene Feld.
	 * Es wird kein neues Feld erzeugt, sondern einfach die Reihenfolge der
	 * Elemente geaendert.
	 *
	 * @param array zu sortierendes Feld
	 */
	public void sort(int[] array);
}
