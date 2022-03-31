package com.cms.megaprint.service.implementation;

import com.cms.megaprint.service.implementation.dflt.DefaultServiceImpl;
import com.cms.megaprint.model.TeammatesSocialNetwork;
import com.cms.megaprint.repository.intface.CrudRepo;
import com.cms.megaprint.service.intface.TeammateSocialNetworkService;
import org.springframework.stereotype.Service;

@Service
public class TeammateSocialNetworkServiceImpl extends DefaultServiceImpl<TeammatesSocialNetwork, Long> implements TeammateSocialNetworkService {

    public TeammateSocialNetworkServiceImpl(CrudRepo<TeammatesSocialNetwork, Long> repo) {
        super(repo);
    }

}
