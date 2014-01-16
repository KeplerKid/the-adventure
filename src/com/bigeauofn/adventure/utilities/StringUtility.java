package com.bigeauofn.adventure.utilities;

import java.util.Collection;

public class StringUtility {
    /*                                      0    5    10   15   20   25   30   35   40   45   50   55   60  64  */
    public static final String spaces_128 = "                                                                                                                                ";
    public static final String spaces_64 = spaces_128.substring(0, 63);
    public static final String spaces_32 = spaces_64.substring(0, 31);
    public static final String spaces_16 = spaces_32.substring(0, 15);

    public static String spaces(int length) {
        String ret = null;
        if (length > 0 && length < 128) {
            ret = spaces_128.substring(0, length);
        }
        return ret;
    }

    public static int[] minMaxStringLength(Collection<String> strings) {
        int[] ret = new int[2];
        ret[0] = Integer.MAX_VALUE;
        ret[1] = 0;
        for (String s : strings) {
            if (s.length() < ret[0]) {
                ret[0] = s.length();
            }
            if (s.length() > ret[1]) {
                ret[1] = s.length();
            }
        }
        return ret;
    }

    public static int maxStringLength(Collection<String> strings) {
        int ret = 0;
        for (String s : strings) {
            if (s.length() > ret) {
                ret = s.length();
            }
        }
        return ret;
    }

    public static int minStringLength(Collection<String> strings) {
        int ret = Integer.MAX_VALUE;
        for (String s : strings) {
            if (s.length() < ret) {
                ret = s.length();
            }
        }
        return ret;
    }
}
