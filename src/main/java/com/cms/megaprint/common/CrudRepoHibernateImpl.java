package com.cms.megaprint.common;

import com.cms.megaprint.db.HibernateSessionFactoryUtil;
import com.cms.megaprint.repos.CrudRepo;

import java.util.Optional;
import java.util.Set;

public class CrudRepoHibernateImpl<T, ID> implements CrudRepo<T, ID> {

    private final HibernateSessionFactoryUtil hibernateSessionFactoryUtil = null;

    @Override
    public Optional<T> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public Set<T> findAll() {
        return null;
    }

    @Override
    public T save(T entity) {
        return null;
    }

    @Override
    public T update(T object) {
        return null;
    }

    @Override
    public void deleteById(ID id) {

    }

    @Override
    public void delete(T entity) {

    }
}
