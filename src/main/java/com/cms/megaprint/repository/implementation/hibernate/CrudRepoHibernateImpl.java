package com.cms.megaprint.repository.implementation.hibernate;

import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.repository.CrudRepo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.core.GenericTypeResolver;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class CrudRepoHibernateImpl<T, ID> implements CrudRepo<T, ID> {

    protected final HibernateSessionFactoryUtil hibernateSessionFactoryUtil;
    private final Class<T> dataType;

    public CrudRepoHibernateImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        this.hibernateSessionFactoryUtil = hibernateSessionFactoryUtil;
        Class<?>[] genericTypes = GenericTypeResolver.resolveTypeArguments(getClass(), CrudRepoHibernateImpl.class);
        dataType = (Class<T>) genericTypes[0];
    }

    @Override
    public Optional<T> findById(ID id) {
        T obj = hibernateSessionFactoryUtil.getSessionFactory().openSession().get(dataType, (Serializable) id);
        if (obj != null) {
            return Optional.of(obj);
        }
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(dataType);
        criteria.from(dataType);
        List<T> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }

    @Override
    public T save(T object) {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(object);
        tx1.commit();
        session.close();
        return object;
    }

    @Override
    public T update(T object) {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(object);
        tx1.commit();
        session.close();
        return object;
    }

    @Override
    public boolean deleteById(ID id) {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        T data = session.load(dataType, (Serializable) id);
        session.delete(data);
        tx1.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(T object) {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(object);
        tx1.commit();
        session.close();
        return true;
    }
}
