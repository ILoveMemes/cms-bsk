package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.User;
import com.cms.megaprint.model.VisitStatistic;
import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.repository.intface.VisitStatisticRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class VisitStatisticRepositoryImpl extends CrudRepoHibernateImpl<VisitStatistic, Long> implements VisitStatisticRepository {
    public VisitStatisticRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }

    @Override
    public Optional<VisitStatistic> findByDate(ZonedDateTime dateTime) {
        dateTime = dateTime.truncatedTo(ChronoUnit.DAYS);
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("select vs from VisitStatistic vs where vs.statTime = :dateTime")
                .setParameter("dateTime", dateTime);
        Optional<VisitStatistic> result;
        if (query.list().size() == 1) {
            result = Optional.of((VisitStatistic) query.list().get(0));
        } else {
            result = Optional.empty();
        }
        tx1.commit();
        session.close();
        return result;
    }
}
