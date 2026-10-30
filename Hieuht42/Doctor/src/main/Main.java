package main;

import java.util.HashMap;
import model.Doctor;
import utils.Helper;
import utils.Validate;

public class Main {

    private static final DoctorHash DOCTOR_HASH = new DoctorHash();

    public static void main(String[] args) {
        while (true) {
            Helper.menu();
            int choice = Validate.getInt("Enter your choice: ", 1, 5);
            try {
                switch (choice) {
                    case 1:
                        addDoctor();
                        break;
                    case 2:
                        updateDoctor();
                        break;
                    case 3:
                        deleteDoctor();
                        break;
                    case 4:
                        searchDoctor();
                        break;
                    case 5:
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void addDoctor() throws Exception {
        System.out.println("--------- Add Doctor ----------");
        String code = Validate.getString("Enter Code: ");
        if (DOCTOR_HASH.findDoctor(code) != null) {
            System.out.println("Doctor code " + code + " is duplicate");
            return;
        }
        String name = Validate.getString("Enter Name: ");
        String specialization = Validate.getString("Enter Specialization: ");
        int availability = Validate.getInt("Enter Availability: ", 0);
        if (DOCTOR_HASH.addDoctor(new Doctor(code, name, specialization, availability))) {
            System.out.println("Add doctor successfully!");
        }
    }

    private static void updateDoctor() throws Exception {
        System.out.println("--------- Update Doctor ---------");
        String code = Validate.getString("Enter Code: ");
        Doctor oldDoctor = DOCTOR_HASH.findDoctor(code);
        if (oldDoctor == null) {
            System.out.println("Doctor code doesn't exist");
            return;
        }
        String name = Validate.getStringOrDefault("Enter Name: ", oldDoctor.getName());
        String specialization = Validate.getStringOrDefault("Enter Specialization: ",
                oldDoctor.getSpecialization());
        int availability = Validate.getIntOrDefault("Enter Availability: ", 0,
                oldDoctor.getAvailability());
        if (DOCTOR_HASH.updateDoctor(new Doctor(code, name, specialization, availability))) {
            System.out.println("Update doctor successfully!");
        }
    }

    private static void deleteDoctor() throws Exception {
        System.out.println("--------- Delete Doctor ---------");
        String code = Validate.getString("Enter Code: ");
        Doctor doctor = DOCTOR_HASH.findDoctor(code);
        if (doctor == null) {
            System.out.println("Doctor code doesn't exist");
            return;
        }
        if (DOCTOR_HASH.deleteDoctor(doctor)) {
            System.out.println("Delete doctor successfully!");
        }
    }

    private static void searchDoctor() throws Exception {
        System.out.println("--------- Search Doctor ---------");
        String input = Validate.getStringOrDefault("Enter text: ", "");
        HashMap<String, Doctor> result = DOCTOR_HASH.searchDoctor(input);
        if (result.isEmpty()) {
            System.out.println("No doctor found");
            return;
        }
        System.out.println("--------- Result ---------");
        System.out.printf("%-10s%-20s%-20s%s%n", "Code", "Name", "Specialization", "Availability");
        for (Doctor doctor : result.values()) {
            System.out.printf("%-10s%-20s%-20s%d%n", doctor.getCode(), doctor.getName(),
                    doctor.getSpecialization(), doctor.getAvailability());
        }
    }
}
