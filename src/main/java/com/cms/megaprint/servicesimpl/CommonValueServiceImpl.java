package com.cms.megaprint.servicesimpl;

import com.cms.megaprint.common.DefaultServiceImpl;
import com.cms.megaprint.models.CommonValue;
import com.cms.megaprint.repos.CommonValueRepository;
import com.cms.megaprint.services.CommonValueService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommonValueServiceImpl extends DefaultServiceImpl<CommonValue, Long> implements CommonValueService {

    public CommonValueServiceImpl(CommonValueRepository repository) {
        super(repository);
    }

    @Override
    public Optional<CommonValue> findByName(String name) {
        for(CommonValue commonValue: repo.findAll()) {
            if (commonValue.getName().equals(name)) {
                return Optional.of(commonValue);
            }
        }
        return Optional.empty();
    }

}
