package com.cms.megaprint.controllers;

import com.cms.megaprint.models.CommonValue;
import com.cms.megaprint.services.CommonValueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/val")
public class CommonValueController {

    private final CommonValueService commonValueService;

    public CommonValueController(CommonValueService commonValueService) {
        this.commonValueService = commonValueService;
    }

    @GetMapping("/get")
    public @ResponseBody CommonValue getById(
            @RequestParam(name = "id", defaultValue = "-1", required = false) Long id,
            @RequestParam(name = "key", defaultValue = "", required = false) String key) {
        Optional<CommonValue> val = Optional.empty();
        if (id >= 0) {
            val = commonValueService.findById(id);
        } else {
            if (!key.equals("")) {
                val = commonValueService.findByKey(key);
            }
        }
        if (val.isPresent()) {
            return val.get();
        }
        return null;
    }
}
