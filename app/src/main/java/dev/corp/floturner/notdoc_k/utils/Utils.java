package dev.corp.floturner.notdoc_k.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Utils {
    public static DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.FRENCH));

    public static String capitalizeFirstLetter(String str) {
        if (str != null) {
            str = str.toLowerCase();
            String capi = "";
            Scanner scan = new Scanner(str);

            while (scan.hasNext()) {
                String word = scan.next();
                //noinspection StringConcatenationInLoop
                capi += Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";
            }

            return capi.trim();
        }
        else {
            return null;
        }
    }

    public static String formateDate(Date date) {
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd MMMM yyyy 'à' HH:mm:ss", Locale.FRANCE);
            return simpleDateFormat.format(date);
        }
        else {
            return null;
        }
    }

    public static String dateFormate(Date date) {
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
            return simpleDateFormat.format(date);
        }
        else {
            return null;
        }
    }

    public static String dateTimeFormate(Date date) {
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy 'à' HH:mm", Locale.FRANCE);
            return simpleDateFormat.format(date);
        }
        else {
            return null;
        }
    }
}
