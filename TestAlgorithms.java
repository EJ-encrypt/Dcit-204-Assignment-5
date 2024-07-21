package test;

import algorithms.divideandconquer.MergeSort;
import algorithms.divideandconquer.QuickSort;
import performance.PerformanceTester;

public class TestAlgorithms {

    public static void main(String[] args) {
        // Sample data for testing
        int[] array1 = {12, 11, 13, 5, 6, 7};
        int[] array2 = array1.clone(); // Clone to ensure same input for both algorithms

        // Create algorithm instances
        MergeSort mergeSort = new MergeSort();
        QuickSort quickSort = new QuickSort();

        // Test MergeSort performance
        long mergeSortTime = PerformanceTester.measureExecutionTime(() -> {
            mergeSort.sort(array1);
            return null;
        });
        System.out.println("MergeSort execution time: " + mergeSortTime + " ms");

        // Test QuickSort performance
        long quickSortTime = PerformanceTester.measureExecutionTime(() -> {
            quickSort.sort(array2);
            return null;
        });
        System.out.println("QuickSort execution time: " + quickSortTime + " ms");

        // Print the sorted arrays to verify correctness
        System.out.println("Sorted array using MergeSort: ");
        for (int num : array1) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.println("Sorted array using QuickSort: ");
        for (int num : array2) {
            System.out.print(num + " ");
        }
    }
}
