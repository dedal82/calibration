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
import ua.vynnyk.calibration.model.entity.TypeMeters;

/**
 *
 * @author vynnyk
 */
class TypeMetersToData implements TypeMetersDao {
    
    private static final String TABLE = "types_meters";
    private static final List<String> FIELDS = new ArrayList<>();
    
    static {
        FIELDS.add("id");               //0
        FIELDS.add("name");             //1
        FIELDS.add("diameter");         //2
        FIELDS.add("cycle");            //3
        FIELDS.add("precisions");       //4        
    }
    
    private Connection con;
    private DataWorker dw;

    TypeMetersToData(Connection con) {
        this.con = con;
        this.dw = new DataWorker(new QueryBuilder(TABLE, FIELDS), con);
    }
    
    // Returns one entity from current position in the result set or null in case of the exception 
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
    
    @Override
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
    
    @Override
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
    
    @Override
    public int insert(TypeMeters tm) {
        List<Object> param = new ArrayList<>();
        
        param.add(tm.getName());
        param.add(tm.getDiameter());
        param.add(tm.getCycle());              
        param.add(tm.getPrecisions());              
        if (dw.insertRecord(param) == 1) {
            return dw.getIdentity();
        }
        return -1;        
    }
    
    @Override
    public int update(TypeMeters tm) {     
        List<Object> param = new ArrayList<>();
        
        param.add(tm.getName());
        param.add(tm.getDiameter());
        param.add(tm.getCycle());              
        param.add(tm.getPrecisions());
        param.add(tm.getId());
        return dw.updateRecord(param);        
    }
    
    @Override
    public int delete(TypeMeters tm) {
        return dw.deleteRecord(tm.getId());
    }

    @Override
    public List<TypeMeters> selectAll() {
        return selects(null);
    }
}
