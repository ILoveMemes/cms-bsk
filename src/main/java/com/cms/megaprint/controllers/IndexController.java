package com.cms.megaprint.controllers;

import com.cms.megaprint.configuration.VarConfig;
import com.cms.megaprint.models.CommonValue;
import com.cms.megaprint.services.CommonValueService;
import com.cms.megaprint.services.TextDecoratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final VarConfig varConfig;
    private final CommonValueService commonValueService;
    private final TextDecoratorService textDecoratorService;

    public IndexController(VarConfig varConfig, CommonValueService commonValueService, TextDecoratorService textDecoratorService) {
        this.varConfig = varConfig;
        this.commonValueService = commonValueService;
        this.textDecoratorService = textDecoratorService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model model) {

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), textDecoratorService.decorate(cValue.getValue()));
        }

        return "testIndex";
    }

}
