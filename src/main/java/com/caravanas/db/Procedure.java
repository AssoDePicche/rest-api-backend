package com.caravanas.db;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class Procedure {
  private CallableStatement statement;

  public Procedure(String call) throws Exception {
    statement = DataSource.getConnection().prepareCall(call);
  }

  public ResultSet execute() throws SQLException {
    return statement.executeQuery();
  }

  public void update() throws SQLException {
    statement.executeUpdate();
  }
}
