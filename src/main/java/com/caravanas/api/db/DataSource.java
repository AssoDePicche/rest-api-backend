package com.caravanas.api.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class DataSource {
  private static HikariDataSource dataSource;

  private DataSource() {
  }

  public static Connection getConnection() throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {
    if (DataSource.dataSource == null) {
      Properties properties = new Properties();

      String path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "/application.properties";

      try (FileInputStream stream = new FileInputStream(path)) {
        properties.load(stream);

        Class.forName(properties.getProperty("database.driver"));

        HikariConfig configuration = new HikariConfig();

        configuration.setDriverClassName(properties.getProperty("database.driver"));

        configuration.setJdbcUrl(properties.getProperty("database.url"));

        configuration.setUsername(properties.getProperty("database.username"));

        configuration.setPassword(properties.getProperty("database.password"));

        configuration.addDataSourceProperty("cachePrepStmts", properties.getProperty("hikari.cachePrepStmts"));

        configuration.addDataSourceProperty("prepStmtCacheSize" , properties.getProperty("hikari.prepStmtCacheSize"));

        configuration.addDataSourceProperty("prepStmtCacheSqlLimit" , properties.getProperty("hikari.prepStmtCacheSqlLimit"));

        dataSource = new HikariDataSource(configuration);
      }
    }

    return dataSource.getConnection();
  }
}
