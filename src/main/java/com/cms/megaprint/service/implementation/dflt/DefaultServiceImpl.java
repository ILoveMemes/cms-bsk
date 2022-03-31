package com.cms.megaprint.service.implementation.dflt;

import com.cms.megaprint.repository.intface.CrudRepo;
import com.cms.megaprint.service.common.CrudService;

import java.util.List;
import java.util.Optional;

public class DefaultServiceImpl<T, ID> implements CrudService<T, ID> {

    protected final CrudRepo<T, ID> repo;

    public DefaultServiceImpl(CrudRepo<T, ID> repo) {
        this.repo = repo;
    }

    @Override
    public List<T> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        // TODO: add mapper here (map Data Base Entity to Data Transfer Object)
        // MapStruct
        // instead of Optional use runtime exception
        return repo.findById(id).map(entity -> entity);
    }

    @Override
    public T save(T object) {
        return repo.save(object);
    }

    @Override
    public T update(T object) {
        return repo.update(object);
    }

    @Override
    public boolean delete(T object) {
        return repo.delete(object);
    }

    @Override
    public boolean deleteById(ID id) {
        return repo.deleteById(id);
    }
}
