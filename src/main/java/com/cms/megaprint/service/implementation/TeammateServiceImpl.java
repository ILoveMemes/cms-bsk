package com.cms.megaprint.service.implementation;

import com.cms.megaprint.service.implementation.dflt.DefaultServiceImpl;
import com.cms.megaprint.model.Teammate;
import com.cms.megaprint.repository.intface.CrudRepo;
import com.cms.megaprint.service.intface.TeammateService;
import org.springframework.stereotype.Service;

@Service
public class TeammateServiceImpl extends DefaultServiceImpl<Teammate, Long> implements TeammateService {
    public TeammateServiceImpl(CrudRepo<Teammate, Long> repo) {
        super(repo);
    }
}
