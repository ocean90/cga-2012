package sorting;
public class Selectionsort implements IntSort {

	/**
	 * Der Algorithmus SelectionSort (direkte Auswahl) funktioniert wie folgt:
	 * <p>
	 * Das Teilfeld array[0] bis array[i-1] ist fertig sortiert. Um i fuer den
	 * naechsten Durchlauf erhoehen zu koennen wir in dem noch nicht sortierten
	 * Teilfeld das Feldelement mit der kleinsten Zahl gesucht. Dieses tauscht
	 * dann seinen Inhalt mit a[i].
	 * <p>
	 * Ein korrekter Ablauf wird durch die folgenden Ausgaben dargestellt:
	 * <pre>
	 * SelectionSort: [ | 7, 1, 6, 2, 3, 8, 4, 5 ]
	 * SelectionSort: [ 1| 7, 6, 2, 3, 8, 4, 5 ]
	 * SelectionSort: [ 1, 2| 6, 7, 3, 8, 4, 5 ]
	 * SelectionSort: [ 1, 2, 3| 7, 6, 8, 4, 5 ]
	 * SelectionSort: [ 1, 2, 3, 4| 6, 8, 7, 5 ]
	 * SelectionSort: [ 1, 2, 3, 4, 5| 8, 7, 6 ]
	 * SelectionSort: [ 1, 2, 3, 4, 5, 6| 7, 8 ]
	 * SelectionSort: [ 1, 2, 3, 4, 5, 6, 7, 8 ]
	 * * </pre>
	 * Der Strich | trennt den sortierten von dem unsortierten Teil.
	 */
	@Override
	public void sort(int[] array) {
		// Array von links nach rechts durchlaufen
		for (int i = 0; i < array.length - 1; i++) {
			Tracer.direct(array, i);

			// Index des bisher kleinsten Wertes speichern
			int tmp_index = i;
			// Wert des bisher kleinsten Wertes speichern
			int tmp_value = array[i];

			// Rest des Arrays durchlaufen
			for ( int j = i + 1; j < array.length; j++) {
				// Kleineren Wert als aktuellen Wert finden
				if ( array[j] < tmp_value ) {
					// Wenn gefunden, Index und Wert speichern
					tmp_index = j;
					tmp_value = array[j];
				}
			}

			// Wenn kleineren Wert gefunden
			if ( i != tmp_index ) {
				// dann alten Wert mit Neuem ersetzen
				array[tmp_index] = array[i];
				array[i] = tmp_value;
			}
		}
		Tracer.array(array);
	}
}
