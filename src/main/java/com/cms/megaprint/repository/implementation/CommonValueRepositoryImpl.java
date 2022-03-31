package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.repository.CommonValueRepository;
import org.springframework.stereotype.Service;

@Service
public class CommonValueRepositoryImpl extends CrudRepoHibernateImpl<CommonValue, Long> implements CommonValueRepository {

    public CommonValueRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }
}
