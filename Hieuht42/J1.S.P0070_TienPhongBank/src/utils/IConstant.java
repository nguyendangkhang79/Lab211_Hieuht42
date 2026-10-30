package utils;

public interface IConstant {

    int CAPTCHA_LENGTH = 5;
    String ACCOUNT_NUMBER = "^[0-9]{10}$";
    String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,31}$";
}
