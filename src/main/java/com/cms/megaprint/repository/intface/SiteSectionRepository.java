package com.cms.megaprint.repository.intface;

import com.cms.megaprint.model.SiteSection;

import java.util.HashMap;
import java.util.Optional;

public interface SiteSectionRepository extends CrudRepo<SiteSection, Long> {

    Optional<SiteSection> findByKey(String key);
    HashMap<String, SiteSection> getAsHashMap();

}
