package sorting;
import java.util.Random;

public class Quicksort implements IntSort {
    private static Random r = new Random();
    
    @Override
    public void sort(int[] array) {
        qs(array, 0, array.length);
        Tracer.array(array);
    }

    private void qs(int[] array, int lo, int hi) {
        if (hi - lo >= 2) {
            int mid = partition(array, lo, hi);
            Tracer.recursive(array, lo, mid, hi);
            qs(array, lo, mid);
            qs(array, mid+1, hi);
        }
    }

    private int partition(int[] array, int lo, int hi) {
        int pivot = getPivot(array, lo, hi);
        int i = lo;
        int j = hi-1;
        while (i < j) {
            while (i <= j && array[i] <= pivot) i++;
            while (array[j] > pivot) j--;
            if (i < j)
                swap(array, i, j);
        }
        swap(array, lo, j);
        return j;
    }

    private int getPivot(int[] array, int lo, int hi) {
        int pivotPos = r.nextInt(hi-lo) + lo;
        swap(array, lo, pivotPos);
        return array[lo];
    }

    private void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}
