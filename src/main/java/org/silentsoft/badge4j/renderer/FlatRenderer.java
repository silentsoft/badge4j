package org.silentsoft.badge4j.renderer;

import org.silentsoft.badge4j.badge.FlatBadge;

public class FlatRenderer implements Renderer {

    @Override
    public String render(String label, String message, String color, String labelColor, String[] links, String logo, int logoWidth) {
        return new FlatBadge(label, message, color, labelColor, links, logo, logoWidth).render();
    }

}
