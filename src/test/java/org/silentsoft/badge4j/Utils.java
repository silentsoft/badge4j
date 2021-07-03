package org.silentsoft.badge4j;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {

    private static final String resourceLocation = "src/test/resources/org/silentsoft/badge4j";
    private static final String docFilesLocation = "src/main/javadoc/org/silentsoft/badge4j/doc-files";

    public static void createMasterModelFile(BadgeBuilder builder) throws IOException {
        createMasterModelFile(builder, false, false, false);
    }

    public static void createMasterModelFile(BadgeBuilder builder, boolean explicitlyProvidedColor, boolean explicitlyProvidedLabelColor, boolean explicitlyProvidedLogoWidth) throws IOException {
        String fileName = buildFileName(builder, explicitlyProvidedColor, explicitlyProvidedLabelColor, explicitlyProvidedLogoWidth);
        Path masterModelFilePath = Paths.get(System.getProperty("user.dir"), resourceLocation, fileName);
        if (Files.exists(masterModelFilePath)) {
            throw new FileAlreadyExistsException(String.format("The master model file is already exists. (%s)", masterModelFilePath));
        }

        writeFile(masterModelFilePath, builder.build());
    }

    public static String readMasterModelFile(BadgeBuilder builder, boolean explicitlyProvidedColor, boolean explicitlyProvidedLabelColor, boolean explicitlyProvidedLogoWidth) throws IOException {
        String fileName = buildFileName(builder, explicitlyProvidedColor, explicitlyProvidedLabelColor, explicitlyProvidedLogoWidth);
        Path masterModelFilePath = Paths.get(System.getProperty("user.dir"), resourceLocation, fileName);

        return String.join("", Files.readAllLines(masterModelFilePath));
    }

    public static void createJavaDocImageFile(BadgeBuilder builder) throws IOException {
        String fileName = buildFileName(builder, false, false, false);

        writeFile(Paths.get(System.getProperty("user.dir"), docFilesLocation, fileName), builder.build());
    }

    public static void createJavaDocColorImageFile(BadgeBuilder builder) throws IOException {
        String fileName = String.format("color-%s.svg", builder.color);
        writeFile(Paths.get(System.getProperty("user.dir"), docFilesLocation, fileName), builder.build());
    }

    private static void writeFile(Path path, String content) throws IOException {
        Files.createDirectories(path.getParent());
        Files.createFile(path);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            writer.write(content);
            writer.flush();
        }
    }

    public static String buildFileName(BadgeBuilder builder, boolean explicitlyProvidedColor, boolean explicitlyProvidedLabelColor, boolean explicitlyProvidedLogoWidth) {
        /**
         * priority
         * . style-{}
         * . label-{}
         * . message-{}
         * . color-{}
         * . labelColor-{}
         * . (with-one | with-two)-link{s}
         * . (with)-logo
         * . logoWidth-{}
         */
        StringBuilder fileName = new StringBuilder();

        fileName.append(String.join("-", "style", builder.style.name().toLowerCase()));

        if (isNotEmpty(builder.label)) {
            fileName.append("-");
            fileName.append(String.join("-", "label", builder.label));
        }
        if (isNotEmpty(builder.message)) {
            fileName.append("-");
            fileName.append(String.join("-", "message", builder.message));
        }
        if (isNotEmpty(builder.color) && explicitlyProvidedColor) {
            fileName.append("-");
            fileName.append(String.join("-", "color", normalizeColorName(builder.color)));
        }
        if (isNotEmpty(builder.labelColor) && explicitlyProvidedLabelColor) {
            fileName.append("-");
            fileName.append(String.join("-", "labelColor", normalizeColorName(builder.labelColor)));
        }
        if (isNotEmpty(builder.links)) {
            if (builder.links.length == 1) {
                fileName.append("-");
                fileName.append("with-one-link");
            } else if (builder.links.length == 2) {
                fileName.append("-");
                fileName.append("with-two-links");
            }
        }
        if (isNotEmpty(builder.logo)) {
            fileName.append("-");
            fileName.append("with-logo");
        }
        if (explicitlyProvidedLogoWidth) {
            fileName.append("-");
            fileName.append(String.join("-", "logoWidth", String.valueOf(builder.logoWidth)));
        }
        fileName.append(".svg");

        return fileName.toString();
    }

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof String) {
            return ((String) object).length() <= 0;
        } else if (object instanceof String[]) {
            return ((String[]) object).length <= 0;
        }

        throw new UnknownError("object is unknown");
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    private static String normalizeColorName(String color) {
        color = color.toLowerCase();
        color = color.replaceAll("#", "");
        color = color.replaceAll("\\(", "-");
        color = color.replaceAll(",", "-");
        color = color.replaceAll(" ", "");
        color = color.replaceAll("\\)", "");
        color = color.replaceAll(";", "");
        return color.trim();
    }

}
