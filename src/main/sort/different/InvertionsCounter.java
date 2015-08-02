package main.sort.different;

public class InvertionsCounter {
    private static int[] b;

    public static long count(int[] array) {
        if (array == null || array.length < 2) {
            return 0l;
        }
        b = new int[array.length];
        return sort(array, 0, array.length - 1);
    }

    private static long sort(int[] a, int low, int high) {
        if (!(low < high)) {
            return 0l;
        }
        int mid = low + ((high - low) >> 1);
        return sort(a, low, mid) + sort(a, mid + 1, high)
                + merge(a, low, mid, high);
    }

    private static long merge(int[] a, int low, int mid, int high) {
        long inversions = 0;
        System.arraycopy(a, low, b, low, high - low);
        int aIndex = low;
        int bIndex = mid + 1;
        for (int index = low; index <= high; index++) {
            if (bIndex > high || aIndex <= mid && b[aIndex] <= a[bIndex]) {
                a[index] = b[aIndex++];
            } else {
                inversions += mid - (aIndex - 1);
                a[index] = a[bIndex++];
            }
        }
        return inversions;
    }

//	private static long merge(int[] a, int low, int mid, int high) {
//		long inversions = 0l;
//		System.arraycopy(a, low, b, low, high - low);
//		int aIndex = low;
//		int bIndex = mid + 1;
//		int index = low;
//		while (aIndex <= mid && bIndex <= high) {
//			if (a[bIndex] < b[aIndex]) {
//				inversions += mid - (aIndex - 1);
//				a[index++] = a[bIndex++];
//			} else {
//				a[index++] = b[aIndex++];
//			}
//		}
//		while (aIndex <= mid) {
//			a[index++] = b[aIndex++];
//		}
//		return inversions;
//	}
}
