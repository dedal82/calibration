/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.model.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.vynnyk.calibration.model.entity.Calibration;
import ua.vynnyk.calibration.model.entity.Meter;

/**
 *
 * @author vynnyk
 */
class CalibrationToData implements CalibrationDao {
    private static final String TABLE = "calibrations";
    private static final List<String> FIELDS = new ArrayList<>();
    
    static {        
        FIELDS.add("id");               //0
        FIELDS.add("meter");            //1
        FIELDS.add("dates");            //2
        FIELDS.add("error0");           //3
        FIELDS.add("error1");           //4
        FIELDS.add("error2");           //5
        FIELDS.add("error3");           //6
        FIELDS.add("meterage_st");      //7
        FIELDS.add("meterage_end");     //8
        FIELDS.add("dstu_number");      //9
        FIELDS.add("dstu_seal");        //10
    }
    
    private Connection con;
    private DataWorker dw;

    CalibrationToData(Connection con) {
        this.con = con;
        this.dw = new DataWorker(new QueryBuilder(TABLE, FIELDS), con);
    }
    
    // повертає один обєкт з поточного запису в резултсеті
    private Calibration convertRow(ResultSet rs) {      
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
    @Override
    public Calibration select(int id) {
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
    public List<Calibration> selects(String condition) {
        ResultSet rs = dw.selectRecords(condition);
        if (rs != null) {
            List<Calibration> cs = new ArrayList<>();
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
    public int insert(Calibration c) {
        List<Object> param = new ArrayList<>();
        param.add(c.getMeter().getId());
        param.add(c.getDates());
        param.add(c.getError0());
        param.add(c.getError1());
        param.add(c.getError2());
        param.add(c.getError3());        
        param.add(c.getMeterageSt());
        param.add(c.getMeterageEnd());
        param.add(c.getDstuNumber());
        param.add(c.getDstuSeal());
        if (dw.insertRecord(param) == 1) {
            return dw.getIdentity();
        }
        return -1;        
    }
    
    @Override
    public int update(Calibration c) {
        List<Object> param = new ArrayList<>();        
        param.add(c.getMeter().getId());
        param.add(c.getDates());
        param.add(c.getError0());
        param.add(c.getError1());
        param.add(c.getError2());
        param.add(c.getError3());     
        param.add(c.getMeterageSt());
        param.add(c.getMeterageEnd());
        param.add(c.getDstuNumber());
        param.add(c.getDstuSeal());
        param.add(c.getId());
        return dw.updateRecord(param);        
    }
    
    @Override
    public int delete(Calibration c) {
        return dw.deleteRecord(c.getId());
    }

    @Override
    public List<Calibration> selectCalibrationForDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Calibration> list = selects("where dates = \'" + sdf.format(date) + "\'");
        setMeters(list);
        return list;
    }

    private void setMeters(List<Calibration> list) {
        MeterDao meterDao = new MeterToData(con);
        for (Calibration calibration : list) {
            int id = calibration.getMeter().getId();
            Meter m = meterDao.select(id);
            calibration.setMeter(m);
        }
    }
}
