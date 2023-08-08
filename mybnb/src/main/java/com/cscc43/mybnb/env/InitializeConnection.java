package com.cscc43.mybnb.env;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// import io.github.cdimascio.dotenv.Dotenv;

public class InitializeConnection {
  public static String CONNECTION_STRING = "CONNECTION_STRING";
  public static String USERNAME = "USERNAME";
  public static String PASSWORD = "PASSWORD";

  public static Connection getConnection() throws SQLException {
    // Dotenv dotenv = Dotenv.load();
    // String connectionString = dotenv.get(CONNECTION_STRING);
    // String username = dotenv.get(USERNAME);
    // String password = dotenv.get(PASSWORD);
    String connectionString = "jdbc:mysql://localhost:3306/mybnb";
    String username = "root";
    String password = "3ekmn2i9wm";

    if (connectionString == null || connectionString.isEmpty()) {
      throw new SQLException("CONNECTION_STRING is not set in .env file");
    }
    if (username == null || username.isEmpty()) {
      throw new SQLException("USERNAME is not set in .env file");
    }
    if (password == null || password.isEmpty()) {
      throw new SQLException("PASSWORD is not set in .env file");
    }
    try {
      return DriverManager.getConnection(connectionString, username, password);
    } catch (SQLException e) {
      System.out.println("Could not connect to database.");
      return null;
    }
  }

  public static void printSampleDBQuery() throws SQLException {
    Connection conn = getConnection();
    PreparedStatement execStat = conn.prepareStatement("SELECT * FROM User");
    ResultSet rs = execStat.executeQuery();

    // extract data from result set
    while (rs.next()) {
      String name = rs.getString("name");
      String sin = rs.getString("sin");
      int eid = rs.getInt("id");
      System.out.println(name);
      System.out.println(eid);
      System.out.println(sin);
    }
  }
}
