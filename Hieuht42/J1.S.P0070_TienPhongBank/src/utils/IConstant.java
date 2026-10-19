package utils;

public class IConstant {
    public static final int CAPTCHA_LENGTH = 5;
    public static final String ACCOUNT_NUMBER = "^[0-9]{10}$";
    // Regex bắt buộc có cả chữ và số, độ dài 8-31 ký tự
    public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,31}$";
}