package com.cms.megaprint.common;

import com.cms.megaprint.repos.CrudRepo;
import com.cms.megaprint.services.CrudService;
import com.google.common.collect.Sets;

import java.util.Optional;
import java.util.Set;

public class DefaultServiceImpl<T, ID> implements CrudService<T, ID> {

    protected final CrudRepo<T, ID> repo;

    public DefaultServiceImpl(CrudRepo<T, ID> repo) {
        this.repo = repo;
    }

    @Override
    public Set<T> findAll() {
        Set<T> set = repo.findAll();
        if (set != null) {
            return Sets.newHashSet(repo.findAll());
        }
        return null;
    }

    @Override
    public Optional<T> findById(ID id) {
        return repo.findById(id);
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
    public void delete(T object) {
        repo.delete(object);
    }

    @Override
    public void deleteById(ID id) {
        repo.deleteById(id);
    }
}
