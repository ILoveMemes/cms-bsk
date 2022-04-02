package com.cms.megaprint.controller.endpoint.crud;

import com.cms.megaprint.controller.common.CrudController;
import com.cms.megaprint.model.ServiceCategory;
import com.cms.megaprint.service.common.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service_categories")
public class ServiceCategoriesController extends CrudController<ServiceCategory, Long> {

    public ServiceCategoriesController(CrudService<ServiceCategory, Long> service) {
        super(service);
    }

}
