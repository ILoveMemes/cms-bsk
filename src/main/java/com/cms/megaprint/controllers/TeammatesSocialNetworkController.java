package com.cms.megaprint.controllers;

import com.cms.megaprint.common.CrudController;
import com.cms.megaprint.models.TeammatesSocialNetwork;
import com.cms.megaprint.services.CrudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teammates_social_network")
public class TeammatesSocialNetworkController extends CrudController<TeammatesSocialNetwork, Long> {

    public TeammatesSocialNetworkController(CrudService<TeammatesSocialNetwork, Long> service) {
        super(service);
    }

}
