package com.bigeauofn.adventure.map.errors;

import java.io.PrintStream;

public class ErrorReporter {
    public static void print(String error, Class foo, PrintStream stream) {
        stream.print(error + " : " + foo.getName());
    }

    public static void println(String error, Class foo, PrintStream stream) {
        stream.println(error + " : " + foo.getName());
    }

    public static void print(String error, Class foo) {
        print(error, foo, System.err);
    }

    public static void println(String error, Class foo) {
        println(error, foo, System.err);
    }
}
