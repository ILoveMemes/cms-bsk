package com.cms.megaprint.service.implementation;

import com.cms.megaprint.service.implementation.dflt.DefaultServiceImpl;
import com.cms.megaprint.model.ServiceUnit;
import com.cms.megaprint.repository.CrudRepo;
import com.cms.megaprint.service.intface.ServiceUnitService;
import org.springframework.stereotype.Service;

@Service
public class ServiceUnitServiceImpl extends DefaultServiceImpl<ServiceUnit, Long> implements ServiceUnitService {

    public ServiceUnitServiceImpl(CrudRepo<ServiceUnit, Long> repo) {
        super(repo);
    }

}
