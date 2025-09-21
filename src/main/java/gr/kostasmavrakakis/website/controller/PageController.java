package gr.kostasmavrakakis.website.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.context.MessageSource;
import java.util.Locale;

@Controller
public class PageController {

    private final MessageSource messageSource;

    public PageController(MessageSource messageSource) { 
        this.messageSource = messageSource;
    }

    @GetMapping("/")
    public String homeGr(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        model.addAttribute("title", messageSource.getMessage("page.title.home", null, new Locale("el")));
        model.addAttribute("description", messageSource.getMessage("page.description.home", null, new Locale("el")));
        return "homeGr";
    }

    @GetMapping("/en")
    public String homeEn(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        model.addAttribute("title", messageSource.getMessage("page.title.home", null, Locale.ENGLISH));
        model.addAttribute("description", messageSource.getMessage("page.description.home", null, Locale.ENGLISH));
        return "homeEn";
    }

    @GetMapping("/cv")
    public String aboutMeGr(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        model.addAttribute("title", messageSource.getMessage("page.title.cv", null, new Locale("el")));
        model.addAttribute("description", messageSource.getMessage("page.description.cv", null, new Locale("el")));
        return "cvGr";
    }

    @GetMapping("/en/cv")
    public String aboutMeEn(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        model.addAttribute("title", messageSource.getMessage("page.title.cv", null, Locale.ENGLISH));
        model.addAttribute("description", messageSource.getMessage("page.description.cv", null, Locale.ENGLISH));
        return "cvEn";
    }

    @GetMapping("/approach")
    public String approachGr(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        model.addAttribute("title", messageSource.getMessage("page.title.approach", null, new Locale("el")));
        model.addAttribute("description", messageSource.getMessage("page.description.approach", null, new Locale("el")));
        return "approachGr";
    }

    @GetMapping("/en/approach")
    public String approachEn(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        model.addAttribute("title", messageSource.getMessage("page.title.approach", null, Locale.ENGLISH));
        model.addAttribute("description", messageSource.getMessage("page.description.approach", null, Locale.ENGLISH));
        return "approachEn";
    }

    @GetMapping("/trauma_therapy")
    public String traumaTherapyGr(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        model.addAttribute("title", messageSource.getMessage("page.title.traumatherapy", null, new Locale("el")));
        model.addAttribute("description", messageSource.getMessage("page.description.traumatherapy", null, new Locale("el")));
        return "traumaTherapyGr";
    }

    @GetMapping("/en/trauma_therapy")
    public String traumaTherapyEn(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        model.addAttribute("title", messageSource.getMessage("page.title.traumatherapy", null, Locale.ENGLISH));
        model.addAttribute("description", messageSource.getMessage("page.description.traumatherapy", null, Locale.ENGLISH));
        return "traumaTherapyEn";
    }
}
