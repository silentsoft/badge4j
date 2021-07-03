package org.silentsoft.badge4j;

public class BadgeBuilder {

    Style style;
    String label;
    String message;
    String color;
    String labelColor;
    String logo;
    int logoWidth;
    String[] links;

    BadgeBuilder() {

    }

    /**
     * Set the style of the badge.</p>
     * <p>Available styles are:</p>
     * <ul>
     *   <li><img src="doc-files/style-flat-label-style-message-flat.svg"/></li>
     *   <li><img src="doc-files/style-flatsquare-label-style-message-flat-square.svg"/></li>
     *   <li><img src="doc-files/style-forthebadge-label-style-message-for-the-badge.svg"/></li>
     *   <li><img src="doc-files/style-plastic-label-style-message-plastic.svg"/></li>
     *   <li><img src="doc-files/style-social-label-style-message-social.svg"/></li>
     * </ul>
     *
     * @param style the look and feel of the badge
     * @return the builder instance, not null
     */
    public BadgeBuilder style(Style style) {
        this.style = style;
        return this;
    }

    public BadgeBuilder label(String label) {
        this.label = label;
        return this;
    }

    public BadgeBuilder message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Set the color of the right side of the badge.</p>
     * <p>Available colors are:</p>
     * <ul>
     *   <li>Named color by shields.io
     *     <p>
     *       <img src="doc-files/color-brightgreen.svg"/>
     *       <img src="doc-files/color-green.svg"/>
     *       <img src="doc-files/color-yellow.svg"/>
     *       <img src="doc-files/color-yellowgreen.svg"/>
     *       <img src="doc-files/color-orange.svg"/>
     *       <img src="doc-files/color-red.svg"/>
     *       <img src="doc-files/color-blue.svg"/>
     *       <img src="doc-files/color-grey.svg"/>
     *       <img src="doc-files/color-lightgrey.svg"/>
     *       <img src="doc-files/color-gray.svg"/>
     *       <img src="doc-files/color-lightgray.svg"/>
     *       <img src="doc-files/color-critical.svg"/>
     *       <img src="doc-files/color-important.svg"/>
     *       <img src="doc-files/color-success.svg"/>
     *       <img src="doc-files/color-informational.svg"/>
     *       <img src="doc-files/color-inactive.svg"/>
     *     </p>
     *   </li>
     *   <li>Any valid <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/color_value">CSS color</a>
     *     <ul>
     *       <li>named color
     *         <ul>
     *           <li><img src="doc-files/color-black.svg"/></li>
     *           <li><img src="doc-files/color-rebeccapurple.svg"/></li>
     *           <li>etc</li>
     *         </ul>
     *       </li>
     *       <li>hexadecimal numbers
     *         <ul>
     *           <li><img src="doc-files/color-ff69b4.svg"/></li>
     *           <li><img src="doc-files/color-9cf.svg"/></li>
     *           <li>etc</li>
     *         </ul>
     *       </li>
     *       <li>rgb[a](red, green, blue[, opacity])</li>
     *       <li>cmyk[a](cyan, magenta, yellow, black[, opacity])</li>
     *       <li>hsl[a](hue, saturation, lightness[, opacity])</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @param color the string representation of valid color
     * @return the builder instance, not null
     * @see org.silentsoft.badge4j.NamedColor
     * @see org.silentsoft.badge4j.NamedColorAlias
     */
    public BadgeBuilder color(String color) {
        this.color = color;
        return this;
    }

    /**
     * Set the color of the left side of the badge.</p>
     * <p>Available colors are:</p>
     * <ul>
     *   <li>Named color by shields.io
     *     <p>
     *       <img src="doc-files/color-brightgreen.svg"/>
     *       <img src="doc-files/color-green.svg"/>
     *       <img src="doc-files/color-yellow.svg"/>
     *       <img src="doc-files/color-yellowgreen.svg"/>
     *       <img src="doc-files/color-orange.svg"/>
     *       <img src="doc-files/color-red.svg"/>
     *       <img src="doc-files/color-blue.svg"/>
     *       <img src="doc-files/color-grey.svg"/>
     *       <img src="doc-files/color-lightgrey.svg"/>
     *       <img src="doc-files/color-gray.svg"/>
     *       <img src="doc-files/color-lightgray.svg"/>
     *       <img src="doc-files/color-critical.svg"/>
     *       <img src="doc-files/color-important.svg"/>
     *       <img src="doc-files/color-success.svg"/>
     *       <img src="doc-files/color-informational.svg"/>
     *       <img src="doc-files/color-inactive.svg"/>
     *     </p>
     *   </li>
     *   <li>Any valid <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/color_value">CSS color</a>
     *     <ul>
     *       <li>named color
     *         <ul>
     *           <li><img src="doc-files/color-black.svg"/></li>
     *           <li><img src="doc-files/color-rebeccapurple.svg"/></li>
     *           <li>etc</li>
     *         </ul>
     *       </li>
     *       <li>hexadecimal numbers
     *         <ul>
     *           <li><img src="doc-files/color-ff69b4.svg"/></li>
     *           <li><img src="doc-files/color-9cf.svg"/></li>
     *           <li>etc</li>
     *         </ul>
     *       </li>
     *       <li>rgb[a](red, green, blue[, opacity])</li>
     *       <li>cmyk[a](cyan, magenta, yellow, black[, opacity])</li>
     *       <li>hsl[a](hue, saturation, lightness[, opacity])</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @param labelColor the string representation of valid color
     * @return the builder instance, not null
     * @see org.silentsoft.badge4j.NamedColor
     * @see org.silentsoft.badge4j.NamedColorAlias
     */
    public BadgeBuilder labelColor(String labelColor) {
        this.labelColor = labelColor;
        return this;
    }

    public BadgeBuilder logo(String logo) {
        this.logo = logo;
        return this;
    }

    public BadgeBuilder logoWidth(int logoWidth) {
        this.logoWidth = logoWidth;
        return this;
    }

    public BadgeBuilder links(String[] links) {
        this.links = links;
        return this;
    }

    /**
     * Returns an image string in svg format representing the badge.
     *
     * @return the svg string, not null
     */
    public String build() {
        return new Badge(this).build();
    }

}