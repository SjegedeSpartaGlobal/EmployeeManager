package com.sparta.sj.Model;

public class Timer {
    private static long start;
    private static long end;
    private static long runtime;

    public static long getStart() {
        return start;
    }

    public static void setStart(long st) {
        start = st;
    }

    public static long getEnd() {
        return end;
    }

    public static void setEnd(long e) {
        end = e;
    }

    public static long getRuntime() {
        return end - start;
    }




}
