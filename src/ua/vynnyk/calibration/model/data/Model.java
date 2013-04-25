/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.model.data;

import java.sql.Connection;
import ua.vynnyk.calibration.model.entity.Calibration;

/**
 *
 * @author vynnyk
 */
public class Model {
    
    private Connection con;

    public Model() {
    }

    public Model(Connection con) {
        this.con = con;
    }
            
    public <T> DataInterface<T> getEntityModel(T entity) {
        if (entity instanceof Calibration) {
            DataInterface<Calibration> data = new CalibrationToData<Calibration>(con);
            return data;
        }
        return null;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }        
}
