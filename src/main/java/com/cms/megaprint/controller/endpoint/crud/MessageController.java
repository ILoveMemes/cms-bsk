package com.cms.megaprint.controller.endpoint.crud;

import com.cms.megaprint.controller.common.CrudController;
import com.cms.megaprint.model.Message;
import com.cms.megaprint.service.common.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController extends CrudController<Message, Long> {
    public MessageController(CrudService<Message, Long> service) {
        super(service);
    }
}
