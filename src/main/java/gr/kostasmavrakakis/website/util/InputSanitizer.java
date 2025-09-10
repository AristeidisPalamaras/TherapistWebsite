package gr.kostasmavrakakis.website.util;

import org.apache.commons.text.StringEscapeUtils;

public class InputSanitizer {

    private InputSanitizer() {}

    public static String sanitizeHeaders(String input) {
        if (input == null) return null;
        return input.replaceAll("[\r\n]", "").trim();
    }

    public static String escapeHtml(String input) {
        if (input == null) return null;
        return StringEscapeUtils.escapeHtml4(input);
    }

    public static String sanitizeLogs(String input) {
        if (input == null) return null;
        return input.replaceAll("[\r\n\t]", " ");
    }
}
