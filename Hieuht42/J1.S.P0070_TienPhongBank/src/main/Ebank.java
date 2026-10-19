package main;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import utils.IConstant;

public class Ebank {
    private ResourceBundle bundle;
    private final Scanner scanner = new Scanner(System.in);

    // Function 1: Convert the languages[cite: 1]
    public void setLocate(Locale locale) {
        if (locale.getLanguage().equals("vi")) {
            bundle = ResourceBundle.getBundle("resources.Vi", locale);
        } else {
            bundle = ResourceBundle.getBundle("resources.En", locale);
        }
    }

    // Function 2: Check the account number[cite: 1]
    public String checkAccountNumber(String accountNumber) {
        if (accountNumber.matches(IConstant.ACCOUNT_NUMBER)) {
            return ""; 
        }
        return bundle.getString("accountInvalid");
    }

    // Function 3: Check the password[cite: 1]
    public String checkPassword(String password) {
        if (password.matches(IConstant.PASSWORD)) {
            return ""; 
        }
        return bundle.getString("passwordInvalid");
    }

    // Function 4: Generate a random captcha code[cite: 1]
    public String generateCaptcha() {
        String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < IConstant.CAPTCHA_LENGTH; i++) {
            captcha.append(alphaNumeric.charAt(random.nextInt(alphaNumeric.length())));
        }
        return captcha.toString();
    }

    // Function 5: Check the captcha code[cite: 1]
    public String checkCaptcha(String captchaInput, String captchaGenerate) {
        // Sử dụng contains() theo đúng Guideline để người dùng chỉ cần nhập 1 ký tự cũng pass[cite: 1]
        if (captchaGenerate.contains(captchaInput)) {
            return ""; 
        }
        return bundle.getString("captchaInvalid");
    }

    // Function 6: Login luồng xử lý chính[cite: 1]
    public void login() {
        // Nhập Account[cite: 1]
        String account;
        while (true) {
            System.out.print(bundle.getString("account") + " ");
            account = scanner.nextLine().trim();
            String errorMsg = checkAccountNumber(account);
            if (errorMsg.isEmpty()) break;
            System.out.println(errorMsg);
        }

        // Nhập Password[cite: 1]
        String password;
        while (true) {
            System.out.print(bundle.getString("password") + " ");
            password = scanner.nextLine().trim();
            String errorMsg = checkPassword(password);
            if (errorMsg.isEmpty()) break;
            System.out.println(errorMsg);
        }

        // Sinh và nhập Captcha[cite: 1]
        String captchaGen = generateCaptcha();
        System.out.println(bundle.getString("captcha") + " " + captchaGen);
        
        String captchaInput;
        while (true) {
            System.out.print(bundle.getString("inputCaptcha") + " ");
            captchaInput = scanner.nextLine().trim();
            String errorMsg = checkCaptcha(captchaInput, captchaGen);
            if (errorMsg.isEmpty()) break;
            System.out.println(errorMsg);
        }
    }
}