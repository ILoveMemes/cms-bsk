package com.cms.megaprint.repository.implementation;

import com.cms.megaprint.repository.implementation.hibernate.CrudRepoHibernateImpl;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.model.Picture;
import com.cms.megaprint.repository.intface.PictureRepository;
import org.springframework.stereotype.Service;

@Service
public class PictureRepositoryImpl extends CrudRepoHibernateImpl<Picture, Long> implements PictureRepository {

    public PictureRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }

}
