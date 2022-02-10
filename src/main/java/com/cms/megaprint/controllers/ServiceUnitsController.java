package com.cms.megaprint.controllers;

import com.cms.megaprint.common.CrudController;
import com.cms.megaprint.models.ServiceUnit;
import com.cms.megaprint.services.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service_units")
public class ServiceUnitsController extends CrudController<ServiceUnit, Long> {

    public ServiceUnitsController(CrudService<ServiceUnit, Long> service) {
        super(service);
    }
    
}
