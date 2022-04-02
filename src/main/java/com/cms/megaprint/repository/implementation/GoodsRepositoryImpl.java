package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.Goods;
import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.repository.intface.GoodsRepository;
import org.springframework.stereotype.Service;

@Service
public class GoodsRepositoryImpl extends CrudRepoHibernateImpl<Goods, Long> implements GoodsRepository {
    public GoodsRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }
}
