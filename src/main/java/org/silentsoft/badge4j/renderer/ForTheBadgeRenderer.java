package org.silentsoft.badge4j.renderer;

import org.silentsoft.badge4j.badge.ForTheBadge;

public class ForTheBadgeRenderer implements Renderer {

    @Override
    public String render(String label, String message, String color, String labelColor, String[] links, String logo, int logoWidth) {
        return new ForTheBadge(label.toUpperCase(), message.toUpperCase(), color, labelColor, links, logo, logoWidth).render();
    }

}
