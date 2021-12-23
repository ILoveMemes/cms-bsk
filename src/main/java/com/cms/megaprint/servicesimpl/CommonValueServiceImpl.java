package com.cms.megaprint.servicesimpl;

import com.cms.megaprint.models.CommonValue;
import com.cms.megaprint.repos.CommonValueRepository;
import com.cms.megaprint.services.CommonValueService;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CommonValueServiceImpl implements CommonValueService {

    private final CommonValueRepository commonValueRepository;

    public CommonValueServiceImpl(CommonValueRepository commonValueRepository) {
        this.commonValueRepository = commonValueRepository;
    }

    @Override
    public Optional<CommonValue> findByName(String name) {
        for(CommonValue commonValue: commonValueRepository.findAll()) {
            if (commonValue.getName().equals(name)) {
                return Optional.of(commonValue);
            }
        }
        return Optional.empty();
    }

    @Override
    public Set<CommonValue> findAll() {
        return Sets.newHashSet(commonValueRepository.findAll());
    }

    @Override
    public Optional<CommonValue> findById(Long aLong) {
        return commonValueRepository.findById(aLong);
    }

    @Override
    public CommonValue save(CommonValue object) {
        return commonValueRepository.save(object);
    }

    @Override
    public void delete(CommonValue object) {
        commonValueRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        commonValueRepository.deleteById(aLong);
    }
}
