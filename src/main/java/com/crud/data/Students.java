package com.crud.data;

/**
 * Objek berisi berbagai komponen data dan untuk format data yang dimasukan
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
  
  /**
   * Ambil data nis.
   */
  public int getNIS() throws Exception {
    return nis;
  }
  
  /**
   * Ambil data name.
   */
  public String  getName() throws Exception {
    return name;
  }
  
  /**
   * Ambil data gender.
   */
  public char getGender() throws Exception {
    return gender;
  }
  
  /**
   * Ganti data nis.
   */
  public void setNIS(int val) throws Exception {
    if(val <= 0) throw new RuntimeException("Value is zero or negatif");
    nis = val;
  }
  
  /**
   * Ganti data name.
   */
  public void setName(String val) throws Exception {
    name = val;
  }
  
  /**
   * Ganti data gender.
   */
  public void setGender(char val) throws Exception {
    gender = val;
  }
  
}