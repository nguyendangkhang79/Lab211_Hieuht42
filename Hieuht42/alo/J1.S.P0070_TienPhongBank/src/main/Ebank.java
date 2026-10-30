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
        accounts.add(new Account("0123456789", "abc12345"));
        accounts.add(new Account("1234567890", "tpbank2026"));
        accounts.add(new Account("9876543210", "ebank1234"));
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
        if (!accountNumber.matches(IConstant.ACCOUNT_NUMBER)) {
            return bundle.getString("accountInvalid");
        }
        if (findAccount(accountNumber) == null) {
            return bundle.getString("accountNotExist");
        }
        return "";
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

    public void login() {
        Account account;
        while (true) {
            String accountNumber = Validate.getString(bundle.getString("account") + " ");
            String error = checkAccountNumber(accountNumber);
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
}
