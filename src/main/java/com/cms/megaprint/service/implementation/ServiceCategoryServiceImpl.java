package com.cms.megaprint.service.implementation;

import com.cms.megaprint.repository.intface.ServiceCategoryRepository;
import com.cms.megaprint.service.implementation.dflt.DefaultServiceImpl;
import com.cms.megaprint.model.ServiceCategory;
import com.cms.megaprint.repository.intface.CrudRepo;
import com.cms.megaprint.service.intface.ServiceCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategoryServiceImpl extends DefaultServiceImpl<ServiceCategory, Long> implements ServiceCategoryService {

    public ServiceCategoryServiceImpl(CrudRepo<ServiceCategory, Long> repo) {
        super(repo);
    }

    @Override
    public List<ServiceCategory> findAllThatShowOnMain() {
        return ((ServiceCategoryRepository)repo).findAllThatShowOnMain();
    }
}
