package com.cms.megaprint.reposimpl;

import com.cms.megaprint.common.CrudRepoHibernateImpl;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.models.TeammatesSocialNetwork;
import org.springframework.stereotype.Service;

@Service
public class TeammatesSocialNetworkRepositoryImpl extends CrudRepoHibernateImpl<TeammatesSocialNetwork, Long> {
    public TeammatesSocialNetworkRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }
}
