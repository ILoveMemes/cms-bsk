package com.cms.megaprint.reposimpl;

import com.cms.megaprint.common.CrudRepoHibernateImpl;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.models.Teammate;
import org.springframework.stereotype.Service;

@Service
public class TeammatesRepositoryImpl extends CrudRepoHibernateImpl<Teammate, Long> {
    public TeammatesRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }
}
