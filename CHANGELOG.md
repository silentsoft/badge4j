# 1.12.0 (27 May 2025)

## Dependency Updates
- [csscolor4j v1.1.0](https://github.com/silentsoft/csscolor4j/releases/tag/v1.1.0)

# 1.11.0 (28 Mar 2022)

## Dependency Updates
- [simpleicons4j v1.9.0](https://github.com/silentsoft/simpleicons4j/releases/tag/v1.9.0)

# 1.10.0 (21 Mar 2022)

## Dependency Updates
- [simpleicons4j v1.8.0](https://github.com/silentsoft/simpleicons4j/releases/tag/v1.8.0)

# 1.9.0 (14 Mar 2022)

## Dependency Updates
- [simpleicons4j v1.7.0](https://github.com/silentsoft/simpleicons4j/releases/tag/v1.7.0)

# 1.8.0 (14 Mar 2022)

## Dependency Updates
- [simpleicons4j v1.6.0](https://github.com/silentsoft/simpleicons4j/releases/tag/v1.6.0)

# 1.7.0 (28 Feb 2022)

## Dependency Updates
- [simpleicons4j v1.5.0](https://github.com/silentsoft/simpleicons4j/releases/tag/v1.5.0)

# 1.6.0 (25 Feb 2022)

## Dependency Updates
- [simpleicons4j v1.4.0](https://github.com/silentsoft/simpleicons4j/releases/tag/v1.4.0)

# 1.5.2 (17 Feb 2022)

## Bug Fixes
- Fix incorrect hashtag(`#`) prefix.

# 1.5.1 (14 Feb 2022)

## Enhancements
- Minor logo color adjustments.

# 1.5.0 (14 Feb 2022)

## Enhancements
- Adjust the logo color according to the background color.

# 1.4.0 (13 Feb 2022)

## Dependency Updates
- [simpleicons4j v1.3.0](https://github.com/silentsoft/simpleicons4j/releases/tag/v1.3.0)

# 1.3.0 (9 Feb 2022)

## Dependency Updates
- [simpleicons4j v1.2.0](https://github.com/silentsoft/simpleicons4j/releases/tag/v1.2.0)

# 1.2.0 (1 Feb 2022)

## New Features
- Supports [simple-icons](https://simpleicons.org).

# 1.1.0 (7 Dec 2021)

## Enhancements
- Supports Java 9 and above.

# 1.0.0 (3 Jul 2021)

## Usage
![](src/main/javadoc/org/silentsoft/badge4j/doc-files/style-flat-label-hello-message-world.svg)
```java
String svg = Badge.builder().label("hello").message("world").build();
```

## Available builder options
```java
Badge.builder()
     .style(Style) /* Flat(default), FlatSquare, ForTheBadge, Plastic, Social */
     .label(String)
     .message(String)
     .color(String)
     .labelColor(String)
     .logo(String) /* data:image/svg+xml;base64,.. */
     .logoWidth(int)
     .links(String[])
     .build();
```

## Styles
![](src/main/javadoc/org/silentsoft/badge4j/doc-files/style-flat-label-style-message-flat.svg)

![](src/main/javadoc/org/silentsoft/badge4j/doc-files/style-flatsquare-label-style-message-flat-square.svg)

![](src/main/javadoc/org/silentsoft/badge4j/doc-files/style-forthebadge-label-style-message-for-the-badge.svg)

![](src/main/javadoc/org/silentsoft/badge4j/doc-files/style-plastic-label-style-message-plastic.svg)

![](src/main/javadoc/org/silentsoft/badge4j/doc-files/style-social-label-style-message-social.svg)

## Colors
- Named color by shields.io
  
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-brightgreen.svg)
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-green.svg)
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-yellow.svg)
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-yellowgreen.svg)
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-orange.svg)
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-red.svg)
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-blue.svg)
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-grey.svg)
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-lightgrey.svg)
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-gray.svg)
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-lightgray.svg)
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-critical.svg)
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-important.svg)
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-success.svg)
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-informational.svg)
  ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-inactive.svg)

- Any valid [CSS color](https://developer.mozilla.org/en-US/docs/Web/CSS/color_value)
  - named color
    - ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-black.svg)
    - ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-rebeccapurple.svg)
    - etc.
  - hexadecimal numbers
    - ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-ff69b4.svg)
    - ![](src/main/javadoc/org/silentsoft/badge4j/doc-files/color-9cf.svg)
    - etc.
  - rgb[a](red, green, blue[, opacity])
  - cmyk[a](cyan, magenta, yellow, black[, opacity])
  - hsl[a](hue, saturation, lightness[, opacity])
