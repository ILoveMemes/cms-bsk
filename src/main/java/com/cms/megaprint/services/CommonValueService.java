package com.cms.megaprint.services;


import com.cms.megaprint.models.CommonValue;

import java.util.Optional;

public interface CommonValueService extends CrudService<Long, CommonValue> {

    Optional<CommonValue> findByName(String name);

}
