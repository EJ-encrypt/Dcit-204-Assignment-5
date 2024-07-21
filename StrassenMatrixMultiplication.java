package algorithms.divideandconquer;

public class StrassenMatrixMultiplication {

    // Function to multiply two matrices using Strassen's algorithm
    public int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;

        // Base case - if the matrix is 1x1
        if (n == 1) {
            int[][] C = new int[1][1];
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }

        // Initialize result matrix
        int[][] C = new int[n][n];

        // Divide matrices into 4 sub-matrices
        int mid = n / 2;
        int[][] A11 = new int[mid][mid];
        int[][] A12 = new int[mid][mid];
        int[][] A21 = new int[mid][mid];
        int[][] A22 = new int[mid][mid];
        int[][] B11 = new int[mid][mid];
        int[][] B12 = new int[mid][mid];
        int[][] B21 = new int[mid][mid];
        int[][] B22 = new int[mid][mid];

        // Populate sub-matrices
        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                A11[i][j] = A[i][j];
                A12[i][j] = A[i][j + mid];
                A21[i][j] = A[i + mid][j];
                A22[i][j] = A[i + mid][j + mid];

                B11[i][j] = B[i][j];
                B12[i][j] = B[i][j + mid];
                B21[i][j] = B[i + mid][j];
                B22[i][j] = B[i + mid][j + mid];
            }
        }

        // Calculate intermediate matrices
        int[][] M1 = strassenMultiply(addMatrix(A11, A22), addMatrix(B11, B22));
        int[][] M2 = strassenMultiply(addMatrix(A21, A22), B11);
        int[][] M3 = strassenMultiply(A11, subtractMatrix(B12, B22));
        int[][] M4 = strassenMultiply(A22, subtractMatrix(B21, B11));
        int[][] M5 = strassenMultiply(addMatrix(A11, A12), B22);
        int[][] M6 = strassenMultiply(subtractMatrix(A21, A11), addMatrix(B11, B12));
        int[][] M7 = strassenMultiply(subtractMatrix(A12, A22), addMatrix(B21, B22));

        // Calculate result sub-matrices
        int[][] C11 = addMatrix(subtractMatrix(addMatrix(M1, M4), M5), M7);
        int[][] C12 = addMatrix(M3, M5);
        int[][] C21 = addMatrix(M2, M4);
        int[][] C22 = addMatrix(subtractMatrix(addMatrix(M1, M3), M2), M6);

        // Combine result sub-matrices into C
        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                C[i][j] = C11[i][j];
                C[i][j + mid] = C12[i][j];
                C[i + mid][j] = C21[i][j];
                C[i + mid][j + mid] = C22[i][j];
            }
        }

        return C;
    }

    // Function to add two matrices
    private int[][] addMatrix(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    // Function to subtract two matrices
    private int[][] subtractMatrix(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    // Example usage
    public static void main(String[] args) {
        StrassenMatrixMultiplication strassen = new StrassenMatrixMultiplication();

        int[][] A = { { 1, 2 }, { 3, 4 } };
        int[][] B = { { 5, 6 }, { 7, 8 } };

        int[][] C = strassen.strassenMultiply(A, B);

        System.out.println("Resultant Matrix:");
        for (int[] ints : C) {
            for (int j = 0; j < C[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
}
