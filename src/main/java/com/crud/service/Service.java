package com.crud.service;

import java.util.*;
import java.sql.*;

import com.crud.data.*;
import com.crud.dbase.*;
import com.crud.oprt.*;

/**
 * Class berisi layanan untuk operasi CRUD dari aplikasi.
 */
public class Service {
  private Scanner scanner = new Scanner(System.in);
  private int tempNIS;
  private String tempName;
  private char tempGender;
  
  public Service() {}
  
  /**
   * Untuk pembuatan data baru. Input data nis, nama, gender. Input data masukan ke objek
   * Students, buat data baru dengan 'sql statement' dan data input di masukam.
   */
  public void serviceCreate() throws Exception {
    System.out.println("Enter data nis(integer value):");
    tempNIS = scanner.nextInt();
    scanner.nextLine();// Skiping from number input to text input
    System.out.println("Enter data name:");
    tempName = scanner.nextLine();
    System.out.println("Enter data gender:");
    tempGender =  scanner.next().charAt(0);
    checkInputGender(tempGender);
    
    Students students = new Students(tempNIS, tempName, tempGender);
    System.out.println("[ Input data ]");
    System.out.println("NIS     : "+students.getNIS());
    System.out.println("Name    : "+students.getName());
    System.out.println("Gender  : "+students.getGender());
    
    if(Operation.isYesOrNo("Save")) {
      String query = String.format(
        "INSERT INTO students(nis, name, gender) VALUES(%d,'%s','%s')",
        students.getNIS(), students.getName(), students.getGender()
      );
      DBase.isQueryUpdate(query);
    }
  }
  
  /**
   * Validasi untuk input data 'gender', data harus huruf besar M/W.
   */
  private void checkInputGender(char val) throws Exception {
    while(tempGender != 'M' && tempGender != 'W') {
      if(tempGender == 'm' || tempGender == 'w') {
        System.err.println("[INFO] Input gender value "+tempGender+" is lower case");
      }
      System.err.println("[INFO] Input gender value "+tempGender+" is not valid");
      System.out.println("Enter data gender:");
      tempGender = scanner.next().charAt(0);
    }
  }
  
  /**
   * Fungsi untuk operasi READ.
   */
  public void serviceRead() throws Exception {
    try (Statement st = DBase.getConnection().createStatement()) {
      ResultSet rs = st.executeQuery("SELECT * FROM students");
      int no = 0;
      System.out.println("| No | Id | nis | gender |        name        | ");
      while(rs.next()) {
        no++;
        System.out.println(
          "| "+
          no+" | "+
          rs.getInt("Id")+" | "+
          rs.getInt("nis")+" | "+
          rs.getString("gender")+" | "+
          rs.getString("name")
        );
      }
    } catch(SQLException sqle) {
      sqle.printStackTrace();
    }
  }
  
  /**
   * Fungsi untuk operasi UPDATE.
   */
  public void serviceUpdate() throws Exception {
    
  }
  
  /**
   * Fungsi untuk operasi DELETE.
   */
  public void serviceDelete() throws Exception {
    
  }
  
}