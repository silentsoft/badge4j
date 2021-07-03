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
