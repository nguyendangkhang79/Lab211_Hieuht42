package main;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static int getInt(String message, int min) {
        while (true) {
            try {
                System.out.println(message);
                int number = Integer.parseInt(SCANNER.nextLine().trim());
                if (number >= min) {
                    return number;
                }
                System.out.println("Please enter a number >= " + min + ".");
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer number.");
            }
        }
    }

    public static int[] createArray(int n) {
        Random random = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(n);
        }
        return array;
    }

    public static int linearSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = getInt("Enter number of array:", 1);
        int[] array = createArray(n);
        System.out.println("The array: " + Arrays.toString(array));
        int value = getInt("Enter search value:", 0);
        int index = linearSearch(array, value);
        if (index == -1) {
            System.out.println("Not found " + value + " in array");
        } else {
            System.out.println("Found " + value + " at index: " + index);
        }
    }
}