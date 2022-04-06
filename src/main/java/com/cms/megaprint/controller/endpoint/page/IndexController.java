package com.cms.megaprint.controller.endpoint.page;

import com.cms.megaprint.configuration.SiteSection;
import com.cms.megaprint.configuration.VarConfig;
import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.model.Description;
import com.cms.megaprint.model.Goods;
import com.cms.megaprint.service.intface.*;
import com.cms.megaprint.service.common.TextDecoratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class IndexController {

    private final VarConfig varConfig;
    private final CommonValueService commonValueService;
    private final TextDecoratorService textDecoratorService;
    private final ServiceCategoryService serviceCategoryService;
    private final TeammateService teammateService;
    private final GoodsService goodsService;
    private final CertificateService certificateService;

    public IndexController(
            VarConfig varConfig,
            CommonValueService commonValueService,
            TextDecoratorService textDecoratorService,
            ServiceCategoryService serviceCategoryService,
            TeammateService teammateService,
            GoodsService goodsService,
            CertificateService certificateService) {
        this.varConfig = varConfig;
        this.commonValueService = commonValueService;
        this.textDecoratorService = textDecoratorService;
        this.serviceCategoryService = serviceCategoryService;
        this.teammateService = teammateService;
        this.goodsService = goodsService;
        this.certificateService = certificateService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model model) {

        model.addAttribute("siteSections", varConfig.getSiteSections());

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), textDecoratorService.decorate(cValue.getValue()));
        }

        model.addAttribute("serviceCategories", serviceCategoryService.findAllThatShowOnMain());
        model.addAttribute("teammates", teammateService.findAll());

        model.addAttribute("goods", goodsService.findAllThatShowOnMain());

        Map<Long, Description> goodsDescription = new HashMap<>();
        for (Goods goods: goodsService.findAllThatShowOnMain()) {
            goodsDescription.put(
                    goods.getId(),
                    Description.of(
                            textDecoratorService.decorate(goods.getShortDescription()),
                            textDecoratorService.decorate(goods.getFullDescription())
                    )
            );
        }

        model.addAttribute("goodsFormattedDescription", goodsDescription);
        model.addAttribute("certificates", certificateService.findAll());

        return "public/index";
    }

}
