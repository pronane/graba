package com.graba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentsController {

    @GetMapping("/fragments")
    public String getHome() {
        return "fragments";
    }

    @GetMapping("/markup")
    public String markupPage() {
        return "markup";
    }

    @GetMapping("/params")
    public String paramsPage() {
        return "params";
    }

    @GetMapping("/other")
    public String otherPage(Model model) {
        //model.addAttribute("data", StudentUtils.buildStudents());
        return "other";
    }
}