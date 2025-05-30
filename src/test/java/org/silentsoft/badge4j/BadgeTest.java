package org.silentsoft.badge4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.silentsoft.simpleicons.Icon;
import org.silentsoft.simpleicons.SimpleIcons;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.function.Consumer;
import java.util.function.Function;

public class BadgeTest {

    @Test
    public void nullTest() {
        Assertions.assertDoesNotThrow(() -> {
            Badge.builder().style(null).label(null).message("message").logo(null).color(null).labelColor(null).links(null).build();
        });

        Assertions.assertThrows(NullPointerException.class, () -> {
            Badge.builder().style(null).label(null).message(null).logo(null).color(null).labelColor(null).links(null).build();
        });
    }

    @Test
    public void logoTest() {
        Assertions.assertEquals(Badge.builder().message("message").logo(null).build(), Badge.builder().message("message").logo("").build());
        Assertions.assertEquals(Badge.builder().message("message").logo(" ").build(), Badge.builder().message("message").logo(" ").build());
        Assertions.assertFalse(Badge.builder().message("message").logo(" ").build().contains("data:image/svg+xml;base64,"));
        Assertions.assertTrue(Badge.builder().message("message").logo("simpleicons").build().contains("data:image/svg+xml;base64,"));
    }

    @Test
    public void simpleTest() {
        for (Style style : Style.values()) {
            Function<String, String> buildLogo = (String logo) -> {
                if (logo.startsWith("data:")) {
                    return logo.toLowerCase();
                }

                Icon simpleIcon = SimpleIcons.get(logo);
                if (simpleIcon != null && simpleIcon.getSvg() != null && simpleIcon.getSvg().length() > 0) {
                    String svg = simpleIcon.getSvg();
                    String adjustedColor = null;
                    if (simpleIcon.getHex() != null && simpleIcon.getHex().length() > 0) {
                        String logoColor = (simpleIcon.getHex().startsWith("#") ? simpleIcon.getHex() : "#".concat(simpleIcon.getHex()));
                        if (style == Style.Social && Brightness.of(logoColor) >= 0.6) {
                            adjustedColor = "#333";
                        } else if (style != Style.Social && Brightness.of(logoColor) <= 0.4) {
                            adjustedColor = "whitesmoke";
                        } else {
                            adjustedColor = logoColor;
                        }
                    }
                    if (adjustedColor != null) {
                        svg = svg.replace("<svg", String.format("<svg fill=\"%s\"", adjustedColor));
                    }
                    return "data:image/svg+xml;base64,".concat(Base64.getEncoder().encodeToString(svg.getBytes(StandardCharsets.UTF_8))).toLowerCase();
                }

                return null;
            };

            String label = "hello".toLowerCase();
            String message = "world".toLowerCase();
            String labelColor = "#555";

            Consumer<String> test = (logo) -> {
                {
                    String svg = Badge.builder().style(style).label(label).message(message).labelColor(labelColor).logo(logo).build().toLowerCase();
                    Assertions.assertTrue(svg.contains(label));
                    Assertions.assertTrue(svg.contains(message));
                    Assertions.assertTrue(svg.contains(buildLogo.apply(logo)));
                }
                {
                    String[] links = new String[]{ "https://silentsoft.org" };
                    String svg = Badge.builder().style(style).label(label).message(message).labelColor(labelColor).logo(logo).links(links).build().toLowerCase();
                    Assertions.assertTrue(svg.contains(label));
                    Assertions.assertTrue(svg.contains(message));
                    Assertions.assertTrue(svg.contains(buildLogo.apply(logo)));
                    Assertions.assertTrue(svg.contains(links[0]));
                }
                {
                    String[] links = new String[]{ "https://left.silentsoft.org", "https://right.silentsoft.org" };
                    String svg = Badge.builder().style(style).label(label).message(message).labelColor(labelColor).logo(logo).links(links).build().toLowerCase();
                    Assertions.assertTrue(svg.contains(label));
                    Assertions.assertTrue(svg.contains(message));
                    Assertions.assertTrue(svg.contains(buildLogo.apply(logo)));
                    Assertions.assertTrue(svg.contains(links[0]));
                    Assertions.assertTrue(svg.contains(links[1]));
                }
                if (style != Style.Social) {
                    for (NamedColor namedColor : NamedColor.values()) {
                        String svg = Badge.builder().style(style).label(label).message(message).logo(logo).color(namedColor.name()).labelColor(namedColor.name()).build().toLowerCase();
                        Assertions.assertTrue(svg.contains(label));
                        Assertions.assertTrue(svg.contains(message));
                        Assertions.assertTrue(svg.contains(buildLogo.apply(logo)));
                        Assertions.assertTrue(svg.contains(namedColor.getHex()));
                    }
                    for (NamedColorAlias namedColorAlias : NamedColorAlias.values()) {
                        String svg = Badge.builder().style(style).label(label).message(message).logo(logo).color(namedColorAlias.name()).labelColor(namedColorAlias.name()).build().toLowerCase();
                        Assertions.assertTrue(svg.contains(label));
                        Assertions.assertTrue(svg.contains(message));
                        Assertions.assertTrue(svg.contains(buildLogo.apply(logo)));
                        Assertions.assertTrue(svg.contains(namedColorAlias.getHex()));
                    }
                }
            };

            test.accept("data:image/svg+xml;base64,Dummy123+LOGO456+data789=");
            test.accept("simpleicons");
            test.accept("github");
        }
    }

