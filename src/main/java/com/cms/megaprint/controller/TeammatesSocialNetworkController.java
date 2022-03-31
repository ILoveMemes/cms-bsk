package com.cms.megaprint.controller;

import com.cms.megaprint.controller.common.CrudController;
import com.cms.megaprint.model.TeammatesSocialNetwork;
import com.cms.megaprint.service.common.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teammates_social_network")
public class TeammatesSocialNetworkController extends CrudController<TeammatesSocialNetwork, Long> {

    public TeammatesSocialNetworkController(CrudService<TeammatesSocialNetwork, Long> service) {
        super(service);
    }

}
