package org.silentsoft.badge4j;

/**
 * This enum defines the following colors.
 *
 * <table>
 *   <tr>
 *     <th></th>
 *     <th>Name</th>
 *     <th>Hex value</th>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#44cc11;"></div></td>
 *     <td>brightgreen</td>
 *     <td>#4c1</td>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#97ca00;"></div></td>
 *     <td>green</td>
 *     <td>#97ca00</td>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#dfb317;"></div></td>
 *     <td>yellow</td>
 *     <td>#dfb317</td>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#a4a61d;"></div></td>
 *     <td>yellowgreen</td>
 *     <td>#a4a61d</td>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#fe7d37;"></div></td>
 *     <td>orange</td>
 *     <td>#fe7d37</td>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#e05d44;"></div></td>
 *     <td>red</td>
 *     <td>#e05d44</td>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#007ec6;"></div></td>
 *     <td>blue</td>
 *     <td>#007ec6</td>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#555555;"></div></td>
 *     <td>grey</td>
 *     <td>#555</td>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#9f9f9f;"></div></td>
 *     <td>lightgrey</td>
 *     <td>#9f9f9f</td>
 *   </tr>
 * </table>
 */
public enum NamedColor {
    BrightGreen("#4c1"),
    Green("#97ca00"),
    Yellow("#dfb317"),
    YellowGreen("#a4a61d"),
    Orange("#fe7d37"),
    Red("#e05d44"),
    Blue("#007ec6"),
    Grey("#555"),
    LightGrey("#9f9f9f");

    String hex;

    NamedColor(String hex) {
        this.hex = hex;
    }

    public String getHex() {
        return hex;
    }

    public static NamedColor nameOf(String name) {
        for (NamedColor namedColor : NamedColor.values()) {
            if (namedColor.name().equalsIgnoreCase(name)) {
                return namedColor;
            }
        }

        return null;
    }
}
