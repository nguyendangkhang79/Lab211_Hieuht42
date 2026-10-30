package main;

import utils.Validate;

public class Calculator {

    private static final int MAX_SIZE = 100;

    public int[][] additionMatrix(int[][] matrix1, int[][] matrix2) {
        int row = matrix1.length;
        int column = matrix1[0].length;
        int[][] result = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                result[i][j] = Math.addExact(matrix1[i][j], matrix2[i][j]);
            }
        }
        return result;
    }

    public int[][] subtractionMatrix(int[][] matrix1, int[][] matrix2) {
        int row = matrix1.length;
        int column = matrix1[0].length;
        int[][] result = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                result[i][j] = Math.subtractExact(matrix1[i][j], matrix2[i][j]);
            }
        }
        return result;
    }

    public int[][] multiplicationMatrix(int[][] matrix1, int[][] matrix2) {
        int row = matrix1.length;
        int column = matrix2[0].length;
        int common = matrix2.length;
        int[][] result = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                for (int k = 0; k < common; k++) {
                    result[i][j] = Math.addExact(result[i][j],
                            Math.multiplyExact(matrix1[i][k], matrix2[k][j]));
                }
            }
        }
        return result;
    }

    public void addition() {
        System.out.println("-------- Addition --------");
        int[][] matrix1 = inputMatrix(1);
        int[][] matrix2 = inputMatrix(2, matrix1.length, matrix1[0].length);
        displayResult(matrix1, "+", matrix2, additionMatrix(matrix1, matrix2));
    }

    public void subtraction() {
        System.out.println("----- Subtraction ------");
        int[][] matrix1 = inputMatrix(1);
        int[][] matrix2 = inputMatrix(2, matrix1.length, matrix1[0].length);
        displayResult(matrix1, "-", matrix2, subtractionMatrix(matrix1, matrix2));
    }

    public void multiplication() {
        System.out.println("-------- Multiplication -------");
        int[][] matrix1 = inputMatrix(1);
        int row2 = inputSize("Row Matrix 2", matrix1[0].length);
        int column2 = inputSize("Column Matrix 2");
        int[][] matrix2 = inputValues(2, row2, column2);
        displayResult(matrix1, "*", matrix2, multiplicationMatrix(matrix1, matrix2));
    }

    private int[][] inputMatrix(int order) {
        int row = inputSize("Row Matrix " + order);
        int column = inputSize("Column Matrix " + order);
        return inputValues(order, row, column);
    }

    private int[][] inputMatrix(int order, int row, int column) {
        inputSize("Row Matrix " + order, row);
        inputSize("Column Matrix " + order, column);
        return inputValues(order, row, column);
    }

    private int inputSize(String label) {
        return Validate.getInt("Enter " + label + ":",
                label + " must be the number",
                label + " must be from 1 to " + MAX_SIZE, 1, MAX_SIZE);
    }

    private int inputSize(String label, int required) {
        return Validate.getInt("Enter " + label + ":",
                label + " must be the number",
                label + " must be equal to " + required, required, required);
    }

    private int[][] inputValues(int order, int row, int column) {
        int[][] matrix = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] = Validate.getInt("Enter Matrix" + order
                        + "[" + (i + 1) + "][" + (j + 1) + "]:",
                        "Values of matrix must be the number");
            }
        }
        return matrix;
    }

    private void displayResult(int[][] matrix1, String operator,
            int[][] matrix2, int[][] result) {
        System.out.println("-------- Result --------");
        displayMatrix(matrix1);
        System.out.println(operator);
        displayMatrix(matrix2);
        System.out.println("=");
        displayMatrix(result);
    }

    private void displayMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print("[" + value + "]");
            }
            System.out.println();
        }
    }
}
