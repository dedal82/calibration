/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.tests;

import java.sql.Connection;
import ua.vynnyk.calibration.MainClass;
import ua.vynnyk.calibration.database.MeterToData;
import ua.vynnyk.calibration.entity.Meter;
import ua.vynnyk.calibration.entity.TypeMeters;

/**
 *
 * @author vynnyk
 */
public class testMeterToData {
    public static void main(String[] args) {
        Connection con = MainClass.getConnection();
        MeterToData meterToData = new MeterToData(con);
        int id = meterToData.insert(new Meter(0, new TypeMeters(1), "222", 2013));
        System.out.println(id);
        id = meterToData.insert(new Meter(0, new TypeMeters(1), "333", 2012));
        System.out.println(id);
        MainClass.closeConnection(con);
    }
}
