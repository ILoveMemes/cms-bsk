package com.cms.megaprint.service.implementation;

import com.cms.megaprint.model.Message;
import com.cms.megaprint.repository.intface.CrudRepo;
import com.cms.megaprint.service.implementation.dflt.DefaultServiceImpl;
import com.cms.megaprint.service.intface.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends DefaultServiceImpl<Message, Long> implements MessageService {
    public MessageServiceImpl(CrudRepo<Message, Long> repo) {
        super(repo);
    }
}
