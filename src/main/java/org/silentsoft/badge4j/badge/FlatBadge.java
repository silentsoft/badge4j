package org.silentsoft.badge4j.badge;

public class FlatBadge extends Badge {

    public FlatBadge(String label, String message, String color, String labelColor, String[] links, String logo, int logoWidth) {
        super(label, message, color, labelColor, links, logo, logoWidth);
    }

    @Override
    protected int getHeight() {
        return 20;
    }

    @Override
    protected int getVerticalMargin() {
        return 0;
    }

    @Override
    protected boolean hasShadow() {
        return true;
    }

    @Override
    public String render() {
        StringBuilder builder = new StringBuilder();
        builder.append("<linearGradient id=\"s\" x2=\"0\" y2=\"100%\">");
        builder.append("<stop offset=\"0\" stop-color=\"#bbb\" stop-opacity=\".1\"/>");
        builder.append("<stop offset=\"1\" stop-opacity=\".1\"/>");
        builder.append("</linearGradient>");
        builder.append("<clipPath id=\"r\">");
        builder.append(String.format("<rect width=\"%s\" height=\"%d\" rx=\"3\" fill=\"#fff\"/>", toString(getWidth()), getHeight()));
        builder.append("</clipPath>");
        builder.append("<g clip-path=\"url(#r)\">");
        builder.append(String.format("<rect width=\"%s\" height=\"%d\" fill=\"%s\"/>", toString(getLeftWidth()), getHeight(), labelColor));
        builder.append(String.format("<rect x=\"%s\" width=\"%s\" height=\"%d\" fill=\"%s\"/>", toString(getLeftWidth()), toString(getRightWidth()), getHeight(), color));
        builder.append(String.format("<rect width=\"%s\" height=\"%d\" fill=\"url(#s)\"/>", toString(getWidth()), getHeight()));
        builder.append("</g>");
        builder.append(String.format("<g fill=\"#fff\" text-anchor=\"middle\" font-family=\"%s\" text-rendering=\"geometricPrecision\" font-size=\"110\">", getFontFamily()));
        builder.append(renderLogo());
        builder.append(renderLabel());
        builder.append(renderMessage());
        builder.append("</g>");
        return renderBadge(builder.toString());
    }
}
