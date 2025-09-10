package gr.kostasmavrakakis.website.limiter;

import org.springframework.stereotype.Component;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.time.Duration;

@Component
public class RateLimiter {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(HttpServletRequest request) {
        String ip = getClientIP(request);

        return cache.computeIfAbsent(ip, k -> newBucket());
    }

    private Bucket newBucket() {
        // Allow 5 submissions per 5 minutes
        Bandwidth limit = Bandwidth.builder()
                .capacity(5)
                .refillIntervally(5, Duration.ofMinutes(5))
                .build();
        return Bucket.builder().addLimit(limit).build();
    }

    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0]; // take first if behind proxy
    }
}
