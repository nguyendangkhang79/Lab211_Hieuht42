package model;

public class Report {

    private String id;
    private String studentName;
    private String courseName;
    private int totalCourse;

    public Report(String id, String studentName, String courseName) {
        this.id = id;
        this.studentName = studentName;
        this.courseName = courseName;
        this.totalCourse = 1;
    }

    public String getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getTotalCourse() {
        return totalCourse;
    }

    public void increaseTotalCourse() {
        totalCourse++;
    }
}
