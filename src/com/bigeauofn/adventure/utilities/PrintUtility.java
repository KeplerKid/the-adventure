package com.bigeauofn.adventure.utilities;

import java.io.PrintStream;

public class PrintUtility {
    protected static final String defaultDelim = ", ";

    public static void printArray(Object[] array, PrintStream out, String delim, boolean lastDelim) {
        if (array != null) {
            for (int i = 0; i < array.length - 1; i++) {
                out.print(array[i].toString());
                out.print(delim);
            }
            out.print(array[array.length - 1].toString());
            if (lastDelim) {
                out.print(delim);
            }
        }
    }

    public static void printArray(Object[] array, PrintStream out) {
        printArray(array, out, defaultDelim, false);
    }

    public static void printArray(Object[] array) {
        printArray(array, System.out, defaultDelim, false);
    }

    public static void printArray(double[] array, PrintStream out, String delim, boolean lastDelim) {
        if (array != null) {
            for (int i = 0; i < array.length - 1; i++) {
                out.print(array[i]);
                out.print(delim);
            }
            out.print(array[array.length - 1]);
            if (lastDelim) {
                out.print(delim);
            }
        }
    }

    public static void printArray(double[] array, PrintStream out) {
        printArray(array, out, defaultDelim, false);
    }

    public static void printArray(double[] array) {
        printArray(array, System.out, defaultDelim, false);
    }
}
