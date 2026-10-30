package bo;

import entity.Task;
import entity.TaskType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class TaskManager {

    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String DATE_REGEX = "\\d{2}-\\d{2}-\\d{4}";
    private static final double MIN_TIME = 8.0;
    private static final double MAX_TIME = 17.5;

    private final List<Task> tasks;
    private int lastId;

    public TaskManager() {
        tasks = new ArrayList<>();
        lastId = 0;
    }

    public int addTask(String requirementName, String assignee, String reviewer,
            String taskTypeId, String date, String planFrom, String planTo) throws Exception {
        try {
            String name = checkText(requirementName, "Requirement Name");
            String assigneeName = checkText(assignee, "Assignee");
            String reviewerName = checkText(reviewer, "Reviewer");
            int typeId = parseTaskType(taskTypeId);
            Date taskDate = parseDate(date);
            double from = parseTime(planFrom, "Plan From");
            double to = parseTime(planTo, "Plan To");
            if (from >= to) {
                throw new Exception("Plan From must be less than Plan To.");
            }
            Task task = new Task(lastId + 1, typeId, name, taskDate,
                    from, to, assigneeName, reviewerName);
            tasks.add(task);
            lastId++;
            return task.getId();
        } catch (NullPointerException e) {
            throw new Exception("Data must not be null.");
        }
    }

    public void deleteTask(String id) throws Exception {
        try {
            int taskId = Integer.parseInt(id.trim());
            Task task = findById(taskId);
            if (task == null) {
                throw new Exception("Task ID does not exist.");
            }
            tasks.remove(task);
        } catch (NumberFormatException e) {
            throw new Exception("ID must be a number.");
        } catch (NullPointerException e) {
            throw new Exception("ID must not be null.");
        }
    }

    public List<Task> getDataTasks() {
        List<Task> result = new ArrayList<>(tasks);
        result.sort(Comparator.comparingInt(Task::getId));
        return result;
    }

    private Task findById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    private String checkText(String value, String field) throws Exception {
        String text = value.trim();
        if (text.isEmpty()) {
            throw new Exception(field + " must not be empty.");
        }
        return text;
    }

    private int parseTaskType(String value) throws Exception {
        try {
            int id = Integer.parseInt(value.trim());
            if (TaskType.getById(id) == null) {
                throw new Exception("Task Type must be from 1 to 4.");
            }
            return id;
        } catch (NumberFormatException e) {
            throw new Exception("Task Type must be a number.");
        }
    }

    private Date parseDate(String value) throws Exception {
        String text = value.trim();
        if (!text.matches(DATE_REGEX)) {
            throw new Exception("Date must be in format " + DATE_FORMAT + ".");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(text);
        } catch (ParseException e) {
            throw new Exception("Date does not exist.");
        }
    }

    private double parseTime(String value, String field) throws Exception {
        try {
            double time = Double.parseDouble(value.trim());
            if (time < MIN_TIME || time > MAX_TIME || time * 2 != Math.floor(time * 2)) {
                throw new Exception(field + " must be 8.0, 8.5, 9.0, ... 17.5.");
            }
            return time;
        } catch (NumberFormatException e) {
            throw new Exception(field + " must be a number.");
        }
    }
}
