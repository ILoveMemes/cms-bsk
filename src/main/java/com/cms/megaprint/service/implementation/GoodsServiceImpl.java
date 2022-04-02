package com.cms.megaprint.service.implementation;

import com.cms.megaprint.model.Goods;
import com.cms.megaprint.repository.intface.CrudRepo;
import com.cms.megaprint.repository.intface.GoodsRepository;
import com.cms.megaprint.service.implementation.dflt.DefaultServiceImpl;
import com.cms.megaprint.service.intface.GoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl extends DefaultServiceImpl<Goods, Long> implements GoodsService {
    public GoodsServiceImpl(CrudRepo<Goods, Long> repo) {
        super(repo);
    }

    @Override
    public List<Goods> findAllThatShowOnMain() {
        return ((GoodsRepository)repo).findAllThatShowOnMain();
    }
}
