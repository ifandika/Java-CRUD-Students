package com.crud.ui;

import java.util.*;

import com.crud.oprt.*;
import com.crud.service.*;

public class Menu {
  private static Scanner scanner = new Scanner(System.in);
  private static Service service = new Service();
  
  public static void mainMenu() throws Exception {
    menu: while(true) {
      System.out.println("\n[ CRUD Students ]");
      System.out.println("1: Create new data students");
      System.out.println("2: Read data students");
      System.out.println("3: Update data students");
      System.out.println("4: Delete data students");
      System.out.println("5: Exit app");
      System.out.println();
      System.out.println("Enter menu 1-5:");
      char input = scanner.next().charAt(0);
      switch(input) {
        case '1' -> menuCreate();
        case '2' -> menuRead();
        case '3' -> menuUpdate();
        case '4' -> menuDelete();
        case '5' -> {
          boolean exit = Operation.isYesOrNo("Exit");
          if(exit) break menu;
        }
        default -> System.out.println("[INFO] Input not found");
      }
      System.out.println();
      boolean next = Operation.isYesOrNo("Continue");
      if(!next) {
        break menu;
      }
    }
  }
  
  private static void menuCreate() throws Exception {
    System.out.println("[ Menu CREATE ]");
    service.serviceCreate();
  }
  
  private static void menuRead() throws Exception {
    System.out.println("[ Menu READ ]");
    service.serviceRead();
  }
  
  private static void menuUpdate() throws Exception {
    System.out.println("[ Menu UPDATE ]");
    service.serviceUpdate();
  }
  
  private static void menuDelete() throws Exception {
    System.out.println("[ Menu DELETE ]");
    service.serviceDelete();
  }
  
}