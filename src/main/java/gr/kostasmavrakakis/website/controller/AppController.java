package gr.kostasmavrakakis.website.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.mail.MailException;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.context.MessageSource;
import java.util.Locale;

import io.github.bucket4j.Bucket;

import gr.kostasmavrakakis.website.service.EmailService;
import gr.kostasmavrakakis.website.dto.MessageDTO;
import gr.kostasmavrakakis.website.logger.CsvLogger;
import gr.kostasmavrakakis.website.service.RateLimiterService;


@Controller
public class AppController {

    private final EmailService emailService;
    private final MessageSource messageSource;
    private final CsvLogger csvLogger;
    private final RateLimiterService rateLimiterService;

    public AppController(
        EmailService emailService, 
        MessageSource messageSource, 
        CsvLogger csvLogger,
        RateLimiterService rateLimiterService
    ) {
        this.emailService = emailService;
        this.messageSource = messageSource;
        this.csvLogger = csvLogger;
        this.rateLimiterService = rateLimiterService;
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
        if (!model.containsAttribute("messageDTO")) {
            model.addAttribute("messageDTO", new MessageDTO());
        }
        return "contactGr";
    }

    @GetMapping("/en/contact")
    public String contactEn(HttpServletRequest request, Model model) {
        model.addAttribute("currentUrl", request.getRequestURI());
        if (!model.containsAttribute("messageDTO")) {
            model.addAttribute("messageDTO", new MessageDTO());
        }
        return "contactEn";
    }

    @PostMapping("/contact")
    public String submitContactFormGr(
        @Valid @ModelAttribute("messageDTO") MessageDTO messageDTO,
        BindingResult bindingResult,
        @RequestParam(value = "info", required = false) String info,
        HttpServletRequest request,
        RedirectAttributes redirectAttributes,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentUrl", request.getRequestURI());
            model.addAttribute("error", messageSource.getMessage("validation.form.warning", null, new Locale("el")));
            return "contactGr";
        }

        if (info != null && !info.isBlank()) {
            csvLogger.logError("SPAM", messageDTO.getEmail(), new Exception("Honeypot caught Winnie the Pooh!"));
            return "redirect:/contact";
        }

        Bucket bucket = rateLimiterService.resolveBucket(request);
        if (!bucket.tryConsume(1)) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("submit.rateLimit", null, new Locale("el")));
            redirectAttributes.addFlashAttribute("messageDTO", messageDTO);
            return "redirect:/contact";
        }

        try {
            emailService.sendMessage(messageDTO);
            csvLogger.logSuccess(messageDTO.getEmail());
            redirectAttributes.addFlashAttribute("success", messageSource.getMessage("submit.success", null, new Locale("el")));
            return "redirect:/contact";
        } catch (MailException e) {
            csvLogger.logError("MAIL", messageDTO.getEmail(), e);
        } catch (Exception e) {
            csvLogger.logError("SYSTEM", messageDTO.getEmail(), e);
        }
        redirectAttributes.addFlashAttribute("error", messageSource.getMessage("submit.error", null, new Locale("el")));
        redirectAttributes.addFlashAttribute("messageDTO", messageDTO);
        return "redirect:/contact";
    }

    @PostMapping("/en/contact")
    public String submitContactFormEn(
        @Valid @ModelAttribute("messageDTO") MessageDTO messageDTO,
        BindingResult bindingResult,
        @RequestParam(value = "info", required = false) String info,
        HttpServletRequest request,
        RedirectAttributes redirectAttributes,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentUrl", request.getRequestURI());
            model.addAttribute("error", messageSource.getMessage("validation.form.warning", null, Locale.ENGLISH));
            return "contactEn";
        }

        if (info != null && !info.isBlank()) {
            csvLogger.logError("SPAM", messageDTO.getEmail(), new Exception("Honeypot caught Winnie the Pooh!"));
            return "redirect:/contact";
        }

        Bucket bucket = rateLimiterService.resolveBucket(request);
        if (!bucket.tryConsume(1)) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("submit.rateLimit", null, Locale.ENGLISH));
            redirectAttributes.addFlashAttribute("messageDTO", messageDTO);
            return "redirect:/en/contact";
        }

        try {
            emailService.sendMessage(messageDTO);
            csvLogger.logSuccess(messageDTO.getEmail());
            redirectAttributes.addFlashAttribute("success", messageSource.getMessage("submit.success", null, Locale.ENGLISH));
            return "redirect:/en/contact";
        } catch (MailException e) {
            csvLogger.logError("MAIL", messageDTO.getEmail(), e);
        } catch (Exception e) {
            csvLogger.logError("SYSTEM", messageDTO.getEmail(), e);
        }
        redirectAttributes.addFlashAttribute("error", messageSource.getMessage("submit.error", null, Locale.ENGLISH));
        redirectAttributes.addFlashAttribute("messageDTO", messageDTO);
        return "redirect:/en/contact";
    }
}
