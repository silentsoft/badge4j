package org.silentsoft.badge4j.renderer;

import org.silentsoft.badge4j.badge.SocialBadge;

public class SocialRenderer implements Renderer {

    @Override
    public String render(String label, String message, String color, String labelColor, String[] links, String logo, int logoWidth) {
        return new SocialBadge(capitalize(label), message, color, labelColor, links, logo, logoWidth).render();
    }

    private String capitalize(String value) {
        return value.substring(0, 1).toUpperCase().concat(value.substring(1));
    }

}
