package com.cms.megaprint.controller.endpoint.page;

import com.cms.megaprint.service.intface.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageAdminController {

    private final MessageService messageService;

    public MessageAdminController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping("/admin/messages")
    public String messages(Model model) {

        model.addAttribute("messages", messageService.findAll());

        return "admin/messages";
    }
}
