package utils;

import java.util.Scanner;

public class Validate {
    private static final Scanner SCANNER = new Scanner(System.in);

    // Hàm lấy lựa chọn menu an toàn
    public static int getInt(String messageInfo, String messageErrorNumber, 
                             String messageErrorOutOfRange, int min, int max) {
        do {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }
}