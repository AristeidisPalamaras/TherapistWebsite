package gr.kostasmavrakakis.website.util;

public class InputSanitizer {

    private InputSanitizer() {}

    public static String sanitizeHeaders(String input) {
        if (input == null) return null;
        return input.trim().replaceAll("[\r\n]", "");
    }

    public static String sanitizeLogs(String input) {
        if (input == null) return null;

        String output = input.trim().replaceAll("[\r\n\t]", " ");
        
        output = output.replaceAll("\"", "\"\"");

        if (output.matches("^[=+\\-@].*")) {
            output = "'" + output;
        }

        return output;
    }
}
