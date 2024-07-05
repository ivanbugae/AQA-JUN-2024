package org.prog.junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MyJunitTests3 {

    @BeforeAll
    public static void setUp() {
        System.out.println("Before all:");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Starting test...");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("--------------------");
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("End of tests");
    }

    @Test
    public void test1() {
        System.out.println("1");
    }

    @Test
    public void test2() {
        System.out.println("2");
        throw new RuntimeException("asdasd");
    }

    @ParameterizedTest
    @MethodSource("testArgs")
    public void test3(String userName, String password) {
        System.out.println("Login attempt with " + userName + " : " + password);
    }

    @ParameterizedTest
    @MethodSource("testArgs")
    public void test4(String userName) {
        System.out.println("Print username " + userName);
    }

    private static Stream<Arguments> testArgs() {
        return Stream.of(
                Arguments.of("userName_1", "password_1"),
                Arguments.of("userName_2", "password_2"),
                Arguments.of("userName_3", "password_3"),
                Arguments.of("userName_4", "password_4")
        );
    }
}
