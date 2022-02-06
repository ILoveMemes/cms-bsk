package com.cms.megaprint.reposimpl;

import com.cms.megaprint.common.CrudRepoHibernateImpl;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.models.ServiceUnit;
import org.springframework.stereotype.Service;

@Service
public class ServiceUnitRepositoryImpl extends CrudRepoHibernateImpl<ServiceUnit, Long> {

    public ServiceUnitRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }

}
