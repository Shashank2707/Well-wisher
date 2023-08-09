package com.wellwisher.producer.util;

public class WellWisherStringUtils {
    public static String toTitleCase(String str) {
        String[] parts = str.trim().toLowerCase().split(" ");
        String titleCase = "";
        for (var part : parts)
            if (part.length() > 0)
                titleCase += part.substring(0, 1).toUpperCase() + part.substring(1) + " ";
        return titleCase.substring(0, titleCase.length() - 1);
    }
}
