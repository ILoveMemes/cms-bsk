package com.cms.megaprint.controllers;

import com.cms.megaprint.services.CommonValueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    private final CommonValueService commonValueService;

    public TestController(CommonValueService commonValueService) {
        this.commonValueService = commonValueService;
    }

    @RequestMapping("/test")
    public String test(Model model) {
        commonValueService.findAll();
        String msg = "hello";
        model.addAttribute("message", msg);
        return "test";
    }

}
