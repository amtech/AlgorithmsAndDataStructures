package test.sort;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;

import main.sort.BubbleSort;
import main.sort.CountingSort;
import main.sort.InsertionSort;
import main.sort.MergeSort;
import main.sort.MergeSortModif1;
import main.sort.QuickSort;
import main.sort.SelectionSort;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SortTest {
	
	private static final int RANGE = 5_000;
	private static final int ARRAY_SIZE = 10_000;
	private static int[] etalon;
	private static int[] sortedArray;
	
	private int[] array;
	
	@BeforeClass
	public static void init() {
		etalon = generateArrayOfRandomNumbers(ARRAY_SIZE);
		sortedArray = etalon.clone();
		Arrays.sort(sortedArray);
		//System.out.println(Arrays.toString(sortedArray));
	}
	
	@Before
	public void setUp() throws Exception {
		array = etalon.clone();
	}

	@Test
	public void testBubbleSort() {
		BubbleSort.sort(array);
		assertArrayEquals(array, sortedArray);
	}
	
	@Test
	public void testInsertionSort() {
		InsertionSort.sort(array);
		assertArrayEquals(array, sortedArray);
	}
	
	@Test
	public void testSelectionSort() {
		SelectionSort.sort(array);
		assertArrayEquals(array, sortedArray);
	}
	
	@Test
	public void testCountingSort() {
		CountingSort.sort(array);
		assertArrayEquals(sortedArray, array);
	}
	
	@Test
	public void testMergeSort() {
		MergeSort.sort(array);
		assertArrayEquals(array, sortedArray);
	}
	
	@Test
	public void testMergeSortModif1() {
		int[] result = MergeSortModif1.sort(array);
		assertArrayEquals(result, sortedArray);
	}
	
	@Test
	public void testQuickSort() {
		QuickSort.sort(array);
		assertArrayEquals(array, sortedArray);
	}
	
	private static int[] generateArrayOfRandomNumbers(int size) {
		int[] array = new int[size];
		Random random = new Random();
		for(int i = 0; i < size; i++) {
			array[i] = random.nextInt(RANGE);
		}
		return array;
	}

}
