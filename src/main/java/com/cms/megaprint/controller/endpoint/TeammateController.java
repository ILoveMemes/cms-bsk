package com.cms.megaprint.controller.endpoint;

import com.cms.megaprint.controller.common.CrudController;
import com.cms.megaprint.model.Teammate;
import com.cms.megaprint.service.common.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teammates")
public class TeammateController extends CrudController<Teammate, Long> {

    public TeammateController(CrudService<Teammate, Long> service) {
        super(service);
    }
}
