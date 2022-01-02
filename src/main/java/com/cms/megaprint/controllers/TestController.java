package com.cms.megaprint.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(Model model) {
        String msg = "hello";
        model.addAttribute("message", msg);
        return "test";
    }

}
