package com.cms.megaprint.service.intface;

import com.cms.megaprint.model.SiteSection;
import com.cms.megaprint.service.common.CrudService;

import java.util.HashMap;
import java.util.Optional;

public interface SiteSectionService extends CrudService<SiteSection, Long> {

    Optional<SiteSection> findByKey(String key);
    HashMap<String, SiteSection> getAsHashMap();

}
