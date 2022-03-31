package com.cms.megaprint.service.intface;

import com.cms.megaprint.model.ServiceUnit;
import com.cms.megaprint.service.common.CrudService;

import java.util.List;

public interface ServiceUnitService extends CrudService<ServiceUnit, Long> {

    List<ServiceUnit> findUnitsWithoutCategories();

}
