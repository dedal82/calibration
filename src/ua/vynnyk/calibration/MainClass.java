/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vynnyk
 */
public class MainClass {
    public static Connection con;
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {        
        con = getConnection();
        
        setLookAndFeel();
                              
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormMain().setVisible(true);
            }
        });
        
        closeConnection(con);
    }   
    
    public static Connection getConnection() {        
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            return DriverManager.getConnection("jdbc:hsqldb:file:./data/calibration", "sa", "");            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static void setLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
}
