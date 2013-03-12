/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.vynnyk.calibration.entity.TypeMeters;

/**
 *
 * @author vynnyk
 */
public class TypeMetersToData {
    private static final String tableName;
    private static final List<String> fields = new ArrayList<>();
    static {
        tableName = "types_meters";
        fields.add("id");               //0
        fields.add("name");             //1
        fields.add("diameter");         //2
        fields.add("cycle");            //3
        fields.add("precisions");       //4        
    }
    private Connection con;
    private DataWorker dw;

    public TypeMetersToData(Connection con) {
        this.con = con;
        this.dw = new DataWorker(new QueryBuilder(tableName, fields), con);
    }
    
    // повертає один обєкт з поточного запису в резултсеті
    private TypeMeters sel(ResultSet rs) {      
        try {
            TypeMeters tm = new TypeMeters(rs.getInt(1),
                                           rs.getString(2),
                                           rs.getInt(3),
                                           rs.getInt(4),                  
                                           rs.getString(5));            
            return tm;
        } catch (SQLException ex) {
            Logger.getLogger(CalibrationToData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;       
    }
    
    // повертає один обєкт з резултсету
    public TypeMeters select(int id) {
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
    public List<TypeMeters> selects(String condition) {
        ResultSet rs = dw.selectRecords(condition);
        if (rs != null) {
            List<TypeMeters> cs = new ArrayList<>();
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
    
    public int insert(TypeMeters tm) {
        List<Object> param = new ArrayList<>();        
        param.add(tm.getName());
        param.add(tm.getDiameter());
        param.add(tm.getCycle());              
        param.add(tm.getPrecisions());              
        return dw.insertRecord(param);        
    }
    
    public int update(TypeMeters tm) {     
        List<Object> param = new ArrayList<>();        
        param.add(tm.getName());
        param.add(tm.getDiameter());
        param.add(tm.getCycle());              
        param.add(tm.getPrecisions());
        param.add(tm.getId());
        return dw.updateRecord(param);        
    }
    
    public int delete(TypeMeters tm) {
        return dw.deleteRecord(tm.getId());
    }
}
