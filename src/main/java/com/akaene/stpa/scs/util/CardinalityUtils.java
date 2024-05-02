package com.akaene.stpa.scs.util;

public class CardinalityUtils {

    public static boolean isAny(Integer value) {
        return value == null;
    }

    public static String toString(Integer value) {
        return isAny(value) ? "*" : value.toString();
    }
}
