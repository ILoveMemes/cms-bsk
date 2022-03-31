package com.cms.megaprint.controller.endpoint;

import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.service.intface.CommonValueService;
import com.cms.megaprint.service.intface.ServiceCategoryService;
import com.cms.megaprint.service.common.TextDecoratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServiceController {

    private final CommonValueService commonValueService;
    private final TextDecoratorService textDecoratorService;
    private final ServiceCategoryService serviceCategoryService;

    public ServiceController(CommonValueService commonValueService, TextDecoratorService textDecoratorService, ServiceCategoryService serviceCategoryService) {
        this.commonValueService = commonValueService;
        this.textDecoratorService = textDecoratorService;
        this.serviceCategoryService = serviceCategoryService;
    }

    @RequestMapping("service")
    public String service(Model model) {

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), textDecoratorService.decorate(cValue.getValue()));
        }

        model.addAttribute("serviceCategories", serviceCategoryService.findAll());

        return "service";
    }

}
