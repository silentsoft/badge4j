package org.silentsoft.badge4j;

import org.silentsoft.badge4j.renderer.*;

class BadgeRenderer {

    static Renderer get(Style style) {
        switch (style) {
            case FlatSquare:
                return new FlatSquareRenderer();
            case Plastic:
                return new PlasticRenderer();
            case Social:
                return new SocialRenderer();
            case ForTheBadge:
                return new ForTheBadgeRenderer();
            default:
                return new FlatRenderer();
        }
    }

}
