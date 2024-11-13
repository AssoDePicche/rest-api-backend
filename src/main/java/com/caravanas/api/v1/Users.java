package com.caravanas.api.v1;

import com.caravanas.db.DataSource;
import com.caravanas.db.Query;

import com.caravanas.domain.User;

import com.caravanas.util.Json;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/v1/users")
public final class Users extends HttpServlet {
  private static final long serialVersionUID = 0L;

  private Connection connection;

  private Query query;

  public Users() throws Exception {
    super();

    this.connection = DataSource.getConnection();

    this.query = new Query(User.class);
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType(Json.CONTENT_TYPE);

    response.setCharacterEncoding(StandardCharsets.UTF_8.name());

    Json json = new Json();

    try {
      final String SQL = this.query.select().toString();

      ResultSet resultSet = this.connection.prepareStatement(SQL).executeQuery();
      
      json.put("success", "true");

      while (resultSet.next()) {
        json.put("payload", resultSet.getString("name"));
      }
    } catch (Exception exception) {
      json.put("success", "false");

      json.put("error", exception.getMessage());
    } finally {
      response.getWriter().print(json);

      response.getWriter().flush();
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType(Json.CONTENT_TYPE);

    response.setCharacterEncoding(StandardCharsets.UTF_8.name());

    Json json = new Json();

    try {
      String name = request.getHeader("name");

      if (name == null || name.isEmpty()) {
        throw new Exception("You must send a name in the request.");
      }

      final String SQL = this.query.insert().toString();

      PreparedStatement statement = this.connection.prepareStatement(SQL);

      statement.setString(1, name);

      statement.executeUpdate();

      json.put("success", "true");

      json.put("payload", name);
    } catch (Exception exception) {
      json.put("error", exception.getMessage());
    } finally {
      response.getWriter().print(json);

      response.getWriter().flush();
    }
  }

  @Override
  public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType(Json.CONTENT_TYPE);

    response.setCharacterEncoding(StandardCharsets.UTF_8.name());

    Json json = new Json();

    try {
      String name = request.getHeader("name");

      if (name == null || name.isEmpty()) {
        throw new Exception("You must send a name in the request.");
      }

      String id = request.getHeader("id");

      if (id == null || id.isEmpty()) {
        throw new Exception("You must send an id in the request.");
      }

      final String SQL = this.query.update().toString();

      PreparedStatement statement = this.connection.prepareStatement(SQL);

      statement.setString(1, name);

      statement.setString(2, id);

      statement.executeUpdate();

      json.put("success", "true");

      json.put("payload", name);
    } catch (Exception exception) {
      json.put("error", exception.getMessage());
    } finally {
      response.getWriter().print(json);

      response.getWriter().flush();
    }
  }
}
