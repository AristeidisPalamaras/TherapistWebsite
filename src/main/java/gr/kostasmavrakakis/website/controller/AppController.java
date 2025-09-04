package gr.kostasmavrakakis.website.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import gr.kostasmavrakakis.website.service.EmailService;
import gr.kostasmavrakakis.website.dto.MessageDTO;


@Controller
public class AppController {

    private final EmailService emailService;

    public AppController(EmailService emailService) {
        this.emailService = emailService;
    }

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

    @GetMapping("/contact")
    public String contactGr(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        model.addAttribute("messageDTO", new MessageDTO());
        return "contactGr";
    }

    @GetMapping("/en/contact")
    public String contactEn(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        model.addAttribute("messageDTO", new MessageDTO());
        return "contactEn";
    }

    @PostMapping("/contact")
    public String submitContactFormGr(
        @Valid @ModelAttribute("MessageDTO") MessageDTO messageDTO,
        BindingResult bindingResult,
        HttpServletRequest request,
        Model model
    ) {
        model.addAttribute("currentUrl", request.getRequestURI());

         if (bindingResult.hasErrors()) {
            return "contactGr";
        }

        try {
            emailService.sendMessage(messageDTO);
            model.addAttribute("success", true);
            model.addAttribute("messageDTO", new MessageDTO());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "There was a problem sending your message. Please try again later.");
            model.addAttribute("messageDTO", messageDTO);
        }

        return "contactGr";
    }

    @PostMapping("/en/contact")
    public String submitContactFormEn(
        @Valid @ModelAttribute("MessageDTO") MessageDTO messageDTO,
        BindingResult bindingResult,
        HttpServletRequest request,
        Model model
    ) {
        model.addAttribute("currentUrl", request.getRequestURI());

        if (bindingResult.hasErrors()) {
            return "contactEn";
        }

        try {
            emailService.sendMessage(messageDTO);
            model.addAttribute("success", true);
            model.addAttribute("messageDTO", new MessageDTO());
        } catch (Exception e) {
            model.addAttribute("error", "There was a problem sending your message. Please try again later.");
            model.addAttribute("messageDTO", messageDTO);
        }

        return "contactEn";
    }
}
