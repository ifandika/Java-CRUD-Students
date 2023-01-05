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
    viewAllStudentData(students);
    if(Operation.isYesOrNo("Save")) {
      String query = String.format(
        "INSERT INTO students(nis, name, gender) VALUES(%d,'%s','%s')",
        students.getNIS(), students.getName(), students.getGender()
      );
      DBase.isQueryUpdate(query);
    }
  }
  
  private void viewAllStudentData(Students students) throws Exception {
    System.out.println("NIS     : "+students.getNIS());
    System.out.println("Name    : "+students.getName());
    System.out.println("Gender  : "+students.getGender());
  }
  
  private void viewAllStudentData(int nis, String name, String gender) throws Exception {
    System.out.println("NIS     : "+nis);
    System.out.println("Name    : "+name);
    System.out.println("Gender  : "+gender);
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
   * Fungs untuk operasi READ.p8an8a
   */
  public void serviceRead() throws Exception {
    try (Statement st = DBase.getConnection().createStatement()) {
      ResultSet rs = st.executeQuery("SELECT * FROM students");
      int no = 0;
      System.out.println("[ All data ]");
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
    System.out.println("[ All Data ]");
    serviceRead();
    System.out.println("Enter no data(integer value):");
    int dataNo = scanner.nextInt();
    try (Statement st = DBase.getConnection().createStatement(
          ResultSet.TYPE_SCROLL_INSENSITIVE,
          ResultSet.CONCUR_UPDATABLE
        )) {
      ResultSet rs = st.executeQuery("SELECT * FROM students");
      if(rs.absolute(dataNo)) {
        Students students = isInputNewData();
        System.out.println("[ Data to be updated ]");
        viewAllStudentData(students);
        if(Operation.isYesOrNo("Update data")) {
          if(students.getNIS() != 0) {
            System.out.println("Updating data NIS...");
            rs.updateInt("nis", students.getNIS());
          } else {
            System.out.println("Not update data NIS...");
          }
          if(students.getName() != null) {
            System.out.println("Updating data Name !!!");
            rs.updateString("name", students.getName());
          } else {
            System.out.println("Not update data Name...");
          }
          String tempGenderData = Character.toString(students.getGender());
          if(tempGenderData != null) {
            System.out.println("Updating data Gender !!!");
            rs.updateString("gender", tempGenderData);
          } else {
            System.out.println("Not update data Gender !!!");
          }
          rs.updateRow();
          System.out.println("Uppdating data finished:)");
        }
      } else {
        System.err.println("[INFO] Data on number "+dataNo+" is does not exist !!!\n");
      }
    } catch(SQLException sqle) {
      sqle.printStackTrace();
    }
  }
  
  /**
   * Input data baru data Students.
   */
  private Students isInputNewData() throws Exception {
    Students newStudents = new Students();
    int newNIS = 0;
    String newName;
    char newGender;
    for(int i = 0; i < 4; i++) {
      switch(i) {
        case 0 -> {
          System.out.println("Enter new data NIS(Integer value):");
          newNIS = scanner.nextInt();
          if(Operation.isYesOrNo("Save new NIS")) {
            newStudents.setNIS(newNIS);
          }
        }
        case 1 -> {
          scanner.nextLine();
          System.out.println("Enter new data name(String value):");
          newName = scanner.nextLine();
          if(Operation.isYesOrNo("Save new Name")) {
            newStudents.setName(newName);
          }
        }
        case 2 -> {
          System.out.println("Enter new data gender M/W:");
          newGender = scanner.next().charAt(0);
          checkInputGender(newGender);
          if(Operation.isYesOrNo("Save new Gender")) {
            newStudents.setGender(newGender);
          }
        }
      }
    }
    return newStudents;
  }
  
  public boolean dataIsEmpty() throws SQLException {
    try (Statement st = DBase.getConnection().createStatement()) {
      ResultSet rs = st.executeQuery("SELECT id FROM students");
      return rs.next() == true ? false : true;
    } catch(SQLException sqle) {
      sqle.printStackTrace();
    }
    return true;
  }
  
  /**
   * Fungsi untuk operasi DELETE.
   */
  public void serviceDelete() throws Exception {
    // - Cek data pada database apakah kosong ?
    System.out.println("Data is empty: "+dataIsEmpty());
    // - Tampilkan semua data
    serviceRead();
    // - Ambil no data yang akan dihapus
    System.out.println("Enter no data to delete(Integer value):");
    int dataNo = scanner.nextInt();
    // - Cek input valid ?
    // - Cek input ada pada data ?
    try (Statement st = DBase.getConnection().createStatement(
      ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_UPDATABLE
      )) {
      ResultSet rs = st.executeQuery("SELECT * FROM students");
    // - Tampilkan data yang dipilih
      if(rs.absolute(dataNo)) {
        viewAllStudentData(
          rs.getInt("nis"),
          rs.getString("name"),
          rs.getString("gender")
        );
    // - Apkah yakin akan dihapus ?
        if(Operation.isYesOrNo("Delete this data")) {
    // - Hapus data
          rs.deleteRow();
          System.err.println("[INFO] Success delete data.");
        } else {
          System.err.println("[INFO] Cancel to delete data !!!");
        }
      } else {
        System.err.println("[INFO] Data on number "+dataNo+" is does not exist !!!\n");
      }
    }
  }
  
}