package org.silentsoft.badge4j.badge;

import org.silentsoft.badge4j.Brightness;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.text.DecimalFormat;

abstract class Badge {

    private static final double brightnessThreshold = 0.69;
    private static final int horizontalPadding = 5;
    private static final DecimalFormat decimalFormat = new DecimalFormat("###.##");

    protected String label;
    protected String message;
    protected String color;
    protected String labelColor;
    protected String[] links;
    protected String logo;
    protected int logoWidth;

    private String accessibleText;

    public Badge(String label, String message, String color, String labelColor, String[] links, String logo, int logoWidth) {
        this.label = label;
        this.message = message;
        this.color = color;
        this.labelColor = labelColor;
        this.links = links;
        this.logo = logo;
        this.logoWidth = logoWidth;

        this.accessibleText = createAccessibleText();

        adjustColors();
    }

    private void adjustColors() {
        labelColor = (hasLabelOrHasLabelColor() || hasLogo()) ? labelColor : color;
        labelColor = escapeXml(labelColor);
        color = escapeXml(color);
    }

    protected int getLabelMargin() {
        return getTotalLogoWidth() + 1;
    }

    protected double getLeftWidth() {
        // FIXME
        TextData textData = renderText(getLabelMargin(), horizontalPadding, label, (hasBothLinks() ? getLeftLink() : null), getHeight(), getVerticalMargin(), hasShadow(), labelColor);
        int labelWidth = textData.width;
        // FIXME int ? double ?
        int leftWidth = hasLabelOrHasLabelColor() ? labelWidth + (2 * horizontalPadding) + getTotalLogoWidth() : 0;
        return leftWidth;
    }

    protected int getMessageMargin() {
        int messageMargin = (int)getLeftWidth() - (message != null && message.length() > 0 ? 1 : 0);
        if (!hasLabelOrHasLabelColor()) {
            if (hasLogo()) {
                messageMargin = messageMargin + getTotalLogoWidth() + horizontalPadding;
            } else {
                messageMargin = messageMargin + 1;
            }
        }
        return messageMargin;
    }

    protected double getRightWidth() {
        TextData textData = renderText(getMessageMargin(), horizontalPadding, message, getRightLink(), getHeight(), getVerticalMargin(), hasShadow(), color);
        int messageWidth = textData.width;
        int rightWidth = messageWidth + (2 * horizontalPadding);
        if (hasLogo() && !hasLabelOrHasLabelColor()) {
            rightWidth += getTotalLogoWidth() + horizontalPadding - 1;
        }
        return rightWidth;
    }

    private boolean hasLabelOrHasLabelColor() {
        // FIXME original was:
        //return (label != null && label.length() > 0) || (labelColor != null && labelColor.length() > 0);
        return hasLabel();
    }

    protected double getWidth() {
        return getLeftWidth() + getRightWidth();
    }

    protected abstract int getHeight();

    protected abstract int getVerticalMargin();

    protected abstract boolean hasShadow();

    public abstract String render();

    protected String getFontFamily() {
        return "Verdana,Geneva,DejaVu Sans,sans-serif";
    }

    protected int getLogoPadding() {
        if (logo != null && message != null && message.length() > 0) {
            return 3;
        }
        return 0;
    }

