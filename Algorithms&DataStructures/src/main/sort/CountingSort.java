package main.sort;

import java.util.Arrays;

public class CountingSort {
	
	public static void sort(int[] array) {
		if (array == null || array.length < 2){
	    	return;
	    }
		sort(array, 0, array.length - 1);
	}

	private static void sort(int[] a, int low, int high)
	{
	    int[] counts = new int[a.length];
	    for (int x : a) {
	        counts[x]++;
	    }
	 
	    int current = 0;
	    for (int i = 0; i < counts.length; i++)
	    {
	        Arrays.fill(a, current, current + counts[i], i);
	        current += counts[i];
	    }
	}

}
