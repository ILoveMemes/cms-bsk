package com.cms.megaprint.controller.endpoint.page;

import com.cms.megaprint.exception.NotFoundException;
import com.cms.megaprint.model.Message;
import com.cms.megaprint.service.intface.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SingleMessageAdminController {

    private final MessageService messageService;

    public SingleMessageAdminController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping("/admin_single_message")
    public String message(Model model, @RequestParam Long id) {
        Message requiredMessage = messageService.findById(id).orElseThrow(NotFoundException::new);
        if (requiredMessage.isUnread()) {
            requiredMessage.setUnread(false); // now this message is read
            requiredMessage = messageService.update(requiredMessage);
        }
        model.addAttribute("message", requiredMessage);
        return "single_message_admin";
    }

    @GetMapping("/asm/markAsRead/{id}")
    public @ResponseBody boolean messageMarkAsRead(@PathVariable("id") Long id) {
        Message msg = messageService.findById(id).orElseThrow(() -> new NotFoundException());
        if (msg.isUnread()) {
            msg.setUnread(false);
            messageService.update(msg);
        }
        return true;
    }

    @GetMapping("/asm/markAsUnread/{id}")
    public @ResponseBody boolean messageMarkAsUnread(@PathVariable("id") Long id) {
        Message msg = messageService.findById(id).orElseThrow(NotFoundException::new);
        if (!msg.isUnread()) {
            msg.setUnread(true);
            messageService.update(msg);
        }
        return true;
    }

    @GetMapping("/asm/markAsSpecial/{id}")
    public @ResponseBody boolean messageMarkAsSpecial(@PathVariable("id") Long id) {
        Message msg = messageService.findById(id).orElseThrow(NotFoundException::new);
        if (!msg.isMarked()) {
            msg.setMarked(true);
            messageService.update(msg);
        }
        return true;
    }

    @GetMapping("/asm/markAsRegular/{id}")
    public @ResponseBody boolean messageMarkAsRegular(@PathVariable("id") Long id) {
        Message msg = messageService.findById(id).orElseThrow(NotFoundException::new);
        if (msg.isMarked()) {
            msg.setMarked(false);
            messageService.update(msg);
        }
        return true;
    }

    @GetMapping("/asm/markAsArchive/{id}")
    public @ResponseBody boolean messageMarkAsArchive(@PathVariable("id") Long id) {
        Message msg = messageService.findById(id).orElseThrow(NotFoundException::new);
        if (!msg.isArchived()) {
            msg.setArchived(true);
            messageService.update(msg);
        }
        return true;
    }

    @GetMapping("/asm/markAsNotArchive/{id}")
    public @ResponseBody boolean messageMarkAsNotArchive(@PathVariable("id") Long id) {
        Message msg = messageService.findById(id).orElseThrow(NotFoundException::new);
        if (msg.isArchived()) {
            msg.setArchived(false);
            messageService.update(msg);
        }
        return true;
    }

}
