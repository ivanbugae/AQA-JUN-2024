package org.prog.exceptions;

public class UncheckedExceptionsDemo {

    public static void main(String[] args) {
        smth1("asuafulagsgd9p7y129p78y3e 8p7yasdp789t as");
        smth1(null);
        System.out.println("this will now work");
    }

    public static void smth1(String s) {
        smth2(s);
    }

    public static void smth2(String s) {
        try {
            smth3(s);
        } catch (NullPointerException npe) {
            System.out.println("String cannot be null!");
        } finally {
            System.out.println("this will always work!");
        }
    }

    public static void smth3(String s) {
        smth4(s);
    }

    public static void smth4(String s) {
        System.out.println(s.length());
    }
}
