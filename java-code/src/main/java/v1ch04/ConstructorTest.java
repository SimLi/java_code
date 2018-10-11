package v1ch04;

import java.util.*;

/**
 * This program demonstrates object construction.
 * @version 1.01 2004-02-19
 * @author Cay Horstmann
 */
public class ConstructorTest
{
   public static void main(String[] args)
   {
      // fill the staff array with three Employee objects
       ConEmployee[] staff = new ConEmployee[3];

//      staff[0] = new ConEmployee("Harry", 40000);
      staff[1] = new ConEmployee(60000);
//      staff[2] = new ConEmployee();
//
//      // print out information about all Employee objects
//      for (ConEmployee e : staff)
//         System.out.println("name=" + e.getName() + ",id=" + e.getId() + ",salary="
//               + e.getSalary());
   }
   
}

class ConEmployee
{
   private static int nextId;

   private int id;
   private String name = ""; // instance field initialization
   private double salary;
  
   // static initialization block
   static
   {
      Random generator = new Random();
      // set nextId to a random number between 0 and 9999
      System.out.println("执行顺序Random："+nextId);
      nextId = generator.nextInt(10000);
   }

   // object initialization block
   {
       System.out.println("执行顺序："+nextId);
      id = nextId;
      nextId++;
   }

   // three overloaded constructors
   public ConEmployee(String n, double s)
   {
      System.out.println("执行顺序cons："+nextId);
      name = n;
      salary = s;
   }

   public ConEmployee(double s)
   {
      // calls the Employee(String, double) constructor
      this("Employee #" + nextId, s);
      System.out.println("执行顺序double："+nextId);
   }

   // the default constructor
   public ConEmployee()
   {
      // name initialized to ""--see above
      // salary not explicitly set--initialized to 0
      // id initialized in initialization block
   }

   public String getName()
   {
      return name;
   }

   public double getSalary()
   {
      return salary;
   }

   public int getId()
   {
      return id;
   }
}
