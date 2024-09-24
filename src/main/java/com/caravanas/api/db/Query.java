package com.caravanas.api.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class Query {
  private PreparedStatement statement;

  public Query(String query) throws Exception {
    statement = DataSource.getConnection().prepareStatement(query);
  }

  public ResultSet execute() throws SQLException {
    return statement.executeQuery();
  }

  public void update() throws SQLException {
    statement.executeUpdate();
  }

  public void setInt(int index, Integer integer) throws SQLException {
    statement.setInt(index, integer);
  }

  public void setString(int index, String string) throws SQLException {
    statement.setString(index, string);
  }
}
