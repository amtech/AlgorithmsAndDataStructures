package sort;

public class QuickSort {
	
	public static void sort(int[] array) {
		if (array == null || array.length < 2){
	    	return;
	    }
	    quicksort1(array, 0, array.length - 1);
	}

  	private static void quicksort1(int[] array, int low, int high) {
		int i = low, j = high;
		
		int pivot = array[low + (high-low)/2];
		
		while (i <= j) {
			while (array[i] < pivot) {
				i++;
			}
			while (array[j] > pivot) {
				j--;
			}
			if (i <= j) {
				swap(array, i, j);
				i++;
				j--;
			}
		}
		if (low < j)
			quicksort1(array, low, j);
		if (i < high)
			quicksort1(array, i, high);
  	}

  	private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
