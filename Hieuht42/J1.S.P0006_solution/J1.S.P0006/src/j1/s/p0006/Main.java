package j1.s.p0006;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    private static int inputPositiveInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                int value = Integer.parseInt(SCANNER.nextLine().trim());
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Ask again below.
            }
            System.out.println("Please enter a positive integer.");
        }
    }

    private static int inputInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(SCANNER.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer.");
            }
        }
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt(size);
        }
        return array;
    }

    private static int binarySearch(int[] array, int searchValue) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (array[middle] == searchValue) {
                return middle;
            }

            if (searchValue < array[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int size = inputPositiveInt("Enter number of array: ");
        int searchValue = inputInt("Enter search value: ");

        int[] array = generateRandomArray(size);
        Arrays.sort(array);

        int index = binarySearch(array, searchValue);

        System.out.println("Sorted array: " + Arrays.toString(array));

        if (index >= 0) {
            System.out.println("Found " + searchValue + " at index: " + index);
        } else {
            System.out.println(searchValue + " is not found in the array.");
        }
    }
}
