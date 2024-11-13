package com.caravanas.domain;

import com.caravanas.db.Column;
import com.caravanas.db.Id;
import com.caravanas.db.Table;

@Table(name="Users")
public final class User {
  @Id
  private Long id;

  @Column
  private String name;

  public User() {
  }

  public Long getID() {
    return this.id;
  }

  public void setID(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
