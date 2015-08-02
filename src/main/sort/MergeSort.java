package main.sort;

public class MergeSort {

    private static int[] b;

    public static void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        b = new int[array.length];
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] a, int low, int high) {
        if (!(low < high)) {
            return;
        }

        int mid = low + ((high - low) >> 1);
        sort(a, low, mid);
        sort(a, mid + 1, high);

        merge(a, low, mid, high);
    }

    private static void merge(int[] a, int low, int mid, int high) {
        System.arraycopy(a, low, b, low, high - low);

        int aIndex = low;
        int bIndex = mid + 1;
        int index = low;
        while (aIndex <= mid && bIndex <= high) {
            a[index++] = (a[bIndex] < b[aIndex]) ? a[bIndex++] : b[aIndex++];
        }
        while (aIndex <= mid) {
            a[index++] = b[aIndex++];
        }
    }

}