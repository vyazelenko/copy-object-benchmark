package com.vyazelenko.blog.copyobject;

public final class HashUtils {
    private HashUtils() {
    }

    public static int doubleHash(double value) {
        return longHash(Double.doubleToLongBits(value));
    }

    public static int longHash(long value) {
        return (int) (value ^ (value >>> 32));
    }

    public static int objectHash(Object value) {
        return value != null ? value.hashCode() : 0;
    }
}
