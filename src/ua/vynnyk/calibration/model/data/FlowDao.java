/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.model.data;

import java.util.List;
import ua.vynnyk.calibration.model.entity.Flow;

/**
 *
 * @author Admin
 */
public interface FlowDao extends Dao<Flow>{

    public List<Flow> selectAll();
    
}
