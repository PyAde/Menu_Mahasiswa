/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication_hello;

//import com.sun.jdi.connect.spi.Connection;
import view.Dashboard;
import config.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author pyade 
 */
public class JavaApplication_Hello {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        new Dashboard().setVisible(true);
        
//        
    System.out.println("Test koneksi pada java_db");
    
    try{
        Connection koneksi = DBConnection.getConnection();
        if(koneksi != null){
            System.out.println("Berhasil terhubung");
            koneksi.close();
        }
    }
    catch(SQLException e){
           System.err.println("Gagal terhubung karena " + e.getMessage());
    }
//        System.out.print("hello word");
    }
    
}
