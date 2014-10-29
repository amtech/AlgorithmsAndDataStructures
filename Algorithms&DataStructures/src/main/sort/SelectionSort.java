package main.sort;

public class SelectionSort {

	public static void sort(int[] array) {
		if (array == null || array.length < 2){
	    	return;
	    }
		
		for (int barrier = 0; barrier < array.length - 1; barrier++) {
			int index_min_element = barrier + 1;
			int min_elemen = array[index_min_element];
			for (int index = barrier + 1; index < array.length; index++) {
				if (array[index] < min_elemen) {
					min_elemen = array[index];
					index_min_element = index;
				}
			}
			if (array[barrier] > min_elemen) {
				array[index_min_element] = array[barrier];
				array[barrier] = min_elemen;
			}
		}
	}

}
