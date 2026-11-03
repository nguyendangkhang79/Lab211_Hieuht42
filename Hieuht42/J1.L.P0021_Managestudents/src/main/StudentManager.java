package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.Report;
import model.Student;

public class StudentManager {

    private final ArrayList<Student> students = new ArrayList<>();
    private int count = 10;

    public StudentManager() {
        students.add(new Student("FPT01", "Khang", "FA26", "Java"));
        students.add(new Student("FPT02", "Khanh", "FA26", ".Net"));
        students.add(new Student("FPT03", "Long", "FA26", "Java"));
        students.add(new Student("FPT04", "Linh", "FA26", "Java"));
        students.add(new Student("FPT05", "Minh", "FA26", ".Net"));
        students.add(new Student("FPT06", "Vuong", "FA26", "Java"));
        students.add(new Student("FPT07", "Hanh", "FA26", ".Net"));
        students.add(new Student("FPT08", "Thanh", "FA26", "Java"));
        students.add(new Student("FPT09", "An", "FA26", "Java"));
        students.add(new Student("FPT10", "Nhi", "FA26", ".Net"));
    }

    public int size() {
        return students.size();
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String findIdByName(String name) {
        for (Student student : students) {
            if (student.getStudentName().equalsIgnoreCase(name)) {
                return student.getId();
            }
        }
        return null;
    }

    public String generateId() {
        count++;
        return String.format("FPT%02d", count);
    }

    public boolean isDuplicate(String name, String semester, String courseName, Student ignored) {
        for (Student student : students) {
            if (student != ignored
                    && student.getStudentName().equalsIgnoreCase(name)
                    && student.getSemester().equalsIgnoreCase(semester)
                    && student.getCourseName().equals(courseName)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Student> findById(String id) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                result.add(student);
            }
        }
        return result;
    }

    public ArrayList<Student> findByName(String name) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getStudentName().toLowerCase().contains(name.toLowerCase())) {
                result.add(student);
            }
        }
        Collections.sort(result, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return student1.getStudentName().compareToIgnoreCase(student2.getStudentName());
            }
        });
        return result;
    }

    public void updateStudent(Student student, String studentName, String semester, String courseName) {
        for (Student item : students) {
            if (item.getId().equalsIgnoreCase(student.getId())) {
                item.setStudentName(studentName);
            }
        }
        student.setSemester(semester);
        student.setCourseName(courseName);
    }

    public void deleteStudent(Student student) {
        students.remove(student);
    }

    public ArrayList<Report> getReport() {
        ArrayList<Report> reports = new ArrayList<>();
        for (Student student : students) {
            Report report = findReport(reports, student);
            if (report == null) {
                reports.add(new Report(student.getId(), student.getStudentName(), student.getCourseName()));
            } else {
                report.increaseTotalCourse();
            }
        }
        return reports;
    }

    private Report findReport(ArrayList<Report> reports, Student student) {
        for (Report report : reports) {
            if (report.getStudentName().equalsIgnoreCase(student.getStudentName())
                    && report.getCourseName().equals(student.getCourseName())) {
                return report;
            }
        }
        return null;
    }
}
