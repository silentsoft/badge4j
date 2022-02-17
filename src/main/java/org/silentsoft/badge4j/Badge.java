package org.silentsoft.badge4j;

import org.silentsoft.csscolor4j.Color;

public class Badge {

    private BadgeBuilder badgeBuilder;

    private Badge() {

    }

    Badge(BadgeBuilder badgeBuilder) {
        this.badgeBuilder = badgeBuilder;
    }

    String build() {
        Style style = (badgeBuilder.style != null) ? badgeBuilder.style : Style.Flat;
        String label = (badgeBuilder.label != null) ? badgeBuilder.label.trim() : null;
        String message = badgeBuilder.message.trim(); // message field is required.
        String color = (badgeBuilder.color != null && badgeBuilder.color.length() > 0) ? badgeBuilder.color : "#4c1";
        {
            color = color.trim();
            color = (NamedColor.nameOf(color) != null) ? NamedColor.nameOf(color).getHex() : (NamedColorAlias.nameOf(color) != null) ? NamedColorAlias.nameOf(color).getHex() : color;
            if (color.startsWith("#") == false && org.silentsoft.csscolor4j.NamedColor.nameOf(color) == null) {
                color = Color.valueOf(color).getHex();
            }
        }
        String labelColor = (badgeBuilder.labelColor != null && badgeBuilder.labelColor.length() > 0) ? badgeBuilder.labelColor : "#555";
        {
            labelColor = labelColor.trim();
            labelColor = (NamedColor.nameOf(labelColor) != null) ? NamedColor.nameOf(labelColor).getHex() : (NamedColorAlias.nameOf(labelColor) != null) ? NamedColorAlias.nameOf(labelColor).getHex() : labelColor;
            if (labelColor.startsWith("#") == false && org.silentsoft.csscolor4j.NamedColor.nameOf(labelColor) == null) {
                labelColor = Color.valueOf(labelColor).getHex();
            }
        }
        String[] links = badgeBuilder.links;
        String logo = badgeBuilder.logo;
        int logoWidth = badgeBuilder.logoWidth;
        if (logoWidth <= 0) {
            if (logo == null || "".equals(logo)) {
                logoWidth = 0;
            } else {
                logoWidth = 14;
            }
        }

        return stripXmlWhitespace(BadgeRenderer.get(style).render(label, message, color, labelColor, links, logo, logoWidth));
    }

    private String stripXmlWhitespace(String value) {
        return value.replaceAll(">\\s+", ">").replaceAll("<\\s+", "<");
    }

    public static BadgeBuilder builder() {
        return new BadgeBuilder();
    }

}
