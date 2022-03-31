package com.cms.megaprint.service.intface;


import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.service.common.CrudService;

import java.util.Optional;

public interface CommonValueService extends CrudService<CommonValue, Long> {

    Optional<CommonValue> findByKey(String name);

}
