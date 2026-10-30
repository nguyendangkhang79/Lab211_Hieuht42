package ui;

import bo.Manager;
import entity.SalaryHistory;
import entity.SalaryStatus;
import entity.Worker;
import java.util.List;
import utils.Validator;

public class Main {

    private static final String CODE_REGEX = "[Ww]\\d+";
    private static final Manager manager = new Manager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("======== Worker Management =========");
            System.out.println("1. Add Worker");
            System.out.println("2. Up salary");
            System.out.println("3. Down salary");
            System.out.println("4. Display Information salary");
            System.out.println("5. Exit");
            int choice = Validator.getInt("Enter your choice: ", 1, 5);
            switch (choice) {
                case 1:
                    addWorker();
                    break;
                case 2:
                    changeSalary(SalaryStatus.UP);
                    break;
                case 3:
                    changeSalary(SalaryStatus.DOWN);
                    break;
                case 4:
                    displaySalary();
                    break;
                case 5:
                    return;
            }
        }
    }

    private static void addWorker() {
        System.out.println("--------- Add Worker ----------");
        String code;
        while (true) {
            code = Validator.getString("Enter Code: ", CODE_REGEX, "Code must be W followed by digits (e.g. W1)!").toUpperCase();
            if (manager.getWorker(code) == null) {
                break;
            }
            System.out.println("Code already exists!");
        }
        String name = Validator.getString("Enter Name: ", "[A-Za-z ]+", "Name contains letters only!");
        int age = Validator.getInt("Enter Age: ", 18, 50);
        double salary = Validator.getPositiveDouble("Enter Salary: ");
        String location = Validator.getString("Enter work location: ", ".+", "Work location cannot be empty!");
        try {
            if (manager.addWorker(new Worker(code, name, age, salary, location))) {
                System.out.println("Add worker successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void changeSalary(SalaryStatus status) {
        System.out.println("------- Up/Down Salary --------");
        String code = Validator.getString("Enter Code: ", CODE_REGEX, "Code must be W followed by digits (e.g. W1)!").toUpperCase();
        if (manager.getWorker(code) == null) {
            System.out.println("Code does not exist!");
            return;
        }
        double amount = Validator.getPositiveDouble("Enter Salary: ");
        if (manager.changeSalary(status, code, amount)) {
            System.out.println("Update salary successfully!");
        } else {
            System.out.println("Salary after decrease must be greater than 0!");
        }
    }

    private static void displaySalary() {
        List<SalaryHistory> list = manager.getInfomationSalary();
        if (list.isEmpty()) {
            System.out.println("No worker has been adjusted salary!");
            return;
        }
        System.out.println("--------------------Display Information Salary-----------------------");
        System.out.printf("%-8s%-15s%-6s%-12s%-8s%s%n", "Code", "Name", "Age", "Salary", "Status", "Date");
        for (SalaryHistory h : list) {
            System.out.println(h);
        }
    }
}
