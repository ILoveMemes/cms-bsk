package com.cms.megaprint.controllers;

import com.cms.megaprint.common.CrudController;
import com.cms.megaprint.models.Teammate;
import com.cms.megaprint.services.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teammates")
public class TeammateController extends CrudController<Teammate, Long> {

    public TeammateController(CrudService<Teammate, Long> service) {
        super(service);
    }
}
