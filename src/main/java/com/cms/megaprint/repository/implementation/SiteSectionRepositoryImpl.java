package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.SiteSection;
import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.repository.intface.SiteSectionRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SiteSectionRepositoryImpl extends CrudRepoHibernateImpl<SiteSection, Long> implements SiteSectionRepository {
    public SiteSectionRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }

    @Override
    public Optional<SiteSection> findByKey(String key) {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("select s from SiteSection s where s.key = '" + key + "'");
        Optional<SiteSection> result;
        if (query.list().size() == 1) {
            result = Optional.of((SiteSection)query.list().get(0));
        }
        else {
            result = Optional.empty();
        }
        tx1.commit();
        session.close();
        return result;
    }

    @Override
    public HashMap<String, SiteSection> getAsHashMap() {
        return (HashMap<String, SiteSection>) findAll().stream()
                .collect(Collectors.toMap(SiteSection::getKey, Function.identity()));
    }
}
