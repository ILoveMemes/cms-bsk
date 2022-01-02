package com.cms.megaprint.reposimpl;

import com.cms.megaprint.common.CrudRepoHibernateImpl;
import com.cms.megaprint.models.CommonValue;
import com.cms.megaprint.repos.CommonValueRepository;
import org.springframework.stereotype.Service;

@Service
public class CommonValueRepositoryImpl extends CrudRepoHibernateImpl<CommonValue, Long> implements CommonValueRepository {

}
