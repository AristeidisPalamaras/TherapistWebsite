package gr.kostasmavrakakis.website.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CsrfControllerAdvice {
    @ModelAttribute
    public void exposeCsrfToken(Model model, HttpServletRequest request) {
        Object tokenAttr = request.getAttribute(CsrfToken.class.getName());
        if (tokenAttr instanceof CsrfToken ct) {
            DefaultCsrfToken safeToken = new DefaultCsrfToken(
                ct.getHeaderName(),
                ct.getParameterName(),
                ct.getToken()
            );
            model.addAttribute("_csrf", safeToken);
        }
    }
}