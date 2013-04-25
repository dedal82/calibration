package ua.vynnyk.calibration.model.data;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.vynnyk.calibration.model.entity.Calibration;
import ua.vynnyk.calibration.model.entity.Flow;
import ua.vynnyk.calibration.model.entity.Meter;
import ua.vynnyk.calibration.model.entity.TypeMeters;

/**
 *
 * @author vynnyk
 */
public class ModelImpl implements Model {
    
    private Connection con;

    public ModelImpl() {
    }

    public ModelImpl(Connection con) {
        this.con = con;
    }
           
    @Override
    public Entity getEntityModel(Class c) {                                    
        if (c == Calibration.class) {
            return (Entity) new CalibrationToData(con);
        }     
        if (c == Meter.class) {
            return (Entity) new MeterToData(con);
        } 
        if (c == TypeMeters.class) {
            return (Entity) new TypeMetersToData(con);
        } 
        if (c == Flow.class) {
            return (Entity) new FlowToData(con);
        } 
        return null;
    }

    @Override
    public Connection getConnection() {
        return con;
    }

    @Override
    public void setConnection(Connection con) {
        this.con = con;
    }  
    
    @Override
    public boolean isConnected() {        
        try {
            return !con.isClosed();
        } catch (SQLException ex) {
            Logger.getLogger(ModelImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } 
}
