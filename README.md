# Badge4J

![release](https://img.shields.io/badge/release-v1.0.0-blue.svg)

`Badge4J` is a Java implementation of the [badge-maker](https://www.npmjs.com/package/badge-maker) JavaScript library and is inspired by [shields.io](https://shields.io).

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

## Maven Central
```xml
<dependency>
    <groupId>org.silentsoft</groupId>
    <artifactId>badge4j</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please note we have a [CODE_OF_CONDUCT](https://github.com/silentsoft/badge4j/blob/main/CODE_OF_CONDUCT.md), please follow it in all your interactions with the project.

## License
Please refer to [LICENSE](https://github.com/silentsoft/badge4j/blob/main/LICENSE.txt).