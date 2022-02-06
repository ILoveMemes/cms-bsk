package com.cms.megaprint.servicesimpl;

import com.cms.megaprint.common.DefaultServiceImpl;
import com.cms.megaprint.models.ServiceUnit;
import com.cms.megaprint.repos.CrudRepo;
import com.cms.megaprint.services.ServiceUnitService;
import org.springframework.stereotype.Service;

@Service
public class ServiceUnitServiceImpl extends DefaultServiceImpl<ServiceUnit, Long> implements ServiceUnitService {

    public ServiceUnitServiceImpl(CrudRepo<ServiceUnit, Long> repo) {
        super(repo);
    }

}
