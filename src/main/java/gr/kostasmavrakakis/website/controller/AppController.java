package gr.kostasmavrakakis.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @GetMapping("/")
    public String homeGr() {
        return "homeGr";
    }

    @GetMapping("/en")
    public String homeEn() {
        return "homeEn";
    }

    @GetMapping("/proseggisi")
    public String approachGr() {
        return "approachGr";
    }

    @GetMapping("/en/my-approach")
    public String approachEn() {
        return "approachEn";
    }

    @GetMapping("/traumatotherapeia")
    public String traumaTherapyGr(){
        return "traumaTherapyGr";
    }

    @GetMapping("/en/trauma-therapy")
    public String traumaTherapyEn(){
        return "traumaTherapyEn";
    }

    @GetMapping("/biografiko")
    public String aboutMeGr() {
        return "aboutMeGr";
    }

    @GetMapping("/en/about-me")
    public String aboutMeEn() {
        return "aboutMeEn";
    }

    @GetMapping("/epikoinonia")
    public String contactGr() {
        return "contactGr";
    }

    @GetMapping("/en/contact")
    public String contactEn() {
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
