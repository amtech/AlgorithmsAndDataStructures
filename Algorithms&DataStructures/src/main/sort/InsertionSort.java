package main.sort;

public class InsertionSort {
    
    public static void sort(int[] array) {
    	if (array == null || array.length < 2){
	    	return;
	    }
    	
        for (int k = 1; k < array.length; k++) {
            int newElement = array[k];
            int location = k - 1;
            while (location >= 0 && array[location] > newElement) {
                array[location + 1] = array[location];
                location--;
            }
            array[location + 1] = newElement;
        }
    }
}
