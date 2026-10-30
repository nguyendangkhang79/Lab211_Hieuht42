package utils;

import java.util.Scanner;

public class Validate {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getString(String message) {
        while (true) {
            System.out.print(message);
            String input = SCANNER.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    public static String getStringOrDefault(String message, String defaultValue) {
        System.out.print(message);
        String input = SCANNER.nextLine().trim();
        if (input.isEmpty()) {
            return defaultValue;
        }
        return input;
    }

    public static int getInt(String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                int number = Integer.parseInt(SCANNER.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println("Please enter a number from " + min + " to " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer number.");
            }
        }
    }

    public static int getInt(String message, int min) {
        while (true) {
            try {
                System.out.print(message);
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

    public static int getIntOrDefault(String message, int min, int defaultValue) {
        while (true) {
            try {
                System.out.print(message);
                String input = SCANNER.nextLine().trim();
                if (input.isEmpty()) {
                    return defaultValue;
                }
                int number = Integer.parseInt(input);
                if (number >= min) {
                    return number;
                }
                System.out.println("Please enter a number >= " + min + ".");
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer number.");
            }
        }
    }
}
