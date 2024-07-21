package interfaces;

import algorithms.divideandconquer.*;
import algorithms.greedy.HuffmanCodes;
import algorithms.greedy.KruskalMST;
import performance.PerformanceTester;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.*;

import java.util.*;

public class AlgorithmTesterConsole {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getChoice();
            if (choice == 0) break;
            handleChoice(choice);
        }
    }

    private static void displayMenu() {
        System.out.println("\nSelect an Algorithm to Test:");
        System.out.println("1. QuickSort");
        System.out.println("2. MergeSort");
        System.out.println("3. Closest-Pair Problem");
        System.out.println("4. Strassen’s Matrix Multiplication");
        System.out.println("5. Quickhull");
        System.out.println("6. Prim’s Minimum Spanning Tree (MST)");
        System.out.println("7. Traveling Salesman Problem (TSP)");
        System.out.println("8. Kruskal’s MST");
        System.out.println("9. Dijkstra’s Shortest Path");
        System.out.println("10. Huffman Codes");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice. Please enter a number between 0 and 10.");
        }
        return choice;
    }

    private static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                handleQuickSort();
                break;
            case 2:
                handleMergeSort();
                break;
            case 3:
                handleClosestPair();
                break;
            case 4:
                handleStrassensMatrixMultiplication();
                break;
            case 5:
                handleQuickhull();
                break;
            case 6:
                handlePrimsMST();
                break;
            case 7:
                handleTSP();
                break;
            case 8:
                handleKruskalsMST();
                break;
            case 9:
                handleDijkstra();
                break;
            case 10:
                handleHuffmanCodes();
                break;
            case 0:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 0 and 10.");
        }
    }

    private static void handleQuickSort() {
        System.out.println("Enter the array of integers to sort (space-separated):");
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");
        int[] array = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            array[i] = Integer.parseInt(inputArray[i]);
        }

        QuickSort sorter = new QuickSort();
        long timeTaken = PerformanceTester.measureExecutionTime(() -> {
            sorter.quickSort(array, 0, array.length - 1);
            return null;
        });

        System.out.println("QuickSort result: " + Arrays.toString(array));
        System.out.println("QuickSort execution time: " + timeTaken + " ms");
    }

    private static void handleMergeSort() {
        System.out.println("Enter the array of integers to sort (space-separated):");
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");
        int[] array = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            array[i] = Integer.parseInt(inputArray[i]);
        }

        MergeSort sorter = new MergeSort();
        long timeTaken = PerformanceTester.measureExecutionTime(() -> {
            sorter.sort(array);
            return null;
        });

        System.out.println("MergeSort result: " + Arrays.toString(array));
        System.out.println("MergeSort execution time: " + timeTaken + " ms");
    }

    private static void handleClosestPair() {
        System.out.println("Enter the points (x1 y1 x2 y2 ...):");
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");
        Point[] points = new Point[inputArray.length / 2];
        int index = 0;
        for (int i = 0; i < inputArray.length; i += 2) {
            double x = Double.parseDouble(inputArray[i]);
            double y = Double.parseDouble(inputArray[i + 1]);
            points[index++] = new Point(x, y);
        }

        ClosestPair closestPair = new ClosestPair();
        long timeTaken = PerformanceTester.measureExecutionTime(() -> {
            closestPair.closest(points, points.length);
            return null;
        });

        double closestDistance = closestPair.closest(points, points.length);
        System.out.println("Closest-Pair Problem result: " + closestDistance);
        System.out.println("Closest-Pair Problem execution time: " + timeTaken + " ms");
    }

    private static void handleStrassensMatrixMultiplication() {
        System.out.println("Enter the dimension of the matrices (n for nxn matrix):");
        int n = Integer.parseInt(scanner.nextLine());

        int[][] A = new int[n][n];
        int[][] B = new int[n][n];

        System.out.println("Enter the elements of the first matrix (space-separated):");
        String inputA = scanner.nextLine();
        String[] inputArrayA = inputA.split(" ");
        if (inputArrayA.length != n * n) {
            System.out.println("Invalid input size for matrix A.");
            return;
        }

        System.out.println("Enter the elements of the second matrix (space-separated):");
        String inputB = scanner.nextLine();
        String[] inputArrayB = inputB.split(" ");
        if (inputArrayB.length != n * n) {
            System.out.println("Invalid input size for matrix B.");
            return;
        }

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = Integer.parseInt(inputArrayA[index]);
                B[i][j] = Integer.parseInt(inputArrayB[index]);
                index++;
            }
        }

        StrassenMatrixMultiplication strassen = new StrassenMatrixMultiplication();
        long timeTaken = PerformanceTester.measureExecutionTime(() -> {
            strassen.strassenMultiply(A, B);
            return null;
        });

        int[][] C = strassen.strassenMultiply(A, B);

        System.out.println("Strassen’s Matrix Multiplication result:");
        for (int[] row : C) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println("Strassen’s Matrix Multiplication execution time: " + timeTaken + " ms");
    }

    private static void handleQuickhull() {
        System.out.println("Enter the points (x1 y1 x2 y2 ...):");
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");
        Point[] points = new Point[inputArray.length / 2];
        int index = 0;
        for (int i = 0; i < inputArray.length; i += 2) {
            double x = Double.parseDouble(inputArray[i]);
            double y = Double.parseDouble(inputArray[i + 1]);
            points[index++] = new Point(x, y);
        }

        Quickhull quickhull = new Quickhull();
        long timeTaken = PerformanceTester.measureExecutionTime(() -> {
            quickhull.quickHull(points);
            return null;
        });

        List<Point> hull = quickhull.quickHull(points);

        System.out.println("Quickhull result: ");
        for (Point point : hull) {
            System.out.println("(" + point.x + ", " + point.y + ")");
        }
        System.out.println("Quickhull execution time: " + timeTaken + " ms");
    }

    private static void handlePrimsMST() {
        System.out.println("Enter the graph edges and weights (e.g., 3 3 1 2 1 2 3 2 1 3 3):");
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");
        // Parse input into graph

        // Replace with actual Prim’s MST implementation
        long timeTaken = PerformanceTester.measureExecutionTime(() -> {
            // Implement Prim's MST here
            return null;
        });

        System.out.println("Prim’s MST result: " + input);
        System.out.println("Prim’s MST execution time: " + timeTaken + " ms");
    }

    private static void handleTSP() {
        System.out.println("Enter the points (x1 y1 x2 y2 ...):");
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");
        double[] points = new double[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            points[i] = Double.parseDouble(inputArray[i]);
        }

        // Replace with actual TSP implementation
        long timeTaken = PerformanceTester.measureExecutionTime(() -> {
            // Implement TSP here
            return null;
        });

        System.out.println("TSP result: " + Arrays.toString(points));
        System.out.println("TSP execution time: " + timeTaken + " ms");
    }

    private static void handleKruskalsMST() {
        System.out.println("Enter the graph edges and weights (e.g., 2 3 4 5 6 3 5 3 4 6 6):");
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");

        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < inputArray.length; i += 3) {
            if (i + 2 < inputArray.length) { // Ensure enough elements for an edge
                int[] edge = new int[3];
                edge[0] = Integer.parseInt(inputArray[i]);
                edge[1] = Integer.parseInt(inputArray[i + 1]);
                edge[2] = Integer.parseInt(inputArray[i + 2]);
                edges.add(edge);
            } else {
                System.out.println("Invalid input format for edge at index " + i);
                return; // Exit method or handle error condition
            }
        }

        KruskalMST kruskal = new KruskalMST();
        long timeTaken = PerformanceTester.measureExecutionTime(() -> {
            kruskal.findMST(edges.size(), edges);
            return null;
        });

        List<int[]> mst = kruskal.findMST(edges.size(), edges);

        System.out.println("Edges in Kruskal's MST:");
        for (int[] edge : mst) {
            System.out.println(edge[0] + " - " + edge[1] + ": " + edge[2]);
        }
        System.out.println("Kruskal’s MST execution time: " + timeTaken + " ms");
    }

    private static void handleDijkstra() {
        System.out.println("Enter the graph edges and weights, and source node (e.g., 3 3 1 2 1 2 3 2 1 3 3 1):");
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");
        // Parse input into graph and source node

        // Replace with actual Dijkstra’s algorithm implementation
        long timeTaken = PerformanceTester.measureExecutionTime(() -> {
            // Implement Dijkstra's algorithm here
            return null;
        });

        System.out.println("Dijkstra’s Shortest Path result: " + input);
        System.out.println("Dijkstra’s Shortest Path execution time: " + timeTaken + " ms");
    }

    private static void handleHuffmanCodes() {
        // Implement Huffman Codes handling here with performance measurement
    }
}
