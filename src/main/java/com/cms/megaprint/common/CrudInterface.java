package com.cms.megaprint.common;

import java.util.Optional;
import java.util.Set;

public interface CrudInterface<T, ID> {

    Set<T> findAll();
    Optional<T> findById(ID id);
    T save(T object);
    T update(T object);
    void delete(T object);
    void deleteById(ID id);

}
