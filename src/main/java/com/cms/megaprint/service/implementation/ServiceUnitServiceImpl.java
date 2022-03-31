package com.cms.megaprint.service.implementation;

import com.cms.megaprint.repository.intface.ServiceUnitRepository;
import com.cms.megaprint.service.implementation.dflt.DefaultServiceImpl;
import com.cms.megaprint.model.ServiceUnit;
import com.cms.megaprint.repository.intface.CrudRepo;
import com.cms.megaprint.service.intface.ServiceUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUnitServiceImpl extends DefaultServiceImpl<ServiceUnit, Long> implements ServiceUnitService {

    public ServiceUnitServiceImpl(CrudRepo<ServiceUnit, Long> repo) {
        super(repo);
    }

    @Override
    public List<ServiceUnit> findUnitsWithoutCategories() {
        return ((ServiceUnitRepository)repo).findUnitsWithoutCategories();
    }
}
