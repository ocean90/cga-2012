package sorting;
public class BubbleSort implements IntSort {

	/**
	 * Der Algorithmus BubbleSort (direktes Vertauschen) funktioniertwie folgt:
	 * <p>
	 * Für jeden Durchlauf der äußeren Schlleife geht man das Feld beginned mit
	 * a[i] durch.
	 * Dabei vergleicht man 2 aufeinander folgende Feldinhalte und vertauscht sie,
	 * falls sie in der falschen Reihenfolge stehen
	 * <p>
	 * Ein korrekter Ablauf wird durch die folgenden Ausgaben dargestellt:
	 * <pre>
	 * BubbleSort: [ 7, 1, 6, 2, 3, 8, 4, 5| ]
	 * BubbleSort: [ 1, 6, 2, 3, 7, 4, 5|, 8 ] // nach dem 1. Durchlauf
	 * BubbleSort: [ 1, 2, 3, 6, 4, 5|, 7, 8 ]
	 * BubbleSort: [ 1, 2, 3, 4, 5|, 6, 7, 8 ]
	 * BubbleSort: [ 1, 2, 3, 4|, 5, 6, 7, 8 ]
	 * BubbleSort: [ 1, 2, 3|, 4, 5, 6, 7, 8 ]
	 * BubbleSort: [ 1, 2|, 3, 4, 5, 6, 7, 8 ]
	 * BubbleSort: [ 1, 2, 3, 4, 5|, 6, 7, 8 ]
	 * * </pre>
	 * Der Strich | trennt den sortierten von dem unsortierten Teil.
	 */
	@Override
	public void sort(int[] array) {
		// Array von links nach rechts durchlaufen
		for( int i = 0; i < array.length; i++ ) {
			Tracer.direct(array, i);

			// Array von links nach rechts durchlaufen
			for( int j = 0; j < array.length - 1; j++ ) {
				// Prüfen ob nächstes Element größer als aktuelles Element
				if( array[j] > array[j + 1] ) {
					// Wenn ja, tauschen
					swap( array, j, j+1 );
				}
			}
		}
		Tracer.array(array);
	}

	/**
	 * Tauscht zwei Elemente in einem Array.
	 * @param array
	 * @param a Element a
	 * @param b Element b
	 */
	private void swap( int[] array, int a, int b ) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

}
