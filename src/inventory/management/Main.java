/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {

   
   
    public static void main(String args[])
    {

        SimpleDateFormat sdf = new SimpleDateFormat("d/MMMM/yyyy");
        String date = sdf.format(new Date()); 
        System.out.println(date);


        loginForm login = new loginForm();

        login.setVisible(true);


    }

}
   
    

