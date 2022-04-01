package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.ServiceCategory;
import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.repository.intface.ServiceCategoryRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategoryRepositoryImpl extends CrudRepoHibernateImpl<ServiceCategory, Long> implements ServiceCategoryRepository {

    public ServiceCategoryRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }

    @Override
    public List<ServiceCategory> findAllThatShowOnMain() {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("select s from ServiceCategory s where s.id in (select category.id from ServiceUnit where showOnMain = true)");
        List<ServiceCategory> result = query.list();
        tx1.commit();
        session.close();
        return result;
    }
}
