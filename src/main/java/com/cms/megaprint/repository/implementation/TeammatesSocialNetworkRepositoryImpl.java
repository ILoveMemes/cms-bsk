package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.TeammatesSocialNetwork;
import com.cms.megaprint.repository.intface.TeammatesSocialNetworkRepository;
import org.springframework.stereotype.Service;

@Service
public class TeammatesSocialNetworkRepositoryImpl extends CrudRepoHibernateImpl<TeammatesSocialNetwork, Long> implements TeammatesSocialNetworkRepository {
    public TeammatesSocialNetworkRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }
}
