/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management;

import java.sql.*;
import javax.swing.JOptionPane;


public class ConnectDB {

   // JDBC driver name and database URL
     // online server's remote mysql
    /*
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = ""; //Example: jdbc:mysql://ip_address:port/db_name

   //  Database credentials
   static final String USER = "";
   static final String PASS = "";
   */

   //Local PC database

   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = ""; //Example: jdbc:mysql://localhost:port/db_name

   //  Database credentials
   
   static final String USER = "";
   static final String PASS = "";
   
  /* static final String USER = "root";
   static final String PASS = "root";*/
   public static String username ;
   
   public static Connection connection() {
       
   Connection conn ;
   
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver").newInstance();

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      
      return conn;

   }catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
       JOptionPane.showMessageDialog(null, e);
      return null;
   }

}
   
}
    

