package com.cms.megaprint.controller.endpoint.addon;

import com.cms.megaprint.model.Message;
import com.cms.megaprint.service.intface.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.ZonedDateTime;

@Controller
@RequestMapping("/send_message")
public class MessageSendController {

    private final MessageService messageService;

    public MessageSendController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public @ResponseBody boolean send(@RequestBody Message msg) {
        try {
            // set default values
            msg.setUnread(true);
            msg.setMarked(false);
            msg.setArchived(false);
            msg.setSendingTime(ZonedDateTime.now());
            // save to database
            messageService.save(msg);
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

}
