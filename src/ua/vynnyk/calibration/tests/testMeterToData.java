/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.vynnyk.calibration.FormMain;
import ua.vynnyk.calibration.database.MeterToData;
import ua.vynnyk.calibration.entity.Meter;
import ua.vynnyk.calibration.entity.TypeMeters;

/**
 *
 * @author vynnyk
 */
public class testMeterToData {
    public static void main(String[] args) {
        Connection con = openConnection();
        MeterToData meterToData = new MeterToData(con);
        int id = meterToData.insert(new Meter(0, new TypeMeters(1), "222", 2013));
        System.out.println(id);
        id = meterToData.insert(new Meter(0, new TypeMeters(1), "333", 2012));
        System.out.println(id);
        closeConnection(con);
    }
    
    private static Connection openConnection() {        
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            return DriverManager.getConnection("jdbc:hsqldb:file:./data/calibration", "sa", "");            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
}
