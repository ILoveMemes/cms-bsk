package com.cms.megaprint.repository.intface;

import com.cms.megaprint.model.ServiceCategory;

import java.util.List;

public interface ServiceCategoryRepository extends CrudRepo<ServiceCategory, Long> {

    List<ServiceCategory> findAllThatShowOnMain();

}
