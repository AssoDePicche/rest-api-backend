package com.caravanas.api;

import com.caravanas.api.db.Query;
import com.caravanas.api.util.Json;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public final class App extends HttpServlet {
  private static final long serialVersionUID = 0L;

  public App() {
    super();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType(Json.CONTENT_TYPE);

    response.setCharacterEncoding(StandardCharsets.UTF_8.name());

    Json json = new Json();

    try {
      ResultSet resultSet = new Query("SELECT * FROM Users").execute();
      
      json.put("success", "true");

      while (resultSet.next()) {
        json.put("payload", resultSet.getString("name"));
      }
    } catch (Exception exception) {
      json.put("success", "false");

      json.put("payload", "");

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
      if (request.getParameter("name") == null) {
        throw new Exception("You must send a name in the request.");
      }

      Query query = new Query("INSERT Users (name) VALUES (?)");

      query.setString(1, request.getParameter("name"));

      query.update();
    } catch (Exception exception) {
      json.put("error", exception.getMessage());
    } finally {
      response.getWriter().print(json);

      response.getWriter().flush();
    }
  }
}
