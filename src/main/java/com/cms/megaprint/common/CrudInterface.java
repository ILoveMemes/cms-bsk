package com.cms.megaprint.common;

import java.util.List;
import java.util.Optional;

public interface CrudInterface<T, ID> {

    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T object);
    T update(T object);
    void delete(T object);
    void deleteById(ID id);

}
