package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import model.Account;
import utils.IConstant;
import utils.Validate;

public class Ebank {

    private ResourceBundle bundle;
    private final List<Account> accounts = new ArrayList<>();

    public Ebank() {
        addAccount("0123456789", "abc12345");
        addAccount("1234567890", "tpbank2026");
        addAccount("9876543210", "ebank1234");
    }

    private boolean addAccount(String accountNumber, String password) {
        if (!accountNumber.matches(IConstant.ACCOUNT_NUMBER)
                || !password.matches(IConstant.PASSWORD)
                || findAccount(accountNumber) != null) {
            return false;
        }
        accounts.add(new Account(accountNumber, password));
        return true;
    }

    private Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void setLocate(Locale locale) {
        if (locale.getLanguage().equals("vi")) {
            bundle = ResourceBundle.getBundle("resources.Vi", locale);
        } else {
            bundle = ResourceBundle.getBundle("resources.En", locale);
        }
    }

    public String checkAccountNumber(String accountNumber) {
        if (accountNumber.matches(IConstant.ACCOUNT_NUMBER)) {
            return "";
        }
        return bundle.getString("accountInvalid");
    }

    public String checkPassword(String password) {
        if (password.matches(IConstant.PASSWORD)) {
            return "";
        }
        return bundle.getString("passwordInvalid");
    }

    public String generateCaptcha() {
        String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < IConstant.CAPTCHA_LENGTH; i++) {
            char c = alphaNumeric.charAt(random.nextInt(alphaNumeric.length()));
            captcha.append(c);
        }
        return captcha.toString();
    }

    public String checkCaptcha(String captchaInput, String captchaGenerate) {
        if (!captchaInput.isEmpty() && captchaGenerate.contains(captchaInput)) {
            return "";
        }
        return bundle.getString("captchaInvalid");
    }

    public void showMenu() {
        while (true) {
            System.out.println(bundle.getString("menuTitle"));
            System.out.println("1. " + bundle.getString("menuLogin"));
            System.out.println("2. " + bundle.getString("menuCreate"));
            System.out.println("3. " + bundle.getString("menuBack"));
            int choice = Validate.getInt(bundle.getString("choice") + " ",
                    bundle.getString("choiceNumber"),
                    bundle.getString("choiceRange"), 1, 3);
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    return;
            }
        }
    }

    public void login() {
        Account account;
        while (true) {
            String accountNumber = Validate.getString(bundle.getString("account") + " ");
            String error = checkAccountNumber(accountNumber);
            if (error.isEmpty() && findAccount(accountNumber) == null) {
                error = bundle.getString("accountNotExist");
            }
            if (error.isEmpty()) {
                account = findAccount(accountNumber);
                break;
            }
            System.out.println(error);
        }

        while (true) {
            String password = Validate.getString(bundle.getString("password") + " ");
            String error = checkPassword(password);
            if (error.isEmpty() && !account.getPassword().equals(password)) {
                error = bundle.getString("passwordWrong");
            }
            if (error.isEmpty()) {
                break;
            }
            System.out.println(error);
        }

        String captchaGenerate = generateCaptcha();
        System.out.println(bundle.getString("captcha") + " " + captchaGenerate);
        while (true) {
            String captchaInput = Validate.getString(bundle.getString("inputCaptcha") + " ");
            String error = checkCaptcha(captchaInput, captchaGenerate);
            if (error.isEmpty()) {
                break;
            }
            System.out.println(error);
        }

        System.out.println(bundle.getString("loginSuccess"));
    }

    public void createAccount() {
        String accountNumber;
        while (true) {
            accountNumber = Validate.getString(bundle.getString("account") + " ");
            String error = checkAccountNumber(accountNumber);
            if (error.isEmpty() && findAccount(accountNumber) != null) {
                error = bundle.getString("accountExist");
            }
            if (error.isEmpty()) {
                break;
            }
            System.out.println(error);
        }

        String password;
        while (true) {
            password = Validate.getString(bundle.getString("password") + " ");
            String error = checkPassword(password);
            if (error.isEmpty()) {
                break;
            }
            System.out.println(error);
        }

        addAccount(accountNumber, password);
        System.out.println(bundle.getString("createSuccess"));
    }
}