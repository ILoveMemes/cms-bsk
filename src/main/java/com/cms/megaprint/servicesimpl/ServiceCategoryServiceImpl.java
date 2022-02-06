package com.cms.megaprint.servicesimpl;

import com.cms.megaprint.common.DefaultServiceImpl;
import com.cms.megaprint.models.ServiceCategory;
import com.cms.megaprint.repos.CrudRepo;
import com.cms.megaprint.services.ServiceCategoryService;
import org.springframework.stereotype.Service;

@Service
public class ServiceCategoryServiceImpl extends DefaultServiceImpl<ServiceCategory, Long> implements ServiceCategoryService {

    public ServiceCategoryServiceImpl(CrudRepo<ServiceCategory, Long> repo) {
        super(repo);
    }

}
