package org.prog.collections;

import java.util.HashSet;
import java.util.Set;

public class SetDemo {

    public static void main(String[] args) {
        Set<String> strings = new HashSet<>();
        strings.add("a1");
        strings.add("a2");
        strings.add("a3");
        strings.add("a4");
        System.out.println(strings.size());

        for (String s : strings) {
            System.out.println(s);
        }
    }
}
