package com.cms.megaprint.service.implementation;

import com.cms.megaprint.model.SiteSection;
import com.cms.megaprint.repository.intface.CrudRepo;
import com.cms.megaprint.repository.intface.SiteSectionRepository;
import com.cms.megaprint.service.implementation.dflt.DefaultServiceImpl;
import com.cms.megaprint.service.intface.SiteSectionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class SiteSectionServiceImpl extends DefaultServiceImpl<SiteSection, Long> implements SiteSectionService {
    public SiteSectionServiceImpl(CrudRepo<SiteSection, Long> repo) {
        super(repo);
    }

    @Override
    public Optional<SiteSection> findByKey(String key) {
        return ((SiteSectionRepository)repo).findByKey(key);
    }

    @Override
    public HashMap<String, SiteSection> getAsHashMap() {
        return ((SiteSectionRepository)repo).getAsHashMap();
    }
}
