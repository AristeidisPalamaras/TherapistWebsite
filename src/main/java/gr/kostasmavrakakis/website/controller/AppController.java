package gr.kostasmavrakakis.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @GetMapping("/")
    public String homeGr(Model model) {
        model.addAttribute("currentPage", "homeGr");
        return "homeGr";
    }

    @GetMapping("/en")
    public String homeEn(Model model) {
        model.addAttribute("currentPage", "homeEn");
        return "homeEn";
    }

    @GetMapping("/proseggisi")
    public String approachGr(Model model) {
        model.addAttribute("currentPage", "approachGr");
        return "approachGr";
    }

    @GetMapping("/en/my-approach")
    public String approachEn(Model model) {
        model.addAttribute("currentPage", "approachEn");
        return "approachEn";
    }

    @GetMapping("/traumatotherapeia")
    public String traumaTherapyGr(Model model) {
        model.addAttribute("currentPage", "traumaTherapyGr");
        return "traumaTherapyGr";
    }

    @GetMapping("/en/trauma-therapy")
    public String traumaTherapyEn(Model model) {
        model.addAttribute("currentPage", "traumaTherapyEn");
        return "traumaTherapyEn";
    }

    @GetMapping("/biografiko")
    public String aboutMeGr(Model model) {
        model.addAttribute("currentPage", "aboutMeGr");
        return "aboutMeGr";
    }

    @GetMapping("/en/about-me")
    public String aboutMeEn(Model model) {
        model.addAttribute("currentPage", "aboutMeEn");
        return "aboutMeEn";
    }

    @GetMapping("/epikoinonia")
    public String contactGr(Model model) {
        model.addAttribute("currentPage", "contactGr");
        return "contactGr";
    }

    @GetMapping("/en/contact")
    public String contactEn(Model model) {
        model.addAttribute("currentPage", "contactEn");
        return "contactEn";
    }

    @PostMapping("/epikoinonia")
    public String contactGr(@RequestParam Object message) {
        return "contactGr";
    }

    @PostMapping("/en/contact")
    public String contactEn(@RequestParam Object message) {
        return "contactEn";
    }
}
