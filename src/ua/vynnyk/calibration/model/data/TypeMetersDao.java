/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.model.data;

import java.util.List;
import ua.vynnyk.calibration.model.entity.TypeMeters;

/**
 *
 * @author Admin
 */
public interface TypeMetersDao extends Dao<TypeMeters>{

    public List<TypeMeters> selectAll();
    
}
