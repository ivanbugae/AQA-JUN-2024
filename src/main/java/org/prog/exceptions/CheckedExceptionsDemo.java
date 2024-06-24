package org.prog.exceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CheckedExceptionsDemo {

    public static void main(String[] args) {
        try {
            smth1();
        } catch (Exception e) {
            System.out.println("oops!" + e.getMessage());
        }
        System.out.println("Done!");
    }

    public static void smth1() {
        smth2();
    }

    public static void smth2() {
        smth3();
    }

    public static void smth3() {
        try {
            smth4();
        } finally {
            System.out.println("=================");
        }
    }

    public static void smth4() {
        try {
            FileReader fileReader = new FileReader(new File("asdasd.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
