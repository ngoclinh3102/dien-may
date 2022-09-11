package bean;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Testing {
    private static String removeAccent(String s) {
        String temp = s.toLowerCase();
        temp = Normalizer.normalize(temp, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
        temp = temp.replaceAll("đ", "d");
        temp = temp.replaceAll(" ", "");

        return temp;
    }

    public static void main(String[] args) {
        System.out.println(removeAccent("Điện thoại iPhone 13 Pro Max"));
    }
}
