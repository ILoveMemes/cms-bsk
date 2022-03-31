package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.Teammate;
import com.cms.megaprint.repository.intface.TeammateRepository;
import org.springframework.stereotype.Service;

@Service
public class TeammatesRepositoryImpl extends CrudRepoHibernateImpl<Teammate, Long> implements TeammateRepository {
    public TeammatesRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }
}
