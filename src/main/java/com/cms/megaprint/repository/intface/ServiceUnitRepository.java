package com.cms.megaprint.repository.intface;

import com.cms.megaprint.model.ServiceUnit;

import java.util.List;

public interface ServiceUnitRepository extends CrudRepo<ServiceUnit, Long>{

    List<ServiceUnit> findUnitsWithoutCategories();

}
