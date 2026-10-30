package main;

import java.util.Locale;
import utils.Helper;
import utils.Validate;

public class Main {

    public static void main(String[] args) {
        Ebank ebank = new Ebank();
        while (true) {
            Helper.menu();
            int choice = Validate.getInt("Please choice one option: ",
                    "Invalid input! Please enter a number.",
                    "Invalid input! Please enter 1 to 3.", 1, 3);
            switch (choice) {
                case 1:
                    ebank.setLocate(Locale.forLanguageTag("vi-VN"));
                    ebank.login();
                    break;
                case 2:
                    ebank.setLocate(Locale.forLanguageTag("en-US"));
                    ebank.login();
                    break;
                case 3:
                    return;
            }
        }
    }
}
