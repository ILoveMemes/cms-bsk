package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.Goods;
import com.cms.megaprint.model.Message;
import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.repository.intface.MessageRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageRepositoryImpl extends CrudRepoHibernateImpl<Message, Long> implements MessageRepository {
    public MessageRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }

    @Override
    public Long getUnreadMessageCount() {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("select count(m) from Message m where m.unread = true");
        Long result = (Long) query.getSingleResult();
        tx1.commit();
        session.close();
        return result;
    }
}
