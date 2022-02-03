package com.cms.megaprint.servicesimpl;

import com.cms.megaprint.common.DefaultServiceImpl;
import com.cms.megaprint.models.Picture;
import com.cms.megaprint.repos.PictureRepository;
import com.cms.megaprint.services.PictureService;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl extends DefaultServiceImpl<Picture, Long> implements PictureService {

    public PictureServiceImpl(PictureRepository repo) {
        super(repo);
    }

}
