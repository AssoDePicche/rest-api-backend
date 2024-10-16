package com.caravanas.db;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T, ID> {
  Collection<T> list() throws Exception;

  Optional<T> find(ID id) throws Exception;

  void delete(ID id) throws Exception;

  T save(T object) throws Exception;

  Collection<T> save(Collection<T> objects) throws Exception;
}
