package com.cms.megaprint.controller.endpoint.page;

import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.service.common.TextDecoratorService;
import com.cms.megaprint.service.intface.CommonValueService;
import com.cms.megaprint.service.intface.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PictureAdmin {

    private final CommonValueService commonValueService;
    private final PictureService pictureService;
    private final TextDecoratorService textDecoratorService;

    public PictureAdmin(CommonValueService commonValueService, PictureService pictureService, TextDecoratorService textDecoratorService) {
        this.commonValueService = commonValueService;
        this.pictureService = pictureService;
        this.textDecoratorService = textDecoratorService;
    }

    @RequestMapping("admin/picture")
    public String picture(Model model) {

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), textDecoratorService.decorate(cValue.getValue()));
        }

        model.addAttribute("pictureIdList", pictureService.getIdList());

        return "admin/picture";
    }

}
