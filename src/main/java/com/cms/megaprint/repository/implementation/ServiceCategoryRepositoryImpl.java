package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.ServiceCategory;
import com.cms.megaprint.repository.intface.ServiceCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceCategoryRepositoryImpl extends CrudRepoHibernateImpl<ServiceCategory, Long> implements ServiceCategoryRepository {

    public ServiceCategoryRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }

}
