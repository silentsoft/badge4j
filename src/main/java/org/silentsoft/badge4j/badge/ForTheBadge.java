package org.silentsoft.badge4j.badge;

import java.awt.*;

public class ForTheBadge extends Badge {

    private static final int FONT_SIZE = 10;
    private static final int BADGE_HEIGHT = 28;
    private static final int LOGO_HEIGHT = 14;
    private static final int TEXT_MARGIN = 12;
    private static final int LOGO_MARGIN = 9;
    private static final int LOGO_TEXT_GUTTER = 6;
    private static final double LETTER_SPACING = 1.25;

    private static int FONT_SCALE_UP_FACTOR = 10;

    private double labelTextWidth;
    private double messageTextWidth;

    private boolean needsLabelRect;

    private int logoMinX, labelTextMinX;

    private double labelRectWidth, messageTextMinX, messageRectWidth;

    public ForTheBadge(String label, String message, String color, String labelColor, String[] links, String logo, int logoWidth) {
        super(label, message, color, labelColor, links, logo, logoWidth);

        labelTextWidth = label == null || "".equals(label) ? 0 : preferredWidthOf(label, new Font("Verdana", Font.PLAIN, 10)) + LETTER_SPACING * label.length();
        messageTextWidth = message == null || "".equals(message) ? 0 : preferredWidthOf(message, new Font("Verdana", Font.BOLD, 10)) + LETTER_SPACING * message.length();

        needsLabelRect = hasLabel() || (logo != null && logo.length() > 0 && labelColor != null && labelColor.length() > 0);

        if (logo != null && logo.length() > 0) {
            logoMinX = LOGO_MARGIN;
            labelTextMinX = logoMinX + logoWidth + LOGO_TEXT_GUTTER;
        } else {
            labelTextMinX = TEXT_MARGIN;
        }

        if (needsLabelRect) {
            if (hasLabel()) {
                labelRectWidth = labelTextMinX + labelTextWidth + TEXT_MARGIN;
            } else {
                labelRectWidth = 2 * LOGO_MARGIN + logoWidth;
            }
            messageTextMinX = labelRectWidth + TEXT_MARGIN;
            messageRectWidth = 2 * TEXT_MARGIN + messageTextWidth;
        } else {
            if (logo != null && logo.length() > 0) {
                messageTextMinX = TEXT_MARGIN + logoWidth + LOGO_TEXT_GUTTER;
                messageRectWidth = 2 * TEXT_MARGIN + logoWidth + LOGO_TEXT_GUTTER + messageTextWidth;
            } else {
                messageTextMinX = TEXT_MARGIN;
                messageRectWidth = 2 * TEXT_MARGIN + messageTextWidth;
            }
        }
    }

    @Override
    protected double getLeftWidth() {
        return labelRectWidth;
    }

    @Override
    protected double getRightWidth() {
        return messageRectWidth;
    }

    @Override
    protected String renderLogo() {
        if (hasLogo()) {
            return String.format("<image x=\"%d\" y=\"%.1f\" width=\"%d\" height=\"%d\" xlink:href=\"%s\"/>", logoMinX, 0.5 * (BADGE_HEIGHT - LOGO_HEIGHT), super.logoWidth, LOGO_HEIGHT, encodeLogoToBase64(super.logo));
        }
        return "";
    }

    @Override
    protected int getHeight() {
        return BADGE_HEIGHT;
    }

    @Override
    protected int getVerticalMargin() {
        return 0; // doesn't matter due to render custom label and message.
    }

    @Override
    protected boolean hasShadow() {
        return false; // doesn't matter due to render custom label and message.
    }

    @Override
    public String render() {
        return renderBadge(String.join("", renderBackground(), renderForeground()));
    }

    private String renderLabelText() {
        String textColor = colorsForBackground(labelColor).textColor;
        double midX = labelTextMinX + 0.5 * labelTextWidth;

        String text = String.format("<text transform=\"scale(.1)\" x=\"%s\" y=\"175\" textLength=\"%s\" fill=\"%s\">%s</text>", toString(FONT_SCALE_UP_FACTOR * midX), toString(FONT_SCALE_UP_FACTOR * labelTextWidth), textColor, label);

        StringBuilder builder = new StringBuilder();
        if (hasLeftLink() && !shouldWrapBodyWithLink()) {
            builder.append(String.format("<a target=\"_blank\" xlink:href=\"%s\">", getLeftLink()));
            builder.append(String.format("<rect width=\"%.2f\" height=\"%d\" fill=\"rgba(0,0,0,0)\" />", labelRectWidth, BADGE_HEIGHT));
            builder.append(text);
            builder.append("</a>");
        } else {
            builder.append(text);
        }
        return builder.toString();
    }

    private String renderMessageText() {
        String textColor = colorsForBackground(color).textColor;
        double midX = messageTextMinX + 0.5 * messageTextWidth;

        String text = String.format("<text transform=\"scale(.1)\" x=\"%.2f\" y=\"175\" textLength=\"%.1f\" fill=\"%s\" font-weight=\"bold\">%s</text>", FONT_SCALE_UP_FACTOR * midX, FONT_SCALE_UP_FACTOR * messageTextWidth, textColor, message);

        StringBuilder builder = new StringBuilder();
        if (hasRightLink()) {
            builder.append(String.format("<a target=\"_blank\" xlink:href=\"%s\">", getRightLink()));
            builder.append(String.format("<rect width=\"%.2f\" height=\"%d\" x=\"%.2f\" fill=\"rgba(0,0,0,0)\" />", messageRectWidth, BADGE_HEIGHT, labelRectWidth));
            builder.append(text);
            builder.append("</a>");
        } else {
            builder.append(text);
        }
        return builder.toString();
    }

    private String renderBackground() {
        StringBuilder builder = new StringBuilder();
        builder.append("<g shape-rendering=\"crispEdges\">");
        if (needsLabelRect) {
            builder.append(String.format("<rect width=\"%.2f\" height=\"%d\" fill=\"%s\" />", labelRectWidth, BADGE_HEIGHT, labelColor));
            builder.append(String.format("<rect x=\"%.2f\" width=\"%.2f\" height=\"%d\" fill=\"%s\" />", labelRectWidth, messageRectWidth, BADGE_HEIGHT, color));
        } else {
            builder.append(String.format("<rect width=\"%.2f\" height=\"%d\" fill=\"%s\" />", messageRectWidth, BADGE_HEIGHT, color));
        }
        builder.append("</g>");
        return builder.toString();
    }

    private String renderForeground() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("<g fill=\"#fff\" text-anchor=\"middle\" font-family=\"%s\" text-rendering=\"geometricPrecision\" font-size=\"%d\">", getFontFamily(), FONT_SCALE_UP_FACTOR * FONT_SIZE));
        builder.append(hasLogo() ? renderLogo() : "");
        builder.append(hasLabel() ? renderLabelText() : "");
        builder.append(renderMessageText());
        builder.append("</g>");
        return builder.toString();
    }
}
