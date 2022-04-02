package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.Goods;
import com.cms.megaprint.model.ServiceCategory;
import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.repository.intface.GoodsRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsRepositoryImpl extends CrudRepoHibernateImpl<Goods, Long> implements GoodsRepository {
    public GoodsRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }

    @Override
    public List<Goods> findAllThatShowOnMain() {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("select s from Goods s where s.showOnMain = true");
        List<Goods> result = query.list();
        tx1.commit();
        session.close();
        return result;
    }
}
