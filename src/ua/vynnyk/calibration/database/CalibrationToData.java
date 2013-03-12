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
import ua.vynnyk.calibration.entity.Calibration;
import ua.vynnyk.calibration.entity.Meter;

/**
 *
 * @author vynnyk
 */
public class CalibrationToData {
    private static final String tableName;
    private static final List<String> fields = new ArrayList<>();
    static {
        tableName = "calibrations";
        fields.add("id");               //0
        fields.add("meter");            //1
        fields.add("dates");            //2
        fields.add("error0");           //3
        fields.add("error1");           //4
        fields.add("error2");           //5
        fields.add("error3");           //6
        fields.add("meterage_st");      //7
        fields.add("meterage_end");     //8
        fields.add("dstu_number");      //9
        fields.add("dstu_seal");        //10
    }
    private Connection con;
    private DataWorker dw;

    public CalibrationToData(Connection con) {
        this.con = con;
        this.dw = new DataWorker(new QueryBuilder(tableName, fields), con);
    }
    
    // повертає один обєкт з поточного запису в резултсеті
    private Calibration sel(ResultSet rs) {      
        try {        
            Calibration c = new Calibration(rs.getInt(1),
                                            new Meter(rs.getInt(2)),
                                            rs.getDate(3),
                                            rs.getBigDecimal(4),
                                            rs.getBigDecimal(5),
                                            rs.getBigDecimal(6),
                                            rs.getBigDecimal(7),
                                            rs.getBigDecimal(8),
                                            rs.getBigDecimal(9),
                                            rs.getInt(10),
                                            rs.getString(11));
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(CalibrationToData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;            
    }
    
    // повертає один обєкт з резултсету
    public Calibration select(int id) {
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
    public List<Calibration> selects(String condition) {
        ResultSet rs = dw.selectRecords(condition);
        if (rs != null) {
            List<Calibration> cs = new ArrayList<>();
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
    
    public int insert(Calibration c) {
        List<Object> param = new ArrayList<>();
        param.add(c.getMeters().getId());
        param.add(c.getDates());
        param.add(c.getError0());
        param.add(c.getError1());
        param.add(c.getError2());
        param.add(c.getError3());
        param.add(c.getDates());
        param.add(c.getMeterageSt());
        param.add(c.getMeterageEnd());
        param.add(c.getDstuNumber());
        param.add(c.getDstuSeal());
        return dw.insertRecord(param);        
    }
    
    public int update(Calibration c) {
        List<Object> param = new ArrayList<>();        
        param.add(c.getMeters().getId());
        param.add(c.getDates());
        param.add(c.getError0());
        param.add(c.getError1());
        param.add(c.getError2());
        param.add(c.getError3());
        param.add(c.getDates());
        param.add(c.getMeterageSt());
        param.add(c.getMeterageEnd());
        param.add(c.getDstuNumber());
        param.add(c.getDstuSeal());
        param.add(c.getId());
        return dw.updateRecord(param);        
    }
    
    public int delete(Calibration c) {
        return dw.deleteRecord(c.getId());
    }
}
