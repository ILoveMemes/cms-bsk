package com.cms.megaprint.controller.endpoint.crud;

import com.cms.megaprint.exception.NotFoundException;
import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.model.SiteSection;
import com.cms.megaprint.controller.common.CrudController;
import com.cms.megaprint.service.common.CrudService;
import com.cms.megaprint.service.intface.CommonValueService;
import com.cms.megaprint.service.intface.SiteSectionService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @GetMapping("findByKey/{key}")
    public SiteSection findByKey(@PathVariable String key) {
        return ((SiteSectionService) service).findByKey(key).orElse(null);
    }

//    for debug purposes
//    @GetMapping("/getAsHashMap")
//    public HashMap<String, SiteSection> getAsHashMap() {
//        return ((SiteSectionService)service).getAsHashMap();
//    }

}
