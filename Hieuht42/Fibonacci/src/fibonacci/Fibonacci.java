package fibonacci;

import java.util.Scanner;

public class Fibonacci {

    private static final Scanner sc = new Scanner(System.in);

    public static void fibonacci(int n) {
        if (n <= 0) {
            System.out.println("n must be greater than 0");
            return;
        }

        long a = 0;
        long b = 1;

        System.out.println("First " + n + " Fibonacci numbers:");

        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");

            long next = a + b;
            a = b;
            b = next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        System.out.print("Enter n: ");

        try {
            int n = Integer.parseInt(sc.nextLine());
            fibonacci(n);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
        }
    }
}
