/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.model.data;

import java.sql.Connection;

/**
 *
 * @author Admin
 */
public interface Model {

    /**
     *
     * @return
     */
    Connection getConnection();

    /**
     *
     * @param c
     * @return
     */
    <T extends Dao> T getEntityModel(Class c);

    /**
     *
     * @return
     */
    boolean isConnected();

    /**
     *
     * @param con
     */
    void setConnection(Connection con);
    
}
