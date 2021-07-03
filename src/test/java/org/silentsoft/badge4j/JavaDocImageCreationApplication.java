package org.silentsoft.badge4j;

public class JavaDocImageCreationApplication {

    public static void main(String[] args) throws Exception {
        Utils.createJavaDocImageFile(Badge.builder().style(Style.Flat).label("hello").message("world"));

        for (Style style : Style.values()) {
            Utils.createJavaDocImageFile(Badge.builder().style(style).label("label").message("message"));
            Utils.createJavaDocImageFile(Badge.builder().style(style).label("style").message(style.toString()));
        }

        for (NamedColor namedColor : NamedColor.values()) {
            String color = namedColor.name().toLowerCase();
            Utils.createJavaDocColorImageFile(Badge.builder().style(Style.Flat).label(null).message(color).color(color));
        }
        for (NamedColorAlias namedColorAlias : NamedColorAlias.values()) {
            String color = namedColorAlias.name().toLowerCase();
            Utils.createJavaDocColorImageFile(Badge.builder().style(Style.Flat).label(null).message(color).color(color));
        }

        Utils.createJavaDocColorImageFile(Badge.builder().style(Style.Flat).label(null).message("black").color("black"));
        Utils.createJavaDocColorImageFile(Badge.builder().style(Style.Flat).label(null).message("rebeccapurple").color("rebeccapurple"));
        Utils.createJavaDocColorImageFile(Badge.builder().style(Style.Flat).label(null).message("ff69b4").color("ff69b4"));
        Utils.createJavaDocColorImageFile(Badge.builder().style(Style.Flat).label(null).message("9cf").color("9cf"));
    }

}
