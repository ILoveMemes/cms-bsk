package com.cms.megaprint.services;


import com.cms.megaprint.models.CommonValue;

import java.util.Optional;

public interface CommonValueService extends CrudService<CommonValue, Long> {

    Optional<CommonValue> findByKey(String name);

}
