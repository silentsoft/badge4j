package org.silentsoft.badge4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StyleTest {

    @Test
    public void toStringTest() {
        Assertions.assertEquals("flat", Style.Flat.toString());
        Assertions.assertEquals("flat-square", Style.FlatSquare.toString());
        Assertions.assertEquals("for-the-badge", Style.ForTheBadge.toString());
        Assertions.assertEquals("plastic", Style.Plastic.toString());
        Assertions.assertEquals("social", Style.Social.toString());
    }

    @Test
    public void nameOfTest() {
        Assertions.assertEquals(Style.Flat, Style.nameOf("flat"));
        Assertions.assertEquals(Style.FlatSquare, Style.nameOf("flat-square"));
        Assertions.assertEquals(Style.ForTheBadge, Style.nameOf("for-the-badge"));
        Assertions.assertEquals(Style.Plastic, Style.nameOf("plastic"));
        Assertions.assertEquals(Style.Social, Style.nameOf("social"));
        Assertions.assertNull(Style.nameOf("unknown"));
    }

}
