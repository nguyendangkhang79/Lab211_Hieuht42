package utils;

import java.util.Scanner;

public class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Validator() {
    }

    public static int getInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            try {
                int number = Integer.parseInt(SCANNER.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println("Please enter a number from " + min + " to " + max + "!");
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer number!");
            }
        }
    }

    public static double getPositiveDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                double number = Double.parseDouble(SCANNER.nextLine().trim());
                if (number > 0) {
                    return number;
                }
                System.out.println("Value must be greater than 0!");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a real number!");
            }
        }
    }

    public static String getString(String message, String regex, String error) {
        while (true) {
            System.out.print(message);
            String str = SCANNER.nextLine().trim();
            if (str.matches(regex)) {
                return str;
            }
            System.out.println(error);
        }
    }
}
