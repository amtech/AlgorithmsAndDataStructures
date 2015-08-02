package main.matrix;

public class MatrixUtils {

    public static void main(String[] args) {
        double[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        double[][] matrix2 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };
        double[] vector1 = {1, 2, 3};
        double[] vector2 = {9, 8, 7};
        //print(add(matrix1, matrix2));
        print(add(vector1, vector2));
    }

    public static double[] multiplyNumberUponMutrix(final double number, final double[] vector) {
        return multiplyNumberUponMutrix(number, vectorToMatrix(vector))[0];
    }

    public static double[][] multiplyNumberUponMutrix(final double number, final double[][] matrix) {

        final int ROWS = matrix.length;
        final int COLUMNS = matrix[0].length;
        double[][] resultMatrix = new double[ROWS][COLUMNS];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                resultMatrix[i][j] = number * matrix[i][j];
            }
        }
        return resultMatrix;
    }

    private static final double[] substract(final double[] firstVector, final double[] secondVector) {

        return add(vectorToMatrix(firstVector), vectorToMatrix(secondVector))[0];
    }

    private static final double[][] substract(final double[][] firstMatrix, final double[][] secondMatrix) {

        if (firstMatrix.length != secondMatrix.length || firstMatrix[0].length != secondMatrix[0].length) {
            throw new IllegalArgumentException("Matrixes must be same size");
        }

        final int ROWS = firstMatrix.length;
        final int COLUMNS = firstMatrix[0].length;
        double[][] resultMatrix = new double[ROWS][COLUMNS];

        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix[i].length; j++) {
                resultMatrix[i][j] = firstMatrix[i][j] - secondMatrix[i][j];
            }
        }
        return resultMatrix;
    }

    private static final double[] add(final double[] firstVector, final double[] secondVector) {

        return add(vectorToMatrix(firstVector), vectorToMatrix(secondVector))[0];
    }

    private static final double[][] add(final double[][] firstMatrix, final double[][] secondMatrix) {

        if (firstMatrix.length != secondMatrix.length || firstMatrix[0].length != secondMatrix[0].length) {
            throw new IllegalArgumentException("Matrixes must be same size");
        }

        final int ROWS = firstMatrix.length;
        final int COLUMNS = firstMatrix[0].length;
        double[][] resultMatrix = new double[ROWS][COLUMNS];

        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix[i].length; j++) {
                resultMatrix[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
            }
        }
        return resultMatrix;
    }

    private static final double[][] vectorToMatrix(final double[] vector) {
        return new double[][]{vector};
    }

    public static final void print(double[] vector) {
        System.out.println(matrixToString(vector));
    }

    public static final void print(double[][] matrix) {
        System.out.println(matrixToString(matrix));
    }

    public static final String matrixToString(double[] vector) {
        return matrixToString(vectorToMatrix(vector));
    }

    public static final String matrixToString(double[][] matrix) {
        final int ROWS = matrix.length;
        final int COLUMNS = matrix[0].length;

        StringBuilder sb = new StringBuilder(ROWS * COLUMNS * 2);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                sb.append(String.format("%.3f", matrix[i][j])).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
