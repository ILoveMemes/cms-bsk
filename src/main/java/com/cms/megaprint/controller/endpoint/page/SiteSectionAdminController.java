package com.cms.megaprint.controller.endpoint.page;

import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.service.intface.CommonValueService;
import com.cms.megaprint.service.intface.SiteSectionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteSectionAdminController {

    private final SiteSectionService siteSectionService;
    private final CommonValueService commonValueService;

    public SiteSectionAdminController(SiteSectionService siteSectionService, CommonValueService commonValueService) {
        this.siteSectionService = siteSectionService;
        this.commonValueService = commonValueService;
    }

    @RequestMapping("/admin/site_section")
    public String siteSection(Model model) {

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), cValue.getValue());
        }
        model.addAttribute("siteSections", siteSectionService.getAsHashMap());

        return "admin/site_section";
    }

}
