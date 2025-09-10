package gr.kostasmavrakakis.website.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    public PageController() { }

    @GetMapping("/")
    public String homeGr(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        return "homeGr";
    }

    @GetMapping("/en")
    public String homeEn(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        return "homeEn";
    }

    @GetMapping("/approach")
    public String approachGr(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        return "approachGr";
    }

    @GetMapping("/en/approach")
    public String approachEn(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        return "approachEn";
    }

    @GetMapping("/trauma_therapy")
    public String traumaTherapyGr(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        return "traumaTherapyGr";
    }

    @GetMapping("/en/trauma_therapy")
    public String traumaTherapyEn(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        return "traumaTherapyEn";
    }

    @GetMapping("/cv")
    public String aboutMeGr(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        return "cvGr";
    }

    @GetMapping("/en/cv")
    public String aboutMeEn(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        return "cvEn";
    }
}
