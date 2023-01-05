package com.crud.dbase;

import java.util.*;
import java.sql.*;

/**
 * Objek berisi berbagai hal tentang database dan koneksi ke database.
 * + Catatan
 *  - Jika "return" bertipe objek "ResultSet" maka objek "Statement" harus hidup, jika
 *    mati maka objek "ResultSet" tidak bisa digunakan dan eror "Operation not allowed after resultset closed".
 */
public class DBase {
  private static Connection connection;
  private static String url "jdbc:mysql://localhost:3306/project";
  private static String username = "root";
  private static String pass = "root";
  
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
   * Hubungkan ke database dengan parameter url, username, password.
   */
  public void on(String url, String username, String pass) throws SQLException {
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