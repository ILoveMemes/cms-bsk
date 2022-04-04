package com.cms.megaprint.repository.intface;

import com.cms.megaprint.model.Message;

public interface MessageRepository extends CrudRepo<Message, Long> {

    Long getUnreadMessageCount();

}
