package com.cms.megaprint.controller.endpoint.page;

import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.model.ServiceUnit;
import com.cms.megaprint.service.intface.CommonValueService;
import com.cms.megaprint.service.intface.ServiceCategoryService;
import com.cms.megaprint.service.common.TextDecoratorService;
import com.cms.megaprint.service.intface.ServiceUnitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ServiceAdminController {

    private final CommonValueService commonValueService;
    private final TextDecoratorService textDecoratorService;
    private final ServiceCategoryService serviceCategoryService;
    private final ServiceUnitService serviceUnitService;

    public ServiceAdminController(CommonValueService commonValueService, TextDecoratorService textDecoratorService, ServiceCategoryService serviceCategoryService, ServiceUnitService serviceUnitService) {
        this.commonValueService = commonValueService;
        this.textDecoratorService = textDecoratorService;
        this.serviceCategoryService = serviceCategoryService;
        this.serviceUnitService = serviceUnitService;
    }

    @RequestMapping("admin/service")
    public String service(Model model) {

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), textDecoratorService.decorate(cValue.getValue()));
        }

        List<ServiceUnit>  units = serviceUnitService.findUnitsWithoutCategories();

        model.addAttribute("haveUnitsWithoutCategories", units.size() > 0);
        model.addAttribute("unitsWithoutCategories", units);
        model.addAttribute("serviceCategories", serviceCategoryService.findAll());

        return "admin/service";

    }

}
