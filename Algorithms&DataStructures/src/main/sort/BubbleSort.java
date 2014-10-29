/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[] {1, 15, 16, 2, -1, 19, 3, 7, 15, 9};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    
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
