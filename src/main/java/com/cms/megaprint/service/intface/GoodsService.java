package com.cms.megaprint.service.intface;

import com.cms.megaprint.model.Goods;
import com.cms.megaprint.service.common.CrudService;

import java.util.List;

public interface GoodsService extends CrudService<Goods, Long> {

    List<Goods> findAllThatShowOnMain();

}
