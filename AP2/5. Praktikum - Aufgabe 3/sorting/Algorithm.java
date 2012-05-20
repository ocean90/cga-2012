package sorting;

public enum Algorithm {
	JavaLibrary(new JavaLibrary()),
	QuickSort(new Quicksort()),
	MergeSort(new Mergesort()),
	InsertionSort(new Insertionsort()),
	SelectionSort(new Selectionsort()),
	BubbleSort(new BubbleSort());

	private final IntSort sorter;
	private Algorithm(IntSort sorter) {
		this.sorter = sorter;
	}

	/**
	 * Sortiere mit dem angegebenen Algorithmus.
	 *
	 * @param a zu sortierendes Feld.
	 */
	public void sort(int a[]) {
		sorter.sort(a);
	}
}