    protected String renderBadge(String main) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"%s\" height=\"%d\" %s>", decimalFormat.format(getWidth()), getHeight(), renderAriaAttributes()));
        builder.append("\n\n");
        builder.append(renderTitle());
        builder.append("\n");
        builder.append(shouldWrapBodyWithLink() ? String.format("<a target=\"_blank\" xlink:href=\"%s\">%s</a>", getLeftLink(), main) : main);
        builder.append("\n");
        builder.append("</svg>");
        return builder.toString();
    }

    protected String renderLogo() {
        return renderLogo(logo, getHeight(), horizontalPadding, logoWidth).renderedLogo;
    }

    protected int getTotalLogoWidth() {
        return renderLogo(logo, getHeight(), horizontalPadding, logoWidth).totalLogoWidth;
    }

    protected String renderLabel() {
        return renderText(getLabelMargin(), horizontalPadding, label, (hasBothLinks() ? getLeftLink() : null), getHeight(), getVerticalMargin(), hasShadow(), labelColor).renderedText;
    }

    protected String renderMessage() {
        return renderText(getMessageMargin(), horizontalPadding, message, getRightLink(), getHeight(), getVerticalMargin(), hasShadow(), color).renderedText;
    }

    class LogoData {
        boolean hasLogo;
        int totalLogoWidth;
        String renderedLogo;
    }
    protected LogoData renderLogo(String logo, int badgeHeight, int horizPadding, int logoWidth/*, int logoPadding*/) {
        LogoData logoData = new LogoData();
        if (logo != null && logo.length() > 0) {
            int logoHeight = 14;
            int y = (badgeHeight - logoHeight) / 2;
            int x = horizPadding;
            logoData.hasLogo = true;
            logoData.totalLogoWidth = logoWidth + getLogoPadding();
            logoData.renderedLogo = String.format("<image x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" xlink:href=\"%s\"/>", x, y, logoWidth, logoHeight, escapeXml(logo));
        } else {
            logoData.hasLogo = false;
            logoData.totalLogoWidth = 0;
            logoData.renderedLogo = "";
        }
        return logoData;
    }

    protected String renderLink(String link, int height, int textLength, int horizPadding, int leftMargin, String renderedText) {
        int rectHeight = height;
        int rectWidth = textLength + horizPadding * 2; // FIXME
        int rectX = leftMargin > 1 ? leftMargin + 1 : 0;
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("<a target=\"_blank\" xlink:href=\"%s\">", escapeXml(link)));
        builder.append("\n");
        builder.append("\t");
        builder.append(String.format("<rect width=\"%d\" x=\"%d\" height=\"%d\" fill=\"rgba(0,0,0,0)\" />", rectWidth, rectX, rectHeight));
        builder.append("\n");
        builder.append("\t");
        builder.append(renderedText);
        builder.append("</a>");
        return builder.toString();
    }

    Font verdanaFont = new Font("Verdana", Font.PLAIN, 11);
    class TextData {
        String renderedText;
        int width;
    }
    protected TextData renderText(int leftMargin, int horizPadding, String content, String link, int height, int verticalMargin, boolean shadow, String color) {
        TextData textData = new TextData();
        if (content == null || "".equals(content)) {
            textData.renderedText = "";
            textData.width = 0;
            return textData;
        }

        int textLength = preferredWidthOf(content, verdanaFont);
        String escapedContent = escapeXml(content);

        int shadowMargin = 150 + verticalMargin;
        int textMargin = 140 + verticalMargin;

        int outTextLength = 10 * textLength;
        int x = (int)(10 * (leftMargin + 0.5 * textLength + horizPadding)); // FIXME

        String renderedText = "";
        BackgroundColorData backgroundColorData = colorsForBackground(color);
        if (shadow) {
            renderedText = String.format("<text aria-hidden=\"true\" x=\"%d\" y=\"%d\" fill=\"%s\" fill-opacity=\".3\" transform=\"scale(.1)\" textLength=\"%d\">%s</text>", x, shadowMargin, backgroundColorData.shadowColor, outTextLength, escapedContent);
        }
        renderedText += String.format("<text x=\"%d\" y=\"%d\" transform=\"scale(.1)\" fill=\"%s\" textLength=\"%d\">%s</text>", x, textMargin, backgroundColorData.textColor, outTextLength, escapedContent);

        textData.renderedText = (link != null && link.length() > 0) ? renderLink(link, height, textLength, horizPadding, leftMargin, renderedText) : renderedText;
        textData.width = textLength;
        return textData;
    }

    class BackgroundColorData {
        String textColor;
        String shadowColor;
    }
    protected BackgroundColorData colorsForBackground(String color) {
        BackgroundColorData backgroundColorData = new BackgroundColorData();
        if (Brightness.of(color) <= brightnessThreshold) {
            backgroundColorData.textColor = "#fff";
            backgroundColorData.shadowColor = "#010101";
        } else {
            backgroundColorData.textColor = "#333";
            backgroundColorData.shadowColor = "#ccc";
        }
        return backgroundColorData;
    }

    protected int preferredWidthOf(String content, Font font) {
        // FIXME This will give different results depending on the jdk/jre.
        int width = FontDesignMetrics.getMetrics(font).stringWidth(content);
        return roundUpToOdd(width);
    }

    private int roundUpToOdd(int value) {
        return (value % 2 == 0) ? value + 1 : value;
    }

    protected String escapeXml(String value) {
        value = value.replaceAll("&", "&amp;");
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        value = value.replaceAll("\"", "&quot;");
        value = value.replaceAll("'", "&apos;");
        return value;
    }

    protected boolean hasBothLinks() {
        return hasLeftLink() && hasRightLink();
    }

    protected boolean hasLeftLink() {
        return links != null && links.length >= 1 && links[0] != null && links[0].length() > 0;
    }

    protected boolean hasRightLink() {
        return links != null && links.length >= 2 && links[1] != null && links[1].length() > 0;
    }

    protected String getLeftLink() {
        return hasLeftLink() ? escapeXml(links[0]) : null;
    }

    protected String getRightLink() {
        return hasRightLink() ? escapeXml(links[1]) : null;
    }

    private String renderAriaAttributes() {
        return hasBothLinks() ? "" : String.format("role=\"img\" aria-label=\"%s\"", escapeXml(accessibleText));
    }

    private String renderTitle() {
        return hasBothLinks() ? "" : String.format("<title>%s</title>", escapeXml(accessibleText));
    }

    protected boolean shouldWrapBodyWithLink() {
        return hasOnlyOneLink();
    }

    protected boolean hasOnlyOneLink() {
        return hasLeftLink() && !hasRightLink();
    }

    private String createAccessibleText() {
        String labelPrefix = (label != null && label.length() > 0) ? label.concat(": ") : "";
        return labelPrefix.concat(message);
    }

    protected String toString(double number) {
        return decimalFormat.format(number);
    }

    protected boolean hasLogo() {
        return isNotEmpty(logo);
    }

    protected boolean hasLabel() {
        return isNotEmpty(label);
    }

    private boolean isEmpty(String value) {
        return value == null || "".equals(value);
    }

    private boolean isNotEmpty(String value) {
        return isEmpty(value) == false;
    }

}
