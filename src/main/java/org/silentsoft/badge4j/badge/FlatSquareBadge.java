package org.silentsoft.badge4j.badge;

public class FlatSquareBadge extends Badge {

    public FlatSquareBadge(String label, String message, String color, String labelColor, String[] links, String logo, int logoWidth) {
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
        return false;
    }

    @Override
    public String render() {
        StringBuilder builder = new StringBuilder();
        builder.append("<g shape-rendering=\"crispEdges\">");
        builder.append(String.format("<rect width=\"%s\" height=\"%d\" fill=\"%s\"/>", toString(getLeftWidth()), getHeight(), labelColor));
        builder.append(String.format("<rect x=\"%s\" width=\"%s\" height=\"%d\" fill=\"%s\"/>", toString(getLeftWidth()), toString(getRightWidth()), getHeight(), color));
        builder.append("</g>");
        builder.append(String.format("<g fill=\"#fff\" text-anchor=\"middle\" font-family=\"%s\" text-rendering=\"geometricPrecision\" font-size=\"110\">", getFontFamily()));
        builder.append(renderLogo());
        builder.append(renderLabel());
        builder.append(renderMessage());
        builder.append("</g>");
        return renderBadge(builder.toString());
    }
}
