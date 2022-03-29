package com.cms.megaprint.controllers;

import com.cms.megaprint.services.TextDecoratorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TextDecoratorController {

    private final TextDecoratorService textDecoratorService;

    public TextDecoratorController(TextDecoratorService textDecoratorService) {
        this.textDecoratorService = textDecoratorService;
    }

    @PostMapping("/decorate")
    @ResponseBody
    public String decorate(@RequestBody(required = false) String text) {
        return textDecoratorService.decorate(text);
    }

}
