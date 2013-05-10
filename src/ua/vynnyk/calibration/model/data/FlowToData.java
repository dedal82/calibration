/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.model.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.vynnyk.calibration.model.entity.Flow;

/**
 *
 * @author vynnyk
 */
class FlowToData implements FlowDao {
    
    private static final String TABLE = "flows";
    private static final List<String> FIELDS = new ArrayList<>();
    
    static {    
        FIELDS.add("id");               //0
        FIELDS.add("diameter");         //1
        FIELDS.add("capacity1");        //2
        FIELDS.add("flow1");            //3
        FIELDS.add("capacity2");        //4
        FIELDS.add("flow2");            //5
        FIELDS.add("capacity3");        //6
        FIELDS.add("flow3");            //7      
    }
    
    private Connection con;
    private DataWorker dw;

    FlowToData(Connection con) {
        this.con = con;
        this.dw = new DataWorker(new QueryBuilder(TABLE, FIELDS), con);
    }
    
    // повертає один обєкт з поточного запису в резултсеті
    private Flow convertRow(ResultSet rs) {      
        try {
                Flow f = new Flow(rs.getInt(1),
                              rs.getInt(2),  
                              rs.getBigDecimal(3),
                              rs.getBigDecimal(4),
                              rs.getBigDecimal(5),
                              rs.getBigDecimal(6), 
                              rs.getBigDecimal(7),
                              rs.getBigDecimal(8));
            return f;
        } catch (SQLException ex) {
            Logger.getLogger(CalibrationToData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;       
    }
    
    // повертає один обєкт з резултсету
    @Override
    public Flow select(int id) {
        ResultSet rs = dw.selectRecord(id);
        try {
            if (rs != null && rs.next()) {           
                return convertRow(rs);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CalibrationToData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // повертає сет обєктів з резултсету
    @Override
    public List<Flow> selects(String condition) {
        ResultSet rs = dw.selectRecords(condition);
        if (rs != null) {
            List<Flow> cs = new ArrayList<>();
            try {
                while (rs.next()) {
                    cs.add(convertRow(rs));                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(CalibrationToData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return cs;
        } else {
            return null;
        }
    }
    
    @Override
    public int insert(Flow f) {
        List<Object> param = new ArrayList<>();        
        param.add(f.getDiameter());
        param.add(f.getCapacity1());
        param.add(f.getFlow1());
        param.add(f.getCapacity2());
        param.add(f.getFlow2());
        param.add(f.getCapacity3());
        param.add(f.getFlow3());              
        if (dw.insertRecord(param) == 1) {
            return dw.getIdentity();
        }
        return -1;        
    }
    
    @Override
    public int update(Flow f) {     
        List<Object> param = new ArrayList<>();        
        param.add(f.getDiameter());
        param.add(f.getCapacity1());
        param.add(f.getFlow1());
        param.add(f.getCapacity2());
        param.add(f.getFlow2());
        param.add(f.getCapacity3());
        param.add(f.getFlow3()); 
        param.add(f.getId());
        return dw.updateRecord(param);        
    }
    
    @Override
    public int delete(Flow f) {
        return dw.deleteRecord(f.getId());
    }
}
