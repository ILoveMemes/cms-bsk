package com.cms.megaprint.controllers;

import com.cms.megaprint.common.CrudController;
import com.cms.megaprint.models.ServiceCategory;
import com.cms.megaprint.services.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service_categories")
public class ServiceCategoriesController extends CrudController<ServiceCategory, Long> {

    public ServiceCategoriesController(CrudService<ServiceCategory, Long> service) {
        super(service);
    }

}
