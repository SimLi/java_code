package v1ch03;

import java.io.Console;
import java.util.*;

/**
 * This program demonstrates console input.
 * @version 1.10 2004-02-10
 * @author Cay Horstmann
 */
public class InputTest
{
   public static void main(String[] args)
   {
//      Scanner in = new Scanner(System.in);
//
//      // get first input
//      System.out.println("What is your name? ");
//      String name = in.nextLine();
//
//      // get second input
//      System.out.println("How old are you? ");
//      int age = in.nextInt();
//
//      // display output on console
//      System.out.println("Hello, " + name + ". Next year, you'll be " + (age + 1));
       String ss = String.format("%,.2f", -44562358f);
       System.out.println(ss);
       System.out.println(String.valueOf(null));
   }
   
   public static void testConsole(){
       Console cons = System.console();
       cons.readLine("user name : ");
       
   }
}
