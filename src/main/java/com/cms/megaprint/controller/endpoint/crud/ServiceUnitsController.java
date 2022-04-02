package com.cms.megaprint.controller.endpoint.crud;

import com.cms.megaprint.controller.common.CrudController;
import com.cms.megaprint.model.ServiceUnit;
import com.cms.megaprint.service.common.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service_units")
public class ServiceUnitsController extends CrudController<ServiceUnit, Long> {

    public ServiceUnitsController(CrudService<ServiceUnit, Long> service) {
        super(service);
    }
    
}
