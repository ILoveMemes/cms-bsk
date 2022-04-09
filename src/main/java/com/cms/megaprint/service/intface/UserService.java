package com.cms.megaprint.service.intface;

import com.cms.megaprint.model.User;
import com.cms.megaprint.service.common.CrudService;

import java.util.Optional;

public interface UserService extends CrudService<User, Long> {

    Optional<User> findByUsername(String username);

}
