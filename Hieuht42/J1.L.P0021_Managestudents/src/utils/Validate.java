package utils;

import java.util.Scanner;

public class Validate {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String[] COURSES = {"Java", ".Net", "C/C++"};

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

    public static int getInt(String message, int min, int max) {
        while (true) {
            try {
                int number = Integer.parseInt(getString(message));
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println("Please enter a number from " + min + " to " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer number.");
            }
        }
    }

    public static String getCourse(String message) {
        while (true) {
            String input = getString(message);
            for (String course : COURSES) {
                if (course.equalsIgnoreCase(input)) {
                    return course;
                }
            }
            System.out.println("There are only three courses: Java, .Net, C/C++.");
        }
    }

    public static String getOption(String message, String first, String second) {
        while (true) {
            String input = getString(message);
            if (input.equalsIgnoreCase(first)) {
                return first;
            }
            if (input.equalsIgnoreCase(second)) {
                return second;
            }
            System.out.println("Please enter " + first + " or " + second + ".");
        }
    }
}
