package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.ServiceUnit;
import com.cms.megaprint.repository.intface.ServiceUnitRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUnitRepositoryImpl extends CrudRepoHibernateImpl<ServiceUnit, Long> implements ServiceUnitRepository {

    public ServiceUnitRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }


    @Override
    public List<ServiceUnit> findUnitsWithoutCategories() {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("select s from ServiceUnit s where id not in (select id from ServiceCategory)");
        List<ServiceUnit> result = query.list();
        tx1.commit();
        session.close();
        return result;
    }
}
