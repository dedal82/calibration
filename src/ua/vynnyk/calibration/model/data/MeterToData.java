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
import ua.vynnyk.calibration.model.entity.Meter;
import ua.vynnyk.calibration.model.entity.TypeMeters;

/**
 *
 * @author vynnyk
 */
class MeterToData implements MeterDao {
    
    private static final String TABLE= "meters";
    private static final List<String> FIELDS = new ArrayList<>();
    
    static {        
        FIELDS.add("id");               //0
        FIELDS.add("types");            //1
        FIELDS.add("number");           //2
        FIELDS.add("year_produce");     //3        
    }
    
    private Connection con;
    private DataWorker dw;

    MeterToData(Connection con) {
        this.con = con;
        this.dw = new DataWorker(new QueryBuilder(TABLE, FIELDS), con);
    }
    
    // повертає один обєкт з поточного запису в резултсеті
    private Meter convertRow(ResultSet rs) {      
        try {
            Meter m = new Meter(rs.getInt(1),
                                new TypeMeters(rs.getInt(2)),
                                rs.getString(3),
                                rs.getInt(4));            
            return m;
        } catch (SQLException ex) {
            Logger.getLogger(CalibrationToData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;       
    }
    
    // повертає один обєкт з резултсету
    @Override
    public Meter select(int id) {
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
    public List<Meter> selects(String condition) {
        ResultSet rs = dw.selectRecords(condition);
        if (rs != null) {
            List<Meter> cs = new ArrayList<>();
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
    public int insert(Meter m) {
        List<Object> param = new ArrayList<>();        
        param.add(m.getTypesMeters().getId());
        param.add(m.getNumber());
        param.add(m.getYearProduce());              
        if (dw.insertRecord(param) == 1) {
            return dw.getIdentity();
        }
        return -1;
    }
    
    @Override
    public int update(Meter m) {     
        List<Object> param = new ArrayList<>();        
        param.add(m.getTypesMeters().getId());
        param.add(m.getNumber());
        param.add(m.getYearProduce()); 
        param.add(m.getId());
        return dw.updateRecord(param);        
    }
    
    @Override
    public int delete(Meter m) {
        return dw.deleteRecord(m.getId());
    }
}
