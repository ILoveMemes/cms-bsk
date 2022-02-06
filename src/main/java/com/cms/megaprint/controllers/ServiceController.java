package com.cms.megaprint.controllers;

import com.cms.megaprint.models.CommonValue;
import com.cms.megaprint.services.CommonValueService;
import com.cms.megaprint.services.TextDecoratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServiceController {

    private final CommonValueService commonValueService;
    private final TextDecoratorService textDecoratorService;

    public ServiceController(CommonValueService commonValueService, TextDecoratorService textDecoratorService) {
        this.commonValueService = commonValueService;
        this.textDecoratorService = textDecoratorService;
    }

    @RequestMapping("service")
    public String service(Model model) {

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), textDecoratorService.decorate(cValue.getValue()));
        }

        return "service";
    }

}
