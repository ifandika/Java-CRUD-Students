package com.crud.oprt;

import java.util.*;

/**
 * Berisi fungsi-fungsi pembantu.
 */
public class Operation {
  private static Scanner scanner = new Scanner(System.in);
  
  public static boolean isYesOrNo(String message) throws Exception {
    System.out.println(message+" Y/n:");
    String input = scanner.next();
    while(!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
      System.err.println("Input not valid...");
      System.out.println(message+" Y/n:");
      input = scanner.next();
    }
    return input.equalsIgnoreCase("y");
  }
  
  public static void clearScreen() {
    try {
      if(System.getProperty("os.name").contains("Windows")) {
        new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
      }
      else {
        System.out.print("\033\143");
     }
    }
    catch(Exception message) {
      System.out.println("Gagal clear terminal");
    }
  }
  
}