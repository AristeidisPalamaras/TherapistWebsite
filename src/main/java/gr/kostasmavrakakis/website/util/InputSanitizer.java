package gr.kostasmavrakakis.website.util;

public class InputSanitizer {

    private InputSanitizer() {}

    public static String sanitizeHeaders(String input) {
        if (input == null) return null;
        return input.replaceAll("[\r\n]", "").trim();
    }

    public static String sanitizeLogs(String input) {
        if (input == null) return null;
        return input.replaceAll("[\r\n\t]", " ");
    }
}
