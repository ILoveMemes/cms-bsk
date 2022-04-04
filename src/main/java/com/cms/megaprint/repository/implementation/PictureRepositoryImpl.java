package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.model.Goods;
import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.Picture;
import com.cms.megaprint.repository.intface.PictureRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureRepositoryImpl extends CrudRepoHibernateImpl<Picture, Long> implements PictureRepository {

    public PictureRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }

    @Override
    public List<Long> getIdList() {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("select p.id from Picture p");
        List<Long> result = query.list();
        tx1.commit();
        session.close();
        return result;
    }
}
