package controller;

import bo.TaskManager;
import entity.Task;
import entity.TaskType;
import java.text.SimpleDateFormat;
import java.util.List;
import utils.Validator;

public class TaskController {

    private final TaskManager taskManager;

    public TaskController() {
        taskManager = new TaskManager();
    }

    public void addTask() {
        System.out.println("------------Add Task---------------");
        String requirementName = Validator.getString("Requirement Name: ");
        String taskTypeId = Validator.getString("Task Type: ");
        String date = Validator.getString("Date: ");
        String planFrom = Validator.getString("From: ");
        String planTo = Validator.getString("To: ");
        String assignee = Validator.getString("Assignee: ");
        String reviewer = Validator.getString("Reviewer: ");
        try {
            int id = taskManager.addTask(requirementName, assignee, reviewer,
                    taskTypeId, date, planFrom, planTo);
            System.out.println("Add task successfully. Task ID: " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask() {
        System.out.println("---------Del Task------");
        String id = Validator.getString("ID: ");
        try {
            taskManager.deleteTask(id);
            System.out.println("Delete task successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showTasks() {
        List<Task> tasks = taskManager.getDataTasks();
        if (tasks.isEmpty()) {
            System.out.println("Task list is empty.");
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("----------------------------------------- Task ---------------------------------------");
        System.out.printf("%-7s%-20s%-12s%-15s%-8s%-15s%-15s%n",
                "ID", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
        for (Task task : tasks) {
            System.out.printf("%-7d%-20s%-12s%-15s%-8.1f%-15s%-15s%n",
                    task.getId(),
                    task.getRequirementName(),
                    TaskType.getById(task.getTaskTypeId()).getName(),
                    dateFormat.format(task.getDate()),
                    task.getTime(),
                    task.getAssignee(),
                    task.getReviewer());
        }
    }
}
