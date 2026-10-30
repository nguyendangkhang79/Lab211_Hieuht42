package ui;

import controller.TaskController;
import utils.Validator;

public class Main {

    public static void main(String[] args) {
        TaskController controller = new TaskController();
        while (true) {
            System.out.println("========= Task program =========");
            System.out.println("1. Add Task");
            System.out.println("2. Delete task");
            System.out.println("3. Display Task");
            System.out.println("4. exit");
            int choice = Validator.getInt("Enter your choice: ", 1, 4);
            switch (choice) {
                case 1:
                    controller.addTask();
                    break;
                case 2:
                    controller.deleteTask();
                    break;
                case 3:
                    controller.showTasks();
                    break;
                case 4:
                    return;
            }
        }
    }
}
