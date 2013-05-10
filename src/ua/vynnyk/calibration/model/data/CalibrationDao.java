/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.model.data;

import java.util.Date;
import java.util.List;
import ua.vynnyk.calibration.model.entity.Calibration;

/**
 *
 * @author Admin
 */
public interface CalibrationDao extends Dao<Calibration>{
    
    List<Calibration> selectCalibrationForDate(Date date);
    
}
