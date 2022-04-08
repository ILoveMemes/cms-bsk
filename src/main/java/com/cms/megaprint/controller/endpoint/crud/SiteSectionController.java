package com.cms.megaprint.controller.endpoint.crud;

import com.cms.megaprint.model.SiteSection;
import com.cms.megaprint.controller.common.CrudController;
import com.cms.megaprint.service.common.CrudService;
import com.cms.megaprint.service.intface.SiteSectionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/site_sections")
public class SiteSectionController extends CrudController<SiteSection, Long> {

    public SiteSectionController(CrudService<SiteSection, Long> service) {
        super(service);
    }

    @PostMapping("/updateByKey")
    public SiteSection updateByKey(@RequestBody SiteSection object) {
        Optional<SiteSection> entity = ((SiteSectionService)service).findByKey(object.getKey());
        if (entity.isPresent()) {
            entity.get().setCaption(object.getCaption());
            entity.get().setVisible(object.isVisible());
            service.update(entity.get());
        }
        return object;
    }

}
