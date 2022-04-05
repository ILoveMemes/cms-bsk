package com.cms.megaprint.controller.endpoint.page;


import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.service.common.TextDecoratorService;
import com.cms.megaprint.service.intface.CertificateService;
import com.cms.megaprint.service.intface.CommonValueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CertificateAdminController {

    private final CommonValueService commonValueService;
    private final CertificateService certificateService;
    private final TextDecoratorService textDecoratorService;

    public CertificateAdminController(CommonValueService commonValueService, CertificateService certificateService, TextDecoratorService textDecoratorService) {
        this.commonValueService = commonValueService;
        this.certificateService = certificateService;
        this.textDecoratorService = textDecoratorService;
    }

    @RequestMapping("admin/certificates")
    public String certificates(Model model) {

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), textDecoratorService.decorate(cValue.getValue()));
        }

        model.addAttribute("certificates", certificateService.findAll());

        return "admin/certificates";
    }

}
