package com.cms.megaprint.servicesimpl;

import com.cms.megaprint.common.DefaultServiceImpl;
import com.cms.megaprint.models.Teammate;
import com.cms.megaprint.repos.CrudRepo;
import com.cms.megaprint.services.TeammateService;
import org.springframework.stereotype.Service;

@Service
public class TeammateServiceImpl extends DefaultServiceImpl<Teammate, Long> implements TeammateService {
    public TeammateServiceImpl(CrudRepo<Teammate, Long> repo) {
        super(repo);
    }
}
