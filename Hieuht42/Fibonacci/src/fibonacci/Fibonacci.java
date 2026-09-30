package fibonacci;

import java.util.Scanner;

public class Fibonacci {

    private static final Scanner sc = new Scanner(System.in);

    public static void fibonacci(int n) { // In số lượng fibo cần in
        long a = 0; // "long" lưu các số fibo trong dãy
        long b = 1;

        System.out.println("First " + n + " Fibonacci number: ");
        
        for (int i = 0; i < n; i++) { // Đếm xem vòng lặp đã chạy bao nhiêu lần
            // Dùng print thay vì println để các số nằm trên cùng 1 dòng
            System.out.print(a + " "); 
            
            long next = a + b;
            a = b;
            b = next;
        }
        
        System.out.println(); // Xuống dòng sau khi in xong dãy số
    }

    public static void main(String[] args) {
        while (true) {
            // Dùng print để con trỏ chuột nằm ngay cạnh chữ "Enter n: "
            System.out.print("Enter n: ");
            
            try {
                // Đã bổ sung dòng lấy dữ liệu nhập vào để hết báo lỗi đỏ
                int n = Integer.parseInt(sc.nextLine()); 

                if (n <= 0) {
                    System.out.println("n must be greater than 0");
                    continue; // Quay lại bắt nhập tiếp
                }

                fibonacci(n);

                // Dùng print để con trỏ chuột nằm ngay cạnh câu hỏi
                System.out.print("Do you want continue (Y/N)? ");
                String chose = sc.nextLine();
                
                if (chose.equalsIgnoreCase("N")) {
                    break; // Thoát vòng lặp nếu nhập N hoặc n
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }
}