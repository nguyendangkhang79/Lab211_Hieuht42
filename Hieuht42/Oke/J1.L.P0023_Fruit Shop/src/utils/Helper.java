package utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Helper {

    private static final DecimalFormat MONEY_FORMAT
            = new DecimalFormat("0.##", DecimalFormatSymbols.getInstance(Locale.US));

    public static void menu() {
        System.out.println("FRUIT SHOP SYSTEM");
        System.out.println("1. Create Fruit");
        System.out.println("2. View orders");
        System.out.println("3. Shopping (for buyer)");
        System.out.println("4. Exit");
        System.out.println("(Please choose 1 to create product, 2 to view order, "
                + "3 for shopping, 4 to Exit program).");
    }

    public static String formatMoney(double money) {
        return MONEY_FORMAT.format(money) + "$";
    }
}
