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
  private char gender;
  
  public Students() {}
  
  public Students(int valNis, String valName, char valGender) {
    nis = valNis;
    name = valName;
    gender = valGender;
  }
  
  public int getNIS() throws Exception {
    return nis;
  }
  
  public String  getName() throws Exception {
    return name;
  }
  
  public char getGender() throws Exception {
    return gender;
  }
  
  public void setNIS(int val) throws Exception {
    if(val <= 0) throw new RuntimeException("Value is zero or negatif");
    nis = val;
  }
  
  public void setName(String val) throws Exception {
    name = val;
  }
  
  public void setGender(char val) throws Exception {
    gender = val;
  }
  
}