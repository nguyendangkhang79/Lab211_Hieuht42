package main;

import java.util.ArrayList;
import model.Report;
import model.Student;
import utils.Helper;
import utils.Validate;

public class Main {

    private static final int MIN_STUDENTS = 10;
    private static final StudentManager MANAGER = new StudentManager();

    public static void main(String[] args) {
        while (true) {
            Helper.menu();
            int choice = Validate.getInt("Enter your choice: ", 1, 5);
            switch (choice) {
                case 1:
                    create();
                    break;
                case 2:
                    findAndSort();
                    break;
                case 3:
                    updateOrDelete();
                    break;
                case 4:
                    report();
                    break;
                case 5:
                    return;
            }
        }
    }

    private static void create() {
        while (true) {
            System.out.println("----- Student " + (MANAGER.size() + 1) + " -----");
            String id = Validate.getString("Enter ID: ").toUpperCase();
            String name = MANAGER.findNameById(id);
            if (name == null) {
                name = Validate.getString("Enter student name: ");
            } else {
                System.out.println("Student name: " + name);
            }
            String semester = Validate.getString("Enter semester: ");
            String course = Validate.getCourse("Enter course name (Java, .Net, C/C++): ");
            if (MANAGER.isDuplicate(id, semester, course, null)) {
                System.out.println("Student already exists.");
            } else {
                MANAGER.addStudent(new Student(id, name, semester, course));
                System.out.println("Add student successfully.");
            }
            if (MANAGER.size() >= MIN_STUDENTS
                    && Validate.getOption("Do you want to continue (Y/N)? ", "Y", "N").equals("N")) {
                return;
            }
        }
    }

    private static void findAndSort() {
        if (MANAGER.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        String name = Validate.getString("Enter student name: ");
        ArrayList<Student> result = MANAGER.findByName(name);
        if (result.isEmpty()) {
            System.out.println("Not found student.");
            return;
        }
        System.out.printf("%-20s%-15s%s%n", "Student name", "Semester", "Course Name");
        for (Student student : result) {
            System.out.printf("%-20s%-15s%s%n", student.getStudentName(),
                    student.getSemester(), student.getCourseName());
        }
    }

    private static void updateOrDelete() {
        if (MANAGER.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        String id = Validate.getString("Enter ID: ").toUpperCase();
        ArrayList<Student> found = MANAGER.findById(id);
        if (found.isEmpty()) {
            System.out.println("Not found student.");
            return;
        }
        Student student = chooseStudent(found);
        if (Validate.getOption("Do you want to update (U) or delete (D) student? ", "U", "D").equals("U")) {
            update(student);
        } else {
            MANAGER.deleteStudent(student);
            System.out.println("Delete successfully.");
        }
    }

    private static Student chooseStudent(ArrayList<Student> found) {
        System.out.printf("%-5s%-10s%-20s%-15s%s%n", "No.", "ID", "Student name", "Semester", "Course Name");
        for (int i = 0; i < found.size(); i++) {
            Student student = found.get(i);
            System.out.printf("%-5d%-10s%-20s%-15s%s%n", i + 1, student.getId(),
                    student.getStudentName(), student.getSemester(), student.getCourseName());
        }
        if (found.size() == 1) {
            return found.get(0);
        }
        return found.get(Validate.getInt("Choose student: ", 1, found.size()) - 1);
    }

    private static void update(Student student) {
        String name = Validate.getString("Enter student name: ");
        String semester = Validate.getString("Enter semester: ");
        String course = Validate.getCourse("Enter course name (Java, .Net, C/C++): ");
        if (MANAGER.isDuplicate(student.getId(), semester, course, student)) {
            System.out.println("Student already exists.");
            return;
        }
        MANAGER.updateStudent(student, name, semester, course);
        System.out.println("Update successfully.");
    }

    private static void report() {
        if (MANAGER.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        for (Report report : MANAGER.getReport()) {
            System.out.println(report.getStudentName() + " | " + report.getCourseName()
                    + " | " + report.getTotalCourse());
        }
    }
}
