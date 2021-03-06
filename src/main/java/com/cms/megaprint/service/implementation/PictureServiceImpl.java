package com.cms.megaprint.service.implementation;

import com.cms.megaprint.service.implementation.dflt.DefaultServiceImpl;
import com.cms.megaprint.model.Picture;
import com.cms.megaprint.repository.intface.PictureRepository;
import com.cms.megaprint.service.intface.PictureService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl extends DefaultServiceImpl<Picture, Long> implements PictureService {

    public PictureServiceImpl(PictureRepository repo) {
        super(repo);
    }

    @Override
    public List<Long> getIdList() {
        return ((PictureRepository)repo).getIdList();
    }
}
