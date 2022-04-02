package com.cms.megaprint.controller.endpoint.page;

import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.service.common.TextDecoratorService;
import com.cms.megaprint.service.intface.CommonValueService;
import com.cms.megaprint.service.intface.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoodsAdminController {

    private final CommonValueService commonValueService;
    private final GoodsService goodsService;
    private final TextDecoratorService textDecoratorService;

    public GoodsAdminController(CommonValueService commonValueService, GoodsService goodsService, TextDecoratorService textDecoratorService) {
        this.commonValueService = commonValueService;
        this.goodsService = goodsService;
        this.textDecoratorService = textDecoratorService;
    }

    @RequestMapping("admin_goods")
    public String goods(Model model) {

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), textDecoratorService.decorate(cValue.getValue()));
        }

        model.addAttribute("goods", goodsService.findAll());

        return "goods_admin";
    }

}
