package com.cms.megaprint.controllers;

import com.cms.megaprint.models.CommonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {

    @PostMapping("test")
    @ResponseBody
    public String test(@RequestBody CommonValue value) {
        return "fuck you";
    }

    @GetMapping("test2")
    @ResponseBody
    public String test2() {
        return "hello, bitch!";
    }

}
