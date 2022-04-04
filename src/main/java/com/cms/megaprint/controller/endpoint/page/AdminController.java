package com.cms.megaprint.controller.endpoint.page;

import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.service.intface.CommonValueService;
import com.cms.megaprint.service.intface.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    private final CommonValueService commonValueService;
    private final MessageService messageService;

    public AdminController(CommonValueService commonValueService, MessageService messageService) {
        this.commonValueService = commonValueService;
        this.messageService = messageService;
    }

    @RequestMapping("/admin")
    public String admin(Model model) {

        for (CommonValue cValue: commonValueService.findAll()) {
            model.addAttribute(cValue.getKey(), cValue.getValue());
        }
        model.addAttribute("unreadMessageCount", messageService.getUnreadMessageCount());

        return "admin/index";
    }

}
