package com.crud.dbase;

import java.util.*;
import java.sql.*;

/**
 * Objek berisi berbagai hal tentang database mulai koneksi,query, dll.
 */
public class DBase {
  private static Connection connection;
  private String url;
  private String username;
  private String pass;
  
  public DBase(String url, String pass, String username) {
    this.url = url;
    this.pass = pass;
    this.username = username;
  }
  
  /**
   * Untuk query data ke database, query bertipe DML.
   */
  public static int isQueryUpdate(String statementQuery) throws SQLException, Exception {
    int result = -1;
    try (Statement statement = connection.createStatement()) {
      result = statement.executeUpdate(statementQuery);
    } catch(SQLException sqle) {
      sqle.printStackTrace();
    } finally {
      return result;
    }
  }
  
  /**
   * Ambil objek 'connection'.
   */
  public static Connection getConnection() throws SQLException {
    if(connection == null) throw new RuntimeException("Connection is null");
    return connection;
  }
  
  /**
   * Register driver mysql.
   */
  private void register() throws SQLException {
    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
  }
  
  /**
   * Hubungkan ke database.
   */
  public void on() throws SQLException {
    if(connection == null) {
      register();
      connection = DriverManager.getConnection(url, username, pass);
    }
  }
  
  /**
   * Matikan koneksi ke database.
   */
  public void off() throws SQLException {
    try {
      if(connection == null) throw new RuntimeException("Connection is null");
      connection.close();
    } catch(SQLException sqle) {
      sqle.printStackTrace();
    } finally {
      connection.close();
    }
  }
  
}