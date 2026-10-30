package main;

import java.util.HashMap;
import java.util.LinkedHashMap;
import model.Doctor;

public class DoctorHash {

    private final HashMap<String, Doctor> doctors = new LinkedHashMap<>();

    public Doctor findDoctor(String code) {
        return doctors.get(code);
    }

    public boolean checkAvailability(int availability) {
        return availability >= 0;
    }

    public boolean addDoctor(Doctor doctor) throws Exception {
        if (doctors == null) {
            throw new Exception("Database does not exist");
        }
        if (doctor == null) {
            throw new Exception("Data does not exist");
        }
        if (doctors.containsKey(doctor.getCode())) {
            throw new Exception("Doctor code " + doctor.getCode() + " is duplicate");
        }
        if (!checkAvailability(doctor.getAvailability())) {
            throw new Exception("Availability must be >= 0");
        }
        doctors.put(doctor.getCode(), doctor);
        return true;
    }

    public boolean updateDoctor(Doctor doctor) throws Exception {
        if (doctors == null) {
            throw new Exception("Database does not exist");
        }
        if (doctor == null) {
            throw new Exception("Data does not exist");
        }
        if (!doctors.containsKey(doctor.getCode())) {
            throw new Exception("Doctor code doesn't exist");
        }
        if (!checkAvailability(doctor.getAvailability())) {
            throw new Exception("Availability must be >= 0");
        }
        doctors.put(doctor.getCode(), doctor);
        return true;
    }

    public boolean deleteDoctor(Doctor doctor) throws Exception {
        if (doctors == null) {
            throw new Exception("Database does not exist");
        }
        if (doctor == null) {
            throw new Exception("Data does not exist");
        }
        if (!doctors.containsKey(doctor.getCode())) {
            throw new Exception("Doctor code doesn't exist");
        }
        doctors.remove(doctor.getCode());
        return true;
    }

    public HashMap<String, Doctor> searchDoctor(String input) throws Exception {
        if (doctors == null) {
            throw new Exception("Database does not exist");
        }
        HashMap<String, Doctor> result = new LinkedHashMap<>();
        String keyword = input.toLowerCase();
        for (Doctor doctor : doctors.values()) {
            if (doctor.getCode().toLowerCase().contains(keyword)
                    || doctor.getName().toLowerCase().contains(keyword)
                    || doctor.getSpecialization().toLowerCase().contains(keyword)
                    || String.valueOf(doctor.getAvailability()).contains(keyword)) {
                result.put(doctor.getCode(), doctor);
            }
        }
        return result;
    }
}
