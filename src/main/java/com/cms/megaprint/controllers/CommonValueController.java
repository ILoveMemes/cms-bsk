package com.cms.megaprint.controllers;

import com.cms.megaprint.common.CrudController;
import com.cms.megaprint.models.CommonValue;
import com.cms.megaprint.services.CommonValueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/val")
public class CommonValueController extends CrudController<CommonValue, Long> {

    public CommonValueController(CommonValueService service) {
        super(service);
    }

    @GetMapping("/findByKey/{key}")
    public @ResponseBody CommonValue findByKey(@PathVariable String key) {
        Optional<CommonValue> val = ((CommonValueService) service).findByKey(key);
        if (val.isPresent()) {
            return val.get();
        }
        return null;
    }

}
