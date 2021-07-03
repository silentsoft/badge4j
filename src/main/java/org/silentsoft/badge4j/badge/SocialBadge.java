package org.silentsoft.badge4j.badge;

import java.awt.*;

public class SocialBadge extends Badge {

    private int externalHeight;
    private int internalHeight;
    private int labelHorizPadding;
    private int labelRectWidth;
    private boolean hasMessage;
    private int horizGutter;
    private int messageRectWidth;

    private int totalLogoWidth;
    private int labelTextWidth;
    private int messageTextWidth;

    private String renderedLogo;

    public SocialBadge(String label, String message, String color, String labelColor, String[] links, String logo, int logoWidth) {
        super(label, message, color, labelColor, links, logo, logoWidth);

        externalHeight = 20;
        internalHeight = 19;
        labelHorizPadding = 5;
        horizGutter = 6;
        LogoData logoData = renderLogo(logo, externalHeight, labelHorizPadding, logoWidth);
        totalLogoWidth = logoData.totalLogoWidth;
        renderedLogo = logoData.renderedLogo;
        hasMessage = message != null && message.length() > 0;

        Font font = new Font("Helvetica", Font.BOLD, 11);
        labelTextWidth = preferredWidthOf(label, font);
        messageTextWidth = preferredWidthOf(message, font);
        labelRectWidth = labelTextWidth + logoData.totalLogoWidth + 2 * labelHorizPadding; // FIXME
        int messageHorizPadding = 4;
        messageRectWidth = messageTextWidth + 2 * messageHorizPadding;
    }

    @Override
    protected double getLeftWidth() {
        return labelRectWidth + 1;
    }

    @Override
    protected double getRightWidth() {
        return hasMessage ? horizGutter + messageRectWidth : 0;
    }

    @Override
    protected String renderLogo() {
        return renderedLogo;
    }

    @Override
    protected String getFontFamily() {
        return "Helvetica Neue,Helvetica,Arial,sans-serif";
    }

    @Override
    protected int getHeight() {
        return externalHeight;
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
        StringBuilder builder = new StringBuilder();
        builder.append("<style>a:hover #llink{fill:url(#b);stroke:#ccc}a:hover #rlink{fill:#4183c4}</style>");
        builder.append("<linearGradient id=\"a\" x2=\"0\" y2=\"100%\">");
        builder.append("<stop offset=\"0\" stop-color=\"#fcfcfc\" stop-opacity=\"0\"/>");
        builder.append("<stop offset=\"1\" stop-opacity=\".1\"/>");
        builder.append("</linearGradient>");
        builder.append("<linearGradient id=\"b\" x2=\"0\" y2=\"100%\">");
        builder.append("<stop offset=\"0\" stop-color=\"#ccc\" stop-opacity=\".1\"/>");
        builder.append("<stop offset=\"1\" stop-opacity=\".1\"/>");
        builder.append("</linearGradient>");
        builder.append("<g stroke=\"#d5d5d5\">");
        builder.append(String.format("<rect stroke=\"none\" fill=\"#fcfcfc\" x=\"0.5\" y=\"0.5\" width=\"%d\" height=\"%d\" rx=\"2\"/>", labelRectWidth, internalHeight));
        builder.append(hasMessage ? renderMessageBubble() : "");
        builder.append("</g>");
        builder.append(renderLogo());
        builder.append(String.format("<g aria-hidden=\"%s\" fill=\"#333\" text-anchor=\"middle\" font-family=\"%s\" text-rendering=\"geometricPrecision\" font-weight=\"700\" font-size=\"110px\" line-height=\"14px\">", !hasBothLinks(), getFontFamily()));
        builder.append(renderLabelText());
        builder.append(hasMessage ? renderMessageText() : "");
        builder.append("</g>");
        return renderBadge(builder.toString());
    }

    private String renderMessageBubble() {
        double messageBubbleMainX = labelRectWidth + horizGutter + 0.5;
        int messageBubbleNotchX = labelRectWidth + horizGutter;

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("<rect x=\"%.1f\" y=\"0.5\" width=\"%d\" height=\"%d\" rx=\"2\" fill=\"#fafafa\"/>", messageBubbleMainX, messageRectWidth, internalHeight));
        builder.append(String.format("<rect x=\"%d\" y=\"7.5\" width=\"0.5\" height=\"5\" stroke=\"#fafafa\"/>", messageBubbleNotchX));
        builder.append(String.format("<path d=\"M%.1f 6.5 l-3 3v1 l3 3\" stroke=\"d5d5d5\" fill=\"#fafafa\"/>", messageBubbleMainX));
        return builder.toString();
    }

    private String renderLabelText() {
        // FIXME
        int labelTextX = 10 * (totalLogoWidth + labelTextWidth / 2 + labelHorizPadding);
        int labelTextLength = 10 * labelTextWidth;
        String escapedLabel = escapeXml(label);
        boolean shouldWrapWithLink = hasLeftLink() && !shouldWrapBodyWithLink();

        String rect = String.format("<rect id=\"llink\" stroke=\"#d5d5d5\" fill=\"url(#a)\" x=\".5\" y=\".5\" width=\"%d\" height=\"%d\" rx=\"2\" />", labelRectWidth, internalHeight);
        String shadow = String.format("<text aria-hidden=\"true\" x=\"%d\" y=\"150\" fill=\"#fff\" transform=\"scale(.1)\" textLength=\"%d\">%s</text>", labelTextX, labelTextLength, escapedLabel);
        String text = String.format("<text x=\"%d\" y=\"140\" transform=\"scale(.1)\" textLength=\"%d\">%s</text>", labelTextX, labelTextLength, escapedLabel);

        StringBuilder builder = new StringBuilder();
        if (shouldWrapWithLink) {
            builder.append(String.format("<a target=\"_blank\" xlink:href=\"%s\">", getLeftLink()));
            builder.append(shadow);
            builder.append(text);
            builder.append(rect);
            builder.append("</a>");
        } else {
            builder.append(rect);
            builder.append(shadow);
            builder.append(text);
        }
        return builder.toString();
    }

    private String renderMessageText() {
        // FIXME
        int messageTextX = 10 * (labelRectWidth + horizGutter + messageRectWidth / 2);
        int messageTextLength = 10 * messageTextWidth;
        String escapedMessage = escapeXml(message);

        String rect = String.format("<rect width=\"%d\" x=\"%d\" height=\"%d\" fill=\"rgba(0,0,0,0)\" />", messageRectWidth + 1, labelRectWidth + horizGutter, internalHeight + 1);
        String shadow = String.format("<text aria-hidden=\"true\" x=\"%d\" y=\"150\" fill=\"#fff\" transform=\"scale(.1)\" textLength=\"%d\">%s</text>", messageTextX, messageTextLength, escapedMessage);
        String text = String.format("<text id=\"rlink\" x=\"%d\" y=\"140\" transform=\"scale(.1)\" textLength=\"%d\">%s</text>", messageTextX, messageTextLength, escapedMessage);

        StringBuilder builder = new StringBuilder();
        if (hasRightLink()) {
            builder.append(String.format("<a target=\"_blank\" xlink:href=\"%s\">", getRightLink()));
            builder.append(rect);
            builder.append(shadow);
            builder.append(text);
        } else {
            builder.append(shadow);
            builder.append(text);
        }
        return builder.toString();
    }
}
