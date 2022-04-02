package com.cms.megaprint.controller.endpoint.crud;

import com.cms.megaprint.controller.common.CrudController;
import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.service.intface.CommonValueService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/val")
public class CommonValueController extends CrudController<CommonValue, Long> {

    public CommonValueController(CommonValueService service) {
        super(service);
    }

    @GetMapping("/findByKey/{key}")
    public @ResponseBody CommonValue findByKey(@PathVariable String key) {
        Optional<CommonValue> val = ((CommonValueService) service).findByKey(key);
        return val.orElse(null);
    }

}
