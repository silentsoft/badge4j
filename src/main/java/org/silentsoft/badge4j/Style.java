package org.silentsoft.badge4j;

/**
 * This enum defines the following styles.
 *
 * <table>
 *   <tr>
 *     <th>Style</th>
 *     <th>Design</th>
 *   </tr>
 *   <tr>
 *     <td>Flat</td>
 *     <td><img src="doc-files/style-flat-label-label-message-message.svg"/></td>
 *   </tr>
 *   <tr>
 *     <td>FlatSquare</td>
 *     <td><img src="doc-files/style-flatsquare-label-label-message-message.svg"/></td>
 *   </tr>
 *   <tr>
 *     <td>ForTheBadge</td>
 *     <td><img src="doc-files/style-forthebadge-label-label-message-message.svg"/></td>
 *   </tr>
 *   <tr>
 *     <td>Plastic</td>
 *     <td><img src="doc-files/style-plastic-label-label-message-message.svg"/></td>
 *   </tr>
 *   <tr>
 *     <td>Social</td>
 *     <td><img src="doc-files/style-social-label-label-message-message.svg"/></td>
 *   </tr>
 * </table>
 */
public enum Style {
    /**
     * Defines the badge look and feel in a flat style.</p>
     *
     * <pre>
     * Badge.builder().style(Style.Flat).label("label").message("message").build();
     * </pre>
     *
     * The code above will generate the following badge:
     * <p><img src="doc-files/style-flat-label-label-message-message.svg"/></p>
     */
    Flat("flat"),
    /**
     * Defines the badge look and feel in a flat-square style.</p>
     *
     * <pre>
     * Badge.builder().style(Style.FlatSquare).label("label").message("message").build();
     * </pre>
     *
     * The code above will generate the following badge:
     * <p><img src="doc-files/style-flatsquare-label-label-message-message.svg"/></p>
     */
    FlatSquare("flat-square"),
    /**
     * Defines the badge look and feel in a for-the-badge style.</p>
     *
     * <pre>
     * Badge.builder().style(Style.ForTheBadge).label("label").message("message").build();
     * </pre>
     *
     * The code above will generate the following badge:
     * <p><img src="doc-files/style-forthebadge-label-label-message-message.svg"/></p>
     */
    ForTheBadge("for-the-badge"),
    /**
     * Defines the badge look and feel in a plastic style.</p>
     *
     * <pre>
     * Badge.builder().style(Style.Plastic).label("label").message("message").build();
     * </pre>
     *
     * The code above will generate the following badge:
     * <p><img src="doc-files/style-plastic-label-label-message-message.svg"/></p>
     */
    Plastic("plastic"),
    /**
     * Defines the badge look and feel in a social style.</p>
     *
     * <pre>
     * Badge.builder().style(Style.Social).label("label").message("message").build();
     * </pre>
     *
     * The code above will generate the following badge:
     * <p><img src="doc-files/style-social-label-label-message-message.svg"/></p>
     */
    Social("social");

    String name;

    Style(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Style nameOf(String name) {
        for (Style style : Style.values()) {
            if (style.name.equalsIgnoreCase(name)) {
                return style;
            }
        }

        return null;
    }
}
