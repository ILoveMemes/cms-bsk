package com.cms.megaprint.controller.endpoint.crud;

import com.cms.megaprint.controller.common.CrudController;
import com.cms.megaprint.model.User;
import com.cms.megaprint.service.common.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends CrudController<User, Long> {

    public UserController(CrudService<User, Long> service) {
        super(service);
    }

}
