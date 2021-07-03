package org.silentsoft.badge4j;

/**
 * This enum defines the following aliases.
 *
 * <table>
 *   <tr>
 *     <th></th>
 *     <th>Name</th>
 *     <th>Hex value</th>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#555555;"></div></td>
 *     <td>gray</td>
 *     <td>#555</td>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#9f9f9f;"></div></td>
 *     <td>lightgray</td>
 *     <td>#9f9f9f</td>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#e05d44;"></div></td>
 *     <td>critical</td>
 *     <td>#e05d44</td>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#fe7d37;"></div></td>
 *     <td>important</td>
 *     <td>#fe7d37</td>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#44cc11;"></div></td>
 *     <td>success</td>
 *     <td>#4c1</td>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#007ec6;"></div></td>
 *     <td>informational</td>
 *     <td>#007ec6</td>
 *   </tr>
 *   <tr>
 *     <td><div style="width:10px;height:10px;background-color:#9f9f9f;"></div></td>
 *     <td>inactive</td>
 *     <td>#9f9f9f</td>
 *   </tr>
 * </table>
 */
public enum NamedColorAlias {
    Gray(NamedColor.Grey),
    LightGray(NamedColor.LightGrey),
    Critical(NamedColor.Red),
    Important(NamedColor.Orange),
    Success(NamedColor.BrightGreen),
    Informational(NamedColor.Blue),
    Inactive(NamedColor.LightGrey);

    NamedColor namedColor;

    NamedColorAlias(NamedColor namedColor) {
        this.namedColor = namedColor;
    }

    public String getHex() {
        return namedColor.getHex();
    }

    public static NamedColorAlias nameOf(String name) {
        for (NamedColorAlias namedColorAlias : NamedColorAlias.values()) {
            if (namedColorAlias.name().equalsIgnoreCase(name)) {
                return namedColorAlias;
            }
        }

        return null;
    }
}
