package org.prog.collections;

import org.prog.Car;

import java.util.*;

public class MapsDemo {

    public static void main(String[] args) {
        Map<String, String> myMap = new HashMap<>();
        Map<String, String> myMap2 = new HashMap<>();
        myMap.put("unique_key_1", "non-uqniue_value_initial");
        myMap.put("unique_key_2", "non-uqniue_value");
        myMap.put("unique_key_3", "non-uqniue_value");

        myMap2.put("unique_key_3", "non-uqniue_value_2");
        myMap2.put("unique_key_4", "non-uqniue_value");
        myMap2.put("unique_key_5", "non-uqniue_value");

        myMap.putAll(myMap2);

        System.out.println(myMap);

        Map<String, List<String>> listMap = new HashMap<>();
        listMap.put("invited_guests", new ArrayList<>());
        listMap.put("unvited_guests", new ArrayList<>());
        listMap.put("missing_guests", new ArrayList<>());
        listMap.put("hotel_staff", new ArrayList<>());
        listMap.put("hotel_guests", new ArrayList<>());

    }
}
