package com.cms.megaprint.controller.endpoint;

import com.cms.megaprint.model.ServiceCategory;
import com.cms.megaprint.service.intface.ServiceCategoryService;
import com.cms.megaprint.service.intface.ServiceUnitService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestController {

    private final ServiceUnitService serviceUnitService;
    private final ServiceCategoryService serviceCategoryService;

    public TestController(ServiceUnitService serviceUnitService, ServiceCategoryService serviceCategoryService) {
        this.serviceUnitService = serviceUnitService;
        this.serviceCategoryService = serviceCategoryService;
    }

    @GetMapping("test")
    @ResponseBody
    public String test2() {
        List<ServiceCategory> categories = serviceCategoryService.findAll();
        return "hello";
    }

}
