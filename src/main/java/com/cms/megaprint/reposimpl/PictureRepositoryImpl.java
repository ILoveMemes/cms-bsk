package com.cms.megaprint.reposimpl;

import com.cms.megaprint.common.CrudRepoHibernateImpl;
import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.models.Picture;
import com.cms.megaprint.repos.PictureRepository;
import org.springframework.stereotype.Service;

@Service
public class PictureRepositoryImpl extends CrudRepoHibernateImpl<Picture, Long> implements PictureRepository {

    public PictureRepositoryImpl(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        super(hibernateSessionFactoryUtil);
    }

}
