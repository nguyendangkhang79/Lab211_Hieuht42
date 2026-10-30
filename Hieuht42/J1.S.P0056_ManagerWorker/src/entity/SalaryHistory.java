package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryHistory implements Comparable<SalaryHistory> {

    private String code;
    private String name;
    private int age;
    private double salary;
    private SalaryStatus status;
    private Date date;

    public SalaryHistory(Worker worker, SalaryStatus status, Date date) {
        this.code = worker.getCode();
        this.name = worker.getName();
        this.age = worker.getAge();
        this.salary = worker.getSalary();
        this.status = status;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public SalaryStatus getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int compareTo(SalaryHistory o) {
        return code.compareTo(o.code);
    }

    @Override
    public String toString() {
        return String.format("%-8s%-15s%-6d%-12.0f%-8s%s", code, name, age, salary, status,
                new SimpleDateFormat("dd/MM/yyyy").format(date));
    }
}
