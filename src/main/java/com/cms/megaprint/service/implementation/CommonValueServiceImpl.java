package com.cms.megaprint.service.implementation;

import com.cms.megaprint.service.implementation.dflt.DefaultServiceImpl;
import com.cms.megaprint.model.CommonValue;
import com.cms.megaprint.repository.intface.CommonValueRepository;
import com.cms.megaprint.service.intface.CommonValueService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommonValueServiceImpl extends DefaultServiceImpl<CommonValue, Long> implements CommonValueService {

    public CommonValueServiceImpl(CommonValueRepository repository) {
        super(repository);
    }

    @Override
    public Optional<CommonValue> findByKey(String name) {
        for(CommonValue commonValue: repo.findAll()) {
            if (commonValue.getKey().equals(name)) {
                return Optional.of(commonValue);
            }
        }
        return Optional.empty();
    }

}
