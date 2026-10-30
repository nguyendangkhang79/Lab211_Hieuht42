package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.Report;
import model.Student;

public class StudentManager {

    private final ArrayList<Student> students = new ArrayList<>();

    public int size() {
        return students.size();
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String findNameById(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student.getStudentName();
            }
        }
        return null;
    }

    public boolean isDuplicate(String id, String semester, String courseName, Student ignored) {
        for (Student student : students) {
            if (student != ignored
                    && student.getId().equalsIgnoreCase(id)
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
            if (report.getId().equalsIgnoreCase(student.getId())
                    && report.getCourseName().equals(student.getCourseName())) {
                return report;
            }
        }
        return null;
    }
}
