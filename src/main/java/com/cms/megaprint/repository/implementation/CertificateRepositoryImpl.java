package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.Certificate;
import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.repository.intface.CertificateRepository;
import org.springframework.stereotype.Service;

@Service
public class CertificateRepositoryImpl extends CrudRepoHibernateImpl<Certificate, Long> implements CertificateRepository {
    public CertificateRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }
}
