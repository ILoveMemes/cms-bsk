package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.Message;
import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.repository.intface.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageRepositoryImpl extends CrudRepoHibernateImpl<Message, Long> implements MessageRepository {
    public MessageRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }
}
