package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.ServiceUnit;
import org.springframework.stereotype.Service;

@Service
public class ServiceUnitRepositoryImpl extends CrudRepoHibernateImpl<ServiceUnit, Long> {

    public ServiceUnitRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }

}
