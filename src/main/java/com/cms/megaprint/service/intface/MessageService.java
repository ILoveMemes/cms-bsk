package com.cms.megaprint.service.intface;

import com.cms.megaprint.model.Message;
import com.cms.megaprint.service.common.CrudService;

public interface MessageService extends CrudService<Message, Long> {

    Long getUnreadMessageCount();

}
