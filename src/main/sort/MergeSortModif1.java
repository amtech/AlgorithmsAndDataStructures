package main.sort;

public class MergeSortModif1 {

    public static int[] sort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }

        int middle = array.length >> 1;
        int[] left = new int[middle];
        int[] right = new int[array.length - middle];

        System.arraycopy(array, 0, left, 0, middle);
        System.arraycopy(array, middle, right, 0, array.length - middle);

        return merge(sort(left), sort(right));
    }

    private static int[] merge(int[] a, int[] b) {
        if (a.length == 0) {
            return b;
        } else if (b.length == 0) {
            return a;
        }
        int[] result = new int[a.length + b.length];
        int aIndex = 0;
        int bIndex = 0;
        while (aIndex + bIndex != result.length) {
            if (aIndex >= a.length) {
                System.arraycopy(b, bIndex, result, aIndex + bIndex, b.length - bIndex);
                break;
            } else if (bIndex >= b.length) {
                System.arraycopy(a, aIndex, result, aIndex + bIndex, a.length - aIndex);
                break;
            }
            if (a[aIndex] < b[bIndex]) {
                result[aIndex + bIndex] = a[aIndex++];
            } else {
                result[aIndex + bIndex] = b[bIndex++];
            }
        }
        return result;
    }

}