package j1.s.p0010;

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

    private static int linearSearch(int[] array, int searchValue) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == searchValue) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int size = inputPositiveInt("Enter number of array: ");
        int searchValue = inputInt("Enter search value: ");

        int[] array = generateRandomArray(size);
        int index = linearSearch(array, searchValue);

        System.out.println("The array: " + Arrays.toString(array));
        if (index >= 0) {
            System.out.println("Found " + searchValue + " at index: " + index);
        } else {
            System.out.println(searchValue + " is not found in the array.");
        }
    }
}
