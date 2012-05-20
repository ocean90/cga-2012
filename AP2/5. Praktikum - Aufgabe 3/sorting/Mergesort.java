package sorting;
public class Mergesort implements IntSort {

	/**
	 * Sortieren durch rekursives Mischen (merge sort).
	 * Der Algorithmus sortiert <code>int</code> Felder.
	 * @param a zu sortierendes Feld.
	 */
	public void sort(int[] a) {
		int[] help = new int[a.length];
		System.arraycopy(a, 0, help, 0, a.length);
		mergeSort(help, a, 0, a.length);
		Tracer.array(a);
	}

	/**
	 * Hilfsroutine fuer Mergesort auf <code>int</code>.
	 * Der eigentliche Algorithmus zum
	 * sortieren eines zusammenhaengenden Feldabschnitts.
	 * Eine Besonderheit des Hilfsfeldes <code>src</code> ist, beim Aufruf,
	 * dass der zu sortierende Bereich eine Kopie von <code>dest</code> ist.
	 * @param src Hilfsfeld
	 * @param dest zu sortierendes Feld
	 * @param lo Index des ersten Feldelements
	 * @param hi Index des letzten Feldelements+1
	 */
	protected void mergeSort(int[] src, int[] dest, int lo, int hi) {
		int length = hi - lo;
		if (length >= 2) {
			// Aufteilen und getrennt sortieren
			int mid = (lo + hi) / 2;
			mergeSort(dest, src, lo, mid);
			mergeSort(dest, src, mid, hi);
			Tracer.recursive(src, lo, mid, hi);
			merge(src, dest, lo, mid, hi);
		}
	}

	/**
	 * Mischt zwei sortierte Feldabschnitte des Feldes <code>src</code>
	 * sortiert in das Feld <code>dest</code>. Der erste Abschnitt geht
	 * von <code>lo</code> bis <code>mid - 1</code>, der zweite Abschnitt
	 * geht von <code>mid</code> bis <code>hi - 1</code>. Der Abschnitt
	 * in dem Feld <code>dest</code> reicht von <code>lo</code> bis
	 * <code>hi - 1</code>.
	 * @param src Quellfeld.
	 * @param dest Zielfeld.
	 * @param lo Beginn der Feldbereiche.
	 * @param mid Beginn des zweiten Teilbereichs.
	 * @param hi Ende der Feldbereiche + 1.
	 */
	protected void merge(int[] src, int[] dest, int lo, int mid, int hi) {
			int start_left = lo;
			int end_left = mid;
			int start_right = mid;
			int end_right = hi;
			int dest_index = lo;

			// Abschnitte mischen
			while( start_left < end_left && start_right < end_right ) {
				// Kleinere Element finden und speichern
				if ( src[start_left] < src[start_right] ) {
					dest[dest_index++] = src[start_left++];
				} else {
					dest[dest_index++] = src[start_right++];
				}
			}

			// Wenn Abschnitt rechts am Ende
			if ( start_right == end_right ) {
				// Rest von Abschnitt links speichern
				while ( start_left < end_left ) {
					dest[dest_index++] = src[start_left++];
				}
			} else { // Wenn Abschnitt links am Ende
				// Rest von Abschnitt rechts speichern
				while ( start_right < end_right ) {
					dest[dest_index++] = src[start_right++];
				}
			}
	}
}
