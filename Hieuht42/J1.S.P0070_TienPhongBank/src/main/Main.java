package main;

import java.util.Locale;
import utils.Helper;
import utils.Validate;

public class Main {
    public static void main(String[] args) {
        Ebank ebank = new Ebank();
        
        while (true) {
            // Function 1: Display a menu and ask users to select an option[cite: 1]
            Helper.menu();
            int choice = Validate.getInt("Please choice one option: ", 
                                         "Invalid input! Please enter a number.", 
                                         "Invalid input! Please enter 1 to 3.", 1, 3);
            
            // Function 2: Perform language based on the selected option[cite: 1]
            switch (choice) {
                case 1:
                    ebank.setLocate(new Locale("vi", "VN"));
                    ebank.login();
                    break;
                case 2:
                    ebank.setLocate(new Locale("en", "US"));
                    ebank.login();
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }       
    }
}