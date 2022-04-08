package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.configuration.VarConfig;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.SiteSection;
import com.cms.megaprint.repository.intface.CrudRepo;
import com.cms.megaprint.repository.intface.SiteSectionRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SiteSectionRepositoryImpl implements CrudRepo<SiteSection, Long>, SiteSectionRepository {

    protected final HibernateSessionFactoryUtil hibernateSessionFactoryUtil;
    private final VarConfig varConfig;

    public SiteSectionRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil, VarConfig varConfig) {
        this.hibernateSessionFactoryUtil = hibernateSessionFactoryUtil;
        this.varConfig = varConfig;
    }

    @Override
    public Optional<SiteSection> findById(Long id) {
        SiteSection obj = hibernateSessionFactoryUtil.getSessionFactory().openSession().get(SiteSection.class, (Serializable) id);
        if (obj == null) {
            return Optional.empty();
        }
        return Optional.of(obj);
    }

    private List<SiteSection> getAllFromDB() {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SiteSection> criteria = builder.createQuery(SiteSection.class);
        criteria.from(SiteSection.class);
        List<SiteSection> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }

    @Override
    public List<SiteSection> findAll() {
        List<SiteSection> data = getAllFromDB();
        // merge data from db with default data from varConfig
        varConfig.getSiteSections().forEach((s, siteSection) -> {
            if (data.stream().filter(i -> i.getKey().equals(s)).count() < 1) {
                siteSection.setKey(s);
                data.add(siteSection);
            }
        });
        return data;
    }

    @Override
    public SiteSection save(SiteSection object) {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(object);
        tx1.commit();
        session.close();
        return object;
    }

    @Override
    public SiteSection update(SiteSection object) {
        // if this object have only default entity
        if (findByKeyInDB(object.getKey()).isEmpty()) {
            return save(object);
        }
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(object);
        tx1.commit();
        session.close();
        return object;
    }

    @Override
    public boolean deleteById(Long id) {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        SiteSection data = session.load(SiteSection.class, (Serializable) id);
        session.delete(data);
        tx1.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(SiteSection object) {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(object);
        tx1.commit();
        session.close();
        return true;
    }

    private Optional<SiteSection> findByKeyInDB(String key) {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("select s from SiteSection s where s.key = '" + key + "'");
        Optional<SiteSection> result;
        if (query.list().size() == 1) {
            result = Optional.of((SiteSection) query.list().get(0));
        } else {
            result = Optional.empty();
        }
        tx1.commit();
        session.close();
        return result;
    }

    @Override
    public Optional<SiteSection> findByKey(String key) {
        Optional<SiteSection> result = findByKeyInDB(key);
        // if no data in db, return default entity
        if (result.isEmpty()) {
            if (varConfig.getSiteSections().containsKey(key)) {
                result = Optional.of(varConfig.getSiteSections().get(key));
            }
        }
        return result;
    }

    @Override
    public HashMap<String, SiteSection> getAsHashMap() {
        return (HashMap<String, SiteSection>) findAll().stream()
                .collect(Collectors.toMap(SiteSection::getKey, Function.identity()));
    }
}
