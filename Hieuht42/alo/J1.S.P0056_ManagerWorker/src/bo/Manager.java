package bo;

import entity.SalaryHistory;
import entity.SalaryStatus;
import entity.Worker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Manager {

    private final List<Worker> workers = new ArrayList<>();
    private final List<SalaryHistory> histories = new ArrayList<>();

    public Worker getWorker(String code) {
        for (Worker w : workers) {
            if (w.getCode().equalsIgnoreCase(code)) {
                return w;
            }
        }
        return null;
    }

    public boolean addWorker(Worker worker) throws Exception {
        if (worker.getCode() == null || worker.getCode().trim().isEmpty()) {
            throw new Exception("Code cannot be empty!");
        }
        if (getWorker(worker.getCode()) != null) {
            throw new Exception("Code already exists!");
        }
        if (worker.getAge() < 18 || worker.getAge() > 50) {
            throw new Exception("Age must be in range 18 to 50!");
        }
        if (worker.getSalary() <= 0) {
            throw new Exception("Salary must be greater than 0!");
        }
        return workers.add(worker);
    }

    public boolean changeSalary(SalaryStatus status, String code, double amount) {
        Worker worker = getWorker(code);
        if (worker == null || amount <= 0) {
            return false;
        }
        if (status == SalaryStatus.UP) {
            worker.setSalary(worker.getSalary() + amount);
        } else {
            if (worker.getSalary() - amount <= 0) {
                return false;
            }
            worker.setSalary(worker.getSalary() - amount);
        }
        return histories.add(new SalaryHistory(worker, status, new Date()));
    }

    public List<SalaryHistory> getInfomationSalary() {
        List<SalaryHistory> list = new ArrayList<>(histories);
        Collections.sort(list);
        return list;
    }
}
