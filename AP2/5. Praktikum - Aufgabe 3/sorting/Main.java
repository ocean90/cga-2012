package sorting;
import static sorting.Algorithm.BubbleSort;
import static sorting.Algorithm.InsertionSort;
import static sorting.Algorithm.MergeSort;
import static sorting.Algorithm.SelectionSort;

import java.util.EnumSet;

public class Main {

	public static void main(String[] args) {
		int[] a = {7, 1, 6, 2, 3, 8, 4,  5};
		int[] sizes = { 100, 1000, 10000, 100000 };

		TestOfSortingAlgorithms.traceOf(
				EnumSet.of(SelectionSort, InsertionSort, MergeSort, BubbleSort), a);
		TestOfSortingAlgorithms.performanceOf(
				EnumSet.allOf(Algorithm.class), sizes);
	}
}