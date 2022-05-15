package com.cms.megaprint.controller.endpoint.page;

import com.cms.megaprint.configuration.VarConfig;
import com.cms.megaprint.model.*;
import com.cms.megaprint.service.intface.*;
import com.cms.megaprint.service.common.TextDecoratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    private final VarConfig varConfig;
    private final CommonValueService commonValueService;
    private final TextDecoratorService textDecoratorService;
    private final ServiceCategoryService serviceCategoryService;
    private final TeammateService teammateService;
    private final GoodsService goodsService;
    private final CertificateService certificateService;
    private final SiteSectionService siteSectionService;
    private final VisitStatisticService visitStatisticService;

    public IndexController(
            VarConfig varConfig,
            CommonValueService commonValueService,
            TextDecoratorService textDecoratorService,
            ServiceCategoryService serviceCategoryService,
            TeammateService teammateService,
            GoodsService goodsService,
            CertificateService certificateService,
            SiteSectionService siteSectionService,
            VisitStatisticService visitStatisticService) {
        this.varConfig = varConfig;
        this.commonValueService = commonValueService;
        this.textDecoratorService = textDecoratorService;
        this.serviceCategoryService = serviceCategoryService;
        this.teammateService = teammateService;
        this.goodsService = goodsService;
        this.certificateService = certificateService;
        this.siteSectionService = siteSectionService;
        this.visitStatisticService = visitStatisticService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(HttpServletRequest httpServletRequest, Model model) {

        visitStatisticService.addVisitToStatistic(httpServletRequest.getRemoteAddr(), ZonedDateTime.now());

        model.addAttribute("siteSections", siteSectionService.getAsHashMap());

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), textDecoratorService.decorate(cValue.getValue()));
        }

        model.addAttribute("serviceCategories", serviceCategoryService.findAllThatShowOnMain());

        List<Teammate> teammateList = teammateService.findAll();
        model.addAttribute("teammates", teammateList);
        String teammatesClass = "col-md-3";
        if ((teammateList.size() > 0) && (teammateList.size() < 4)) {
            teammatesClass = "col-md-" + String.valueOf(12 / teammateList.size());
        }
        model.addAttribute("teammates_class", teammatesClass);

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

        List<Certificate> certificateList = certificateService.findAll();
        model.addAttribute("certificates", certificateList);
        String certificatesClass = "col-md-3";
        if ((certificateList.size() > 0) && (certificateList.size() < 5)) {
            certificatesClass = "col-md-" + String.valueOf(12 / certificateList.size());
        }
        model.addAttribute("certificates_class", certificatesClass);

        return "public/index";
    }

}
