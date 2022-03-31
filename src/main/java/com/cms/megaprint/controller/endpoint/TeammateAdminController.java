package com.cms.megaprint.controller.endpoint;

import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.service.intface.CommonValueService;
import com.cms.megaprint.service.intface.TeammateService;
import com.cms.megaprint.service.intface.TeammateSocialNetworkService;
import com.cms.megaprint.service.common.TextDecoratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeammateAdminController {

    private final CommonValueService commonValueService;
    private final TextDecoratorService textDecoratorService;
    private final TeammateService teammateService;
    private final TeammateSocialNetworkService teammateSocialNetworkService;

    public TeammateAdminController(CommonValueService commonValueService, TextDecoratorService textDecoratorService, TeammateService teammateService, TeammateSocialNetworkService teammateSocialNetworkService) {
        this.commonValueService = commonValueService;
        this.textDecoratorService = textDecoratorService;
        this.teammateService = teammateService;
        this.teammateSocialNetworkService = teammateSocialNetworkService;
    }

    @RequestMapping("admin_teammate")
    public String teammate(Model model) {

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), textDecoratorService.decorate(cValue.getValue()));
        }

        model.addAttribute("teammates", teammateService.findAll());
        return "teammate_admin";

    }

}