    //@Test
    public void idempotentTest() throws IOException {
        String logo = "data:image/svg+xml;base64,Dummy123+LOGO456+data789=";
        for (Style style : Style.values()) {
            compareWithMasterModel(Badge.builder().style(style).label("hello").message("world"));
            compareWithMasterModel(Badge.builder().style(style).label("hello").message("world").logo(logo));
            compareWithMasterModel(Badge.builder().style(style).label("hello").message("world").logo(logo).logoWidth(30), false, false, true);
            compareWithMasterModel(Badge.builder().style(style).label("hello").message("world").links(new String[]{ "https://silentsoft.org" }));
            compareWithMasterModel(Badge.builder().style(style).label("hello").message("world").links(new String[]{ "https://silentsoft.org" }).logo(logo));
            compareWithMasterModel(Badge.builder().style(style).label("hello").message("world").links(new String[]{ "https://silentsoft.org" }).logo(logo).logoWidth(30), false, false, true);
            compareWithMasterModel(Badge.builder().style(style).label("hello").message("world").links(new String[]{ "https://left.silentsoft.org", "https://right.silentsoft.org" }));
            compareWithMasterModel(Badge.builder().style(style).label("hello").message("world").links(new String[]{ "https://left.silentsoft.org", "https://right.silentsoft.org" }).logo(logo));
            compareWithMasterModel(Badge.builder().style(style).label("hello").message("world").links(new String[]{ "https://left.silentsoft.org", "https://right.silentsoft.org" }).logo(logo).logoWidth(30), false, false, true);

            for (NamedColor namedColor : NamedColor.values()) {
                compareWithMasterModel(Badge.builder().style(style).label("hello").message("world").color(namedColor.name()), true, false, false);
                compareWithMasterModel(Badge.builder().style(style).label("hello").message("world").color(namedColor.name()).labelColor(namedColor.name()), true, true, false);
            }
            for (NamedColorAlias namedColorAlias : NamedColorAlias.values()) {
                compareWithMasterModel(Badge.builder().style(style).label("hello").message("world").color(namedColorAlias.name()), true, false, false);
                compareWithMasterModel(Badge.builder().style(style).label("hello").message("world").color(namedColorAlias.name()).labelColor(namedColorAlias.name()), true, true, false);
            }
        }

        for (NamedColor namedColor : NamedColor.values()) {
            String color = namedColor.name().toLowerCase();
            compareWithMasterModel(Badge.builder().style(Style.Flat).label(null).message(color).color(color), true, false, false);
        }
        for (NamedColorAlias namedColorAlias : NamedColorAlias.values()) {
            String color = namedColorAlias.name().toLowerCase();
            compareWithMasterModel(Badge.builder().style(Style.Flat).label(null).message(color).color(color), true, false, false);
        }
    }

    private void compareWithMasterModel(BadgeBuilder builder) throws IOException {
        compareWithMasterModel(builder, false, false, false);
    }

    private void compareWithMasterModel(BadgeBuilder builder, boolean explicitlyProvidedColor, boolean explicitlyProvidedLabelColor, boolean explicitlyProvidedLogoWidth) throws IOException {
        Assertions.assertEquals(Utils.readMasterModelFile(builder, explicitlyProvidedColor, explicitlyProvidedLabelColor, explicitlyProvidedLogoWidth), builder.build());
    }

}
