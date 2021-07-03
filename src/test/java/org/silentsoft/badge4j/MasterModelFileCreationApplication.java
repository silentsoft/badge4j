package org.silentsoft.badge4j;

public class MasterModelFileCreationApplication {

    public static void main(String[] args) throws Exception {
        String logo = "data:image/svg+xml;base64,Dummy123+LOGO456+data789=";
        for (Style style : Style.values()) {
            Utils.createMasterModelFile(Badge.builder().style(style).label("hello").message("world"));
            Utils.createMasterModelFile(Badge.builder().style(style).label("hello").message("world").logo(logo));
            Utils.createMasterModelFile(Badge.builder().style(style).label("hello").message("world").logo(logo).logoWidth(30), false, false, true);
            Utils.createMasterModelFile(Badge.builder().style(style).label("hello").message("world").links(new String[]{ "https://silentsoft.org" }));
            Utils.createMasterModelFile(Badge.builder().style(style).label("hello").message("world").links(new String[]{ "https://silentsoft.org" }).logo(logo));
            Utils.createMasterModelFile(Badge.builder().style(style).label("hello").message("world").links(new String[]{ "https://silentsoft.org" }).logo(logo).logoWidth(30), false, false, true);
            Utils.createMasterModelFile(Badge.builder().style(style).label("hello").message("world").links(new String[]{ "https://left.silentsoft.org", "https://right.silentsoft.org" }));
            Utils.createMasterModelFile(Badge.builder().style(style).label("hello").message("world").links(new String[]{ "https://left.silentsoft.org", "https://right.silentsoft.org" }).logo(logo));
            Utils.createMasterModelFile(Badge.builder().style(style).label("hello").message("world").links(new String[]{ "https://left.silentsoft.org", "https://right.silentsoft.org" }).logo(logo).logoWidth(30), false, false, true);

            for (NamedColor namedColor : NamedColor.values()) {
                Utils.createMasterModelFile(Badge.builder().style(style).label("hello").message("world").color(namedColor.name()), true, false, false);
                Utils.createMasterModelFile(Badge.builder().style(style).label("hello").message("world").color(namedColor.name()).labelColor(namedColor.name()), true, true, false);
            }
            for (NamedColorAlias namedColorAlias : NamedColorAlias.values()) {
                Utils.createMasterModelFile(Badge.builder().style(style).label("hello").message("world").color(namedColorAlias.name()), true, false, false);
                Utils.createMasterModelFile(Badge.builder().style(style).label("hello").message("world").color(namedColorAlias.name()).labelColor(namedColorAlias.name()), true, true, false);
            }
        }

        for (NamedColor namedColor : NamedColor.values()) {
            String color = namedColor.name().toLowerCase();
            Utils.createMasterModelFile(Badge.builder().style(Style.Flat).label(null).message(color).color(color), true, false, false);
        }
        for (NamedColorAlias namedColorAlias : NamedColorAlias.values()) {
            String color = namedColorAlias.name().toLowerCase();
            Utils.createMasterModelFile(Badge.builder().style(Style.Flat).label(null).message(color).color(color), true, false, false);
        }
    }

}
