package com.crud.data;

/**
 * Objek berisi berbagai komponen dan untuk data yang dimasukan
 * ke dalam database.
 */
public class Students {
  
  /**
   * Data nis.
   */
  private int nis;
  /**
   * Data name.
   */
  private String name;
  /**
   * Data gender.
   */
  private String gender;
  
  public Students() {}
  
  public Students(int valNis, String valName, String valGender) {
    nis = valNis;
    name = valName;
    gender = valGender;
  }
  
  public int getNIS() throws Exception {
    return nis;
  }
  
  public int getName() throws Exception {
    return name;
  }
  
  public int getGender() throws Exception {
    return gender;
  }
  
}