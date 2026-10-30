package main;

import utils.Helper;
import utils.Validate;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        while (true) {
            Helper.menu();
            int choice = Validate.getInt("Your choice:",
                    "Please enter a number.",
                    "Please enter 1 to 4.", 1, 4);
            try {
                switch (choice) {
                    case 1:
                        calculator.addition();
                        break;
                    case 2:
                        calculator.subtraction();
                        break;
                    case 3:
                        calculator.multiplication();
                        break;
                    case 4:
                        return;
                }
            } catch (ArithmeticException e) {
                System.out.println("Result is out of range of integer");
            }
        }
    }
}
