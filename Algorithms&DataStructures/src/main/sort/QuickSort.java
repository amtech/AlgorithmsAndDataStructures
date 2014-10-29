package main.sort;

public class QuickSort {
	
	public static void sort(int[] array) {
		if (array == null || array.length < 2){
	    	return;
	    }
	    quicksort(array, 0, array.length - 1);
	}

  	private static void quicksort(int[] array, int low, int high) {
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
			quicksort(array, low, j);
		if (i < high)
			quicksort(array, i, high);
  	}

  	private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
