package com.cms.megaprint.controllers;

import com.cms.megaprint.models.CommonValue;
import com.cms.megaprint.services.CommonValueService;
import com.cms.megaprint.services.ServiceCategoryService;
import com.cms.megaprint.services.TextDecoratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServiceAdminController {

    private final CommonValueService commonValueService;
    private final TextDecoratorService textDecoratorService;
    private final ServiceCategoryService serviceCategoryService;

    public ServiceAdminController(CommonValueService commonValueService, TextDecoratorService textDecoratorService, ServiceCategoryService serviceCategoryService) {
        this.commonValueService = commonValueService;
        this.textDecoratorService = textDecoratorService;
        this.serviceCategoryService = serviceCategoryService;
    }

    @RequestMapping("admin_service")
    public String service(Model model) {

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), textDecoratorService.decorate(cValue.getValue()));
        }

        model.addAttribute("serviceCategories", serviceCategoryService.findAll());

        return "service_admin";

    }

}
