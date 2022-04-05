package com.cms.megaprint.controller.endpoint.crud;

import com.cms.megaprint.controller.common.CrudController;
import com.cms.megaprint.model.Certificate;
import com.cms.megaprint.service.common.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certificates")
public class CertificateController extends CrudController<Certificate, Long> {
    public CertificateController(CrudService<Certificate, Long> service) {
        super(service);
    }
}
