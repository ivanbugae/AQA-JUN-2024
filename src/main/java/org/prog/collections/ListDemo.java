package org.prog.collections;

import org.prog.Car;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {

    public static void main(String[] args) {
        List<String> sList = new ArrayList<>();
        List<String> lList = new LinkedList<>();
        List<String> validationList = new LinkedList<>();

        sList.add("a");
        sList.add("b");
        sList.add("c");
        sList.add("d");
        sList.add("e");

        /*
            0:a;
            1:b;
            ...
            200:c; << read this
            201:f; << add this
            ...
            302:d;
            303:e;
         */

        lList.add("a1");
        lList.add("b2");
        lList.add("c3");
        lList.add("d4");
        lList.add("e5");

        /*
            <start_of_list>-<a>-<b>-...100...-<c>-<d>-...100...-<e>-<end_of_list>
            ...-<c>-<f>
            <f>-<d>-...
            ...-<c>-<f>-<d>-...
         */

        validationList.add("c");
        validationList.add("c3");

        System.out.println(sList.size());
        sList.addAll(lList);
        System.out.println(sList.size());
        sList.remove("a");
        sList.remove(2);
        System.out.println(sList.size());

        System.out.println(sList.contains("a1"));
        System.out.println(sList.containsAll(validationList));
        System.out.println(sList.removeAll(validationList));
        System.out.println(sList.size());
        System.out.println(sList.containsAll(validationList));

        for (int i = 0; i < sList.size(); i++) {
            System.out.println(sList.get(i));
        }

        for (String tempString : sList) {
            System.out.println(tempString);
        }
    }
}
