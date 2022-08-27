package bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Testing {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("1", "2", "0", "0", "0", "4", "3", "9", "0", "7"));
        List<String> tempList = new ArrayList<>();
        for (String str : list) {
            if (str.equals("0")) {
                tempList.add(str);
            }
        }
        list.removeIf(s -> s.equals("0"));
        System.out.println(list);
        list.addAll(tempList);
        System.out.println(list);
    }
}
