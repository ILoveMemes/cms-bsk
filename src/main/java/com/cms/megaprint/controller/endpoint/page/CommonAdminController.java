package com.cms.megaprint.controller.endpoint.page;

import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.service.intface.CommonValueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonAdminController {

    private final CommonValueService commonValueService;

    public CommonAdminController(CommonValueService commonValueService) {
        this.commonValueService = commonValueService;
    }

    @RequestMapping("/admin/common")
    public String admin(Model model) {

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), cValue.getValue());
        }

        return "admin/common";
    }

}
