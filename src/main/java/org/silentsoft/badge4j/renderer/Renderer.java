package org.silentsoft.badge4j.renderer;

public interface Renderer {

    String render(String label, String message, String color, String labelColor, String[] links, String logo, int logoWidth);

}
