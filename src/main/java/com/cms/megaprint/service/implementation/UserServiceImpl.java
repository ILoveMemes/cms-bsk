package com.cms.megaprint.service.implementation;

import com.cms.megaprint.model.User;
import com.cms.megaprint.repository.intface.CrudRepo;
import com.cms.megaprint.repository.intface.UserRepository;
import com.cms.megaprint.service.implementation.dflt.DefaultServiceImpl;
import com.cms.megaprint.service.intface.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends DefaultServiceImpl<User, Long> implements UserService {
    public UserServiceImpl(CrudRepo<User, Long> repo) {
        super(repo);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return ((UserRepository)repo).findByUsername(username);
    }
}
