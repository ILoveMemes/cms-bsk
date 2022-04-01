package com.cms.megaprint.controller.endpoint;

import com.cms.megaprint.configuration.VarConfig;
import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.service.intface.CommonValueService;
import com.cms.megaprint.service.intface.ServiceCategoryService;
import com.cms.megaprint.service.intface.TeammateService;
import com.cms.megaprint.service.common.TextDecoratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final VarConfig varConfig;
    private final CommonValueService commonValueService;
    private final TextDecoratorService textDecoratorService;
    private final ServiceCategoryService serviceCategoryService;
    private final TeammateService teammateService;

    public IndexController(VarConfig varConfig, CommonValueService commonValueService, TextDecoratorService textDecoratorService, ServiceCategoryService serviceCategoryService, TeammateService teammateService) {
        this.varConfig = varConfig;
        this.commonValueService = commonValueService;
        this.textDecoratorService = textDecoratorService;
        this.serviceCategoryService = serviceCategoryService;
        this.teammateService = teammateService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model model) {

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), textDecoratorService.decorate(cValue.getValue()));
        }

        model.addAttribute("serviceCategories", serviceCategoryService.findAllThatShowOnMain());
        model.addAttribute("teammates", teammateService.findAll());

        return "testIndex";
    }

}
