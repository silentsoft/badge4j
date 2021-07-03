package org.silentsoft.badge4j;

import org.silentsoft.csscolor4j.Color;

public class Brightness {

    private Brightness() {

    }

    public static double of(String color) {
        return of(Color.valueOf(color));
    }

    public static double of(Color color) {
        return ((color.getRed() * 299) + (color.getGreen() * 587) + (color.getBlue() * 114)) / 255000.0;
    }

}
