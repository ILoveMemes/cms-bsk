package com.cms.megaprint.reposimpl;

import com.cms.megaprint.common.CrudRepoHibernateImpl;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.models.ServiceCategory;
import org.springframework.stereotype.Service;

@Service
public class ServiceCategoryRepositoryImpl extends CrudRepoHibernateImpl<ServiceCategory, Long> {

    public ServiceCategoryRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }

}
