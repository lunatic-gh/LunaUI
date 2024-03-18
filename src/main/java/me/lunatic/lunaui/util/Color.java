package me.lunatic.lunaui.util;

public class Color {

    public static int of(int r, int g, int b, int a) {
        return ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF));
    }

    public static int of(int r, int g, int b) {
        return of(r, g, b, 255);
    }
}
