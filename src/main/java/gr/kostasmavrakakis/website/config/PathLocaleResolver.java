package gr.kostasmavrakakis.website.config;

import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

public class PathLocaleResolver extends AcceptHeaderLocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String uri = request.getRequestURI().toLowerCase();
        if (uri.startsWith("/en")) {
            return Locale.ENGLISH;
        }
        return new Locale("el");
    }
}
