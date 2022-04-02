package com.cms.megaprint.controller.endpoint.page;

import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.model.Goods;
import com.cms.megaprint.service.common.TextDecoratorService;
import com.cms.megaprint.service.intface.CommonValueService;
import com.cms.megaprint.service.intface.GoodsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

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

    @Data
    @AllArgsConstructor
    private class Description {
        private String shrt;
        private String full;
    }

    @RequestMapping("admin_goods")
    public String goods(Model model) {

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), textDecoratorService.decorate(cValue.getValue()));
        }

        model.addAttribute("goods", goodsService.findAll());

        Map<Long, Description> goodsDescription = new HashMap<>();
        for (Goods goods: goodsService.findAll()) {
            goodsDescription.put(
                    goods.getId(),
                    new Description(
                            textDecoratorService.decorate(goods.getShortDescription()),
                            textDecoratorService.decorate(goods.getFullDescription())
                    )
            );
        }

        model.addAttribute("goodsFormattedDescription", goodsDescription);

        return "goods_admin";
    }

}
