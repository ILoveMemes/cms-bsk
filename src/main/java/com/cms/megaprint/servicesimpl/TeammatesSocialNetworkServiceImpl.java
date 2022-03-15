package com.cms.megaprint.servicesimpl;

import com.cms.megaprint.common.DefaultServiceImpl;
import com.cms.megaprint.models.TeammatesSocialNetwork;
import com.cms.megaprint.repos.CrudRepo;
import com.cms.megaprint.services.TeammateSocialNetworkService;
import org.springframework.stereotype.Service;

@Service
public class TeammatesSocialNetworkServiceImpl extends DefaultServiceImpl<TeammatesSocialNetwork, Long> implements TeammateSocialNetworkService {

    public TeammatesSocialNetworkServiceImpl(CrudRepo<TeammatesSocialNetwork, Long> repo) {
        super(repo);
    }

}
