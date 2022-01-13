package com.cms.megaprint.controllers;

import com.cms.megaprint.models.CommonValue;
import com.cms.megaprint.services.CommonValueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class TestController {

    private final CommonValueService commonValueService;

    public TestController(CommonValueService commonValueService) {
        this.commonValueService = commonValueService;
    }

    @RequestMapping("/test")
    public String test(Model model) {
        Optional<CommonValue> value = commonValueService.findById(0l);
        String msg = "empty";
        if (value.isPresent()) {
            msg = value.get().getValue();
        }
        model.addAttribute("message", msg);
        return "test";
    }

    @RequestMapping("/test_index")
    public String testIndex(Model model) {
        return "testIndex";
    }

}
