package gr.kostasmavrakakis.website.logger;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.opencsv.CSVWriter;

@Component
public class CsvLogger {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter YEAR_FORMATTER = DateTimeFormatter.ofPattern("yyyy");

    public CsvLogger() {
        try {
            Path logsPath = Paths.get("logs");
            if (!Files.exists(logsPath)) {
                Files.createDirectories(logsPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initFile(String path, String[] header) {
        Path filePath = Paths.get(path);
        if (!Files.exists(filePath)) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(path, true))) {
                writer.writeNext(header);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    public void logSuccess(String email) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        writeLine(getSuccessLogPath(), new String[]{timestamp, safe(email)});
    }

    public void logError(String errorType, String email, Exception e) {
        String timestamp = LocalDateTime.now().format(FORMATTER);

        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String stackTrace = sw.toString().replaceAll("\\s+", " ");

        writeLine(getErrorLogPath(), new String[]{errorType, timestamp, safe(email), stackTrace});
    }

    private void writeLine(String filePath, String[] values) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) {
            writer.writeNext(values, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }
}
