package com.caravanas.db;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T, ID> {
  Collection<T> list() throws Exception;

  Optional<T> find(ID id) throws Exception;

  void delete(ID id) throws Exception;

  void save(T object) throws Exception;

  void save(Collection<T> objects) throws Exception;
}
