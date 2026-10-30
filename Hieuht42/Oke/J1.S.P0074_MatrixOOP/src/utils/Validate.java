package utils;

import java.util.Scanner;

public class Validate {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static int getInt(String messageInfo, String messageErrorNumber,
            String messageErrorOutOfRange, int min, int max) {
        while (true) {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messageErrorOutOfRange);
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        }
    }

    public static int getInt(String messageInfo, String messageErrorNumber) {
        return getInt(messageInfo, messageErrorNumber, "",
                Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
