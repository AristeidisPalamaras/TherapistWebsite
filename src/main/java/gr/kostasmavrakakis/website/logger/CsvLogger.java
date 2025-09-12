package gr.kostasmavrakakis.website.logger;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.StringBuilder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import com.opencsv.CSVWriter;

import gr.kostasmavrakakis.website.util.InputSanitizer;

@Component
public class CsvLogger {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter YEAR_FORMATTER = DateTimeFormatter.ofPattern("yyyy");

    public CsvLogger() {
        Path logsPath = Paths.get("logs");
        if (!Files.exists(logsPath)) {
            try {
                Files.createDirectories(logsPath);

                // Set directory permissions (for Linux/OS only)
                Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwx------");
                try {
                    Files.setPosixFilePermissions(logsPath, perms);
                } catch (UnsupportedOperationException e) {
                    System.out.println("Warning: Could not set POSIX permissions on logs directory.");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void logSuccess(String email) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String safeEmail = InputSanitizer.sanitizeLogs(email);

        writeLine(getSuccessLogPath(), new String[]{timestamp, safeEmail});
    }

    public void logError(String errorType, String email, Exception e) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String safeEmail = InputSanitizer.sanitizeLogs(email);
        String safeStacktrace = InputSanitizer.sanitizeLogs(buildMessage(e));

        writeLine(getErrorLogPath(), new String[]{errorType, timestamp, safeEmail, safeStacktrace});
    }

    private String getSuccessLogPath() {
        String year = LocalDateTime.now().format(YEAR_FORMATTER);
        String filePath = "logs/messages_success_" + year + ".csv";
        initFile(filePath, new String[]{"timestamp", "email"});

        return filePath;
    }

    private String getErrorLogPath() {
        String year = LocalDateTime.now().format(YEAR_FORMATTER);
        String filePath = "logs/messages_error_" + year + ".csv";
        initFile(filePath,  new String[]{"errorType", "timestamp", "email", "stackTrace"});

        return filePath;
    }

    private void initFile(String path, String[] header) {
        Path filePath = Paths.get(path);
        if (!Files.exists(filePath)) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(path, true))) {
                writer.writeNext(header);

                // Set file permissions (for Linux/OS only)
                Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-------");
                try {
                    Files.setPosixFilePermissions(filePath, perms);
                } catch (UnsupportedOperationException e) {
                    System.out.println("Warning: Could not set POSIX file permissions on " + filePath);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeLine(String filePath, String[] values) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) {
            writer.writeNext(values);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildMessage(Throwable e) {
        StringBuilder sb = new StringBuilder();

        Throwable current = e;
        while (current != null) {
            sb.append(current.getClass().getSimpleName());

            if (current.getMessage() != null) {
                String msg = sanitize(current.getMessage());
                sb.append(": ").append(msg);
            }

            current = current.getCause();
            if (current != null) {
                sb.append(" -> caused by: ");
            }
        }
        return sb.toString();
    }

    private String sanitize(String msg) {
        if (msg != null) {
            msg = msg.replaceAll("(?i)password=[^\\s&]+", "password=***");
            msg = msg.replaceAll("(?i)authorization:.*", "authorization:***");
            msg = msg.replaceAll("(?i)secret=[^\\s&]+", "secret=***");
        }
        return msg;
    }
}
