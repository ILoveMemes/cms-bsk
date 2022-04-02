package com.cms.megaprint.controller.endpoint;

import com.cms.megaprint.controller.common.CrudController;
import com.cms.megaprint.model.Goods;
import com.cms.megaprint.service.common.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController extends CrudController<Goods, Long> {
    public GoodsController(CrudService<Goods, Long> service) {
        super(service);
    }
}
