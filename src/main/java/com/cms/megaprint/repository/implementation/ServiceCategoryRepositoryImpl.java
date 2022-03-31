package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.ServiceCategory;
import org.springframework.stereotype.Service;

@Service
public class ServiceCategoryRepositoryImpl extends CrudRepoHibernateImpl<ServiceCategory, Long> {

    public ServiceCategoryRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }

}
