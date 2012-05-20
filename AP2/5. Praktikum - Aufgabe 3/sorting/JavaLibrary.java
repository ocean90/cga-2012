package sorting;
import java.util.Arrays;

/**
 * Sortiert mit der Bibliotheksmethode.
 */
public class JavaLibrary implements IntSort {

	@Override
	public void sort(int[] array) {
		Arrays.sort(array);
	}

}
