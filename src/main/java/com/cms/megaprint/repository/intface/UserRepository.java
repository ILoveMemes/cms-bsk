package com.cms.megaprint.repository.intface;

import com.cms.megaprint.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepo<User, Long> {

    Optional<User> findByUsername(String username);

}
