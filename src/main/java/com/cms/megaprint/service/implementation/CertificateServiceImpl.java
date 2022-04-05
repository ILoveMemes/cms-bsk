package com.cms.megaprint.service.implementation;

import com.cms.megaprint.model.Certificate;
import com.cms.megaprint.repository.intface.CrudRepo;
import com.cms.megaprint.service.implementation.dflt.DefaultServiceImpl;
import com.cms.megaprint.service.intface.CertificateService;
import org.springframework.stereotype.Service;

@Service
public class CertificateServiceImpl extends DefaultServiceImpl<Certificate, Long> implements CertificateService {
    public CertificateServiceImpl(CrudRepo<Certificate, Long> repo) {
        super(repo);
    }
}
