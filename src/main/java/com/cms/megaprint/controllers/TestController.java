package com.cms.megaprint.controllers;

import com.cms.megaprint.models.ServiceCategory;
import com.cms.megaprint.services.ServiceCategoryService;
import com.cms.megaprint.services.ServiceUnitService;
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
