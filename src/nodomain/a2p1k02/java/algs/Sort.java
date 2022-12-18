package nodomain.a2p1k02.java.algs;

import java.util.ArrayList;
import java.util.Arrays;

public class Sort {

    public static void main(String[] args) {
        int[] list1 = {63, 62, 76, 54, 65, 231, 8, 10};
        int[] list2 = {63, 62, 76, 54, 65, 231, 8, 10};

        long startTimeBS = System.nanoTime();
        bubbleSort(list1);
        long endTimeBS = System.nanoTime();

        long startTimeQS = System.nanoTime();
        quickSort(list2, 0, list2.length - 1);
        long endTimeQS = System.nanoTime();

        System.out.println(endTimeBS - startTimeBS);
        System.out.println(endTimeQS - startTimeQS);

//        System.out.println(Arrays.toString(list1));
//        System.out.println(Arrays.toString(list2));
//
//        System.out.println(binarySearch(list1, 65));
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (arr.length == 0)
            return;
        if (low >= high)
            return;

        int mid = low + (high - low) / 2;
        int start = arr[mid];


        int i = low, j = high;

        while (i <= j) {
            while (arr[i] < start)
                i++;
            while (arr[j] > start)
                j--;

            if (i <= j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(arr, low, j);
        if (high > i)
            quickSort(arr, i, high);
    }

    private static int binarySearch(int[] list, int item) {
        int low = 0;
        int high = list.length - 1;

        while (low <= high) {
            int mid = (low + high);
            int guess = list[mid];
            if (guess == item)
                return mid;
            if (guess > item)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return 0;
    }
}
