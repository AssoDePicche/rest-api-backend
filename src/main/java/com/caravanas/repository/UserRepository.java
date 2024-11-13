package com.caravanas.repository;

import com.caravanas.db.Query;
import com.caravanas.db.Repository;

import com.caravanas.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public final class UserRepository implements Repository<User, Long> {
  private Query query;

  private Connection connection;

  public UserRepository(Query query, Connection connection) {
    this.query = query;

    this.connection = connection;
  }

  @Override
  public Collection<User> list() throws Exception {
    final String SQL = this.query.select().toString();

    PreparedStatement statement = this.connection.prepareStatement(SQL);

    ResultSet resultSet = statement.executeQuery();

    Collection<User> users = new ArrayList<>();

    while (resultSet.next()) {
      User user = new User();

      user.setID(resultSet.getLong("id"));

      user.setName(resultSet.getString("name"));

      users.add(user);
    }

    return users;
  }

  @Override
  public Optional<User> find(Long id) throws Exception {
    final String SQL = this.query.select().where().toString();

    PreparedStatement statement = this.connection.prepareStatement(SQL);

    statement.setLong(1, id);

    ResultSet resultSet = statement.executeQuery();

    if (resultSet.next()) {
      User user = new User();

      user.setID(resultSet.getLong("id"));

      user.setName(resultSet.getString("name"));

      return Optional.of(user);
    }

    return Optional.ofNullable(null);
  }

  @Override
  public void delete(Long id) throws Exception {
    final String SQL = this.query.delete().toString();

    PreparedStatement statement = this.connection.prepareStatement(SQL);

    statement.setLong(1, id);

    statement.executeUpdate();
  }

  @Override
  public void save(User user) throws Exception {
    // TODO: Check if entry exists in database and choose between INSERT and UPDATE
    final String SQL = this.query.insert().toString();

    PreparedStatement statement = this.connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

    statement.setString(1, user.getName());

    statement.executeUpdate();

    ResultSet resultSet = statement.getGeneratedKeys();

    if (resultSet.next()) {
      Long id = resultSet.getLong(1);

      user.setID(id);
    }
  }

  public void save(Collection<User> users) throws Exception {
    for (User user : users) {
      this.save(user);
    }
  }
}
