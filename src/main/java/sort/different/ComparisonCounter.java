package sort.different;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ComparisonCounter {

	private long comparisons = 0;

	public static void main(String[] args) throws FileNotFoundException {
		int[] array = getArray();
		ComparisonCounter comparisonCounter = new ComparisonCounter();
		comparisonCounter.quickSort(array, 0, array.length - 1);
		assert (isSorted(array));
		System.out.println(comparisonCounter.getComparisons());
	}

	private static boolean isSorted(int[] array) {
		boolean isSorted = true;
		for (int i = 1; i < array.length; i++) {
			if (array[i - 1] > array[i]) {
				isSorted = false;
			}
		}
		return isSorted;
	}

	private static int[] getArray() throws FileNotFoundException {
		Scanner scanner = new Scanner(
				new File("C:\\Users\\koxa\\git\\Algorithms-DataStructures\\src\\test\\resourses\\sort\\different\\QuickSort.txt"));
		int[] array = new int[10_000];
		int i = 0;
		while (scanner.hasNextInt()) {
			array[i++] = scanner.nextInt();
		}
		scanner.close();
		return array;
	}

	public void quickSort(int arr[], int left, int right) {
		if (right > left) {
			int index = partition(arr, left, right);
			quickSort(arr, left, index - 1);
			quickSort(arr, index + 1, right);
		}
	}

	private int partition(int arr[], int left, int right) {
		comparisons += right - left;
		int i = left + 1;
		int pivot = pivot(arr, left, (left + right) / 2, right);
		for (int j = left + 1; j <= right; j++) {
			if (arr[j] < pivot) {
				swap(arr, j, i);
				i++;
			}
		}
		swap(arr, left, i - 1);
		return i - 1;
	}
	
	private int pivot(int[] arr, int left, int middle, int right) {
		//	1
		//return arr[left];
		
		//	2
//		swap(arr, left, right);
//		return arr[left];
		
		//	3
		return findAverage(arr, left, middle, right);
		
	}

	private int findAverage(int[] arr, int left, int middle, int right) {
		int average;
		if (arr[left] < arr[middle]) {
			if (arr[middle] < arr[right]) {
				average = arr[middle];
				swap(arr, left, middle);
			} else if (arr[left] < arr[right]){
				average = arr[right];
				swap(arr, left, right);
			} else {
				average = arr[left];
			}
		} else {
			if (arr[left] < arr[right]) {
				average = arr[left];
			} else if (arr[middle] < arr[right]) {
				average = arr[right];
				swap(arr, left, right);
			} else {
				average = arr[middle];
				swap(arr, left, middle);
			}
		}
		return average;
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}

	public long getComparisons() {
		return comparisons;
	}

}
