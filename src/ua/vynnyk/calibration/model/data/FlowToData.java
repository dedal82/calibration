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
class FlowToData implements Entity<Flow> {
    
    private static final String tableName;
    private static final List<String> fields = new ArrayList<>();
    
    static {
        tableName = "flows";
        fields.add("id");               //0
        fields.add("diameter");         //1
        fields.add("capacity1");        //2
        fields.add("flow1");            //3
        fields.add("capacity2");        //4
        fields.add("flow2");            //5
        fields.add("capacity3");        //6
        fields.add("flow3");            //7      
    }
    
    private Connection con;
    private DataWorker dw;

    FlowToData(Connection con) {
        this.con = con;
        this.dw = new DataWorker(new QueryBuilder(tableName, fields), con);
    }
    
    // повертає один обєкт з поточного запису в резултсеті
    private Flow sel(ResultSet rs) {      
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
    public Flow select(int id) {
        ResultSet rs = dw.selectRecord(id);
        try {
            if (rs != null && rs.next()) {           
                return sel(rs);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CalibrationToData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // повертає сет обєктів з резултсету
    public List<Flow> selects(String condition) {
        ResultSet rs = dw.selectRecords(condition);
        if (rs != null) {
            List<Flow> cs = new ArrayList<>();
            try {
                while (rs.next()) {
                    cs.add(sel(rs));                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(CalibrationToData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return cs;
        } else {
            return null;
        }
    }
    
    public int insert(Flow f) {
        List<Object> param = new ArrayList<>();        
        param.add(f.getDiameter());
        param.add(f.getCapacity1());
        param.add(f.getFlow1());
        param.add(f.getCapacity2());
        param.add(f.getFlow2());
        param.add(f.getCapacity3());
        param.add(f.getFlow3());              
        return dw.insertRecord(param);        
    }
    
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
    
    public int delete(Flow f) {
        return dw.deleteRecord(f.getId());
    }
}
