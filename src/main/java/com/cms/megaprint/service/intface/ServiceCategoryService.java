package com.cms.megaprint.service.intface;

import com.cms.megaprint.model.ServiceCategory;
import com.cms.megaprint.service.common.CrudService;

import java.util.List;

public interface ServiceCategoryService extends CrudService<ServiceCategory, Long> {

    List<ServiceCategory> findAllThatShowOnMain();

}
