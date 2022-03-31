package com.cms.megaprint.service.implementation;

import com.cms.megaprint.service.implementation.dflt.DefaultServiceImpl;
import com.cms.megaprint.model.ServiceCategory;
import com.cms.megaprint.repository.CrudRepo;
import com.cms.megaprint.service.intface.ServiceCategoryService;
import org.springframework.stereotype.Service;

@Service
public class ServiceCategoryServiceImpl extends DefaultServiceImpl<ServiceCategory, Long> implements ServiceCategoryService {

    public ServiceCategoryServiceImpl(CrudRepo<ServiceCategory, Long> repo) {
        super(repo);
    }

}
