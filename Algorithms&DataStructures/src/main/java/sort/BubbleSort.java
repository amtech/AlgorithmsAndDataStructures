package sort;

public class BubbleSort {
    
    public static void sort(int[] array) {
        for (int barrier = 0; barrier < array.length - 1; barrier++) {
            for (int index = array.length - 1; index > barrier; index--) {
                if(array[index] < array[index - 1]){
                    swap(array, index, index - 1);
                }
            }
        }
    }
    
    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
