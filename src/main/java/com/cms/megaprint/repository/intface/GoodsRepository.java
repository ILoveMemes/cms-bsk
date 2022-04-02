package com.cms.megaprint.repository.intface;

import com.cms.megaprint.model.Goods;

import java.util.List;

public interface GoodsRepository extends CrudRepo<Goods, Long> {

    List<Goods> findAllThatShowOnMain();

}
