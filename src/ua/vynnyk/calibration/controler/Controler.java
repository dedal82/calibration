/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.controler;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import ua.vynnyk.calibration.model.data.CalibrationDao;
import ua.vynnyk.calibration.model.data.FlowDao;
import ua.vynnyk.calibration.model.data.MeterDao;
import ua.vynnyk.calibration.model.data.Model;
import ua.vynnyk.calibration.model.data.TypeMetersDao;
import ua.vynnyk.calibration.model.entity.Calibration;
import ua.vynnyk.calibration.model.entity.Flow;
import ua.vynnyk.calibration.model.entity.Meter;
import ua.vynnyk.calibration.model.entity.TypeMeters;
import ua.vynnyk.calibration.view.View;

/**
 *
 * @author Admin
 */
public class Controler {
    private Model model;
    private View view;
                
    private CalibrationDao eCalibration;
    private MeterDao eMeter;
    private TypeMetersDao eTypeMeters;
    private FlowDao eFlow;

    public Controler(Model model) {
        this.model = model; 
        
        Connection con = DB.openConnection();
        model.setConnection(con);
        
        eCalibration = model.getEntityModel(Calibration.class);
        eMeter = model.getEntityModel(Meter.class);
        eTypeMeters = model.getEntityModel(TypeMeters.class);
        eFlow = model.getEntityModel(Flow.class);
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
    
    public List<Calibration> getCalibrations(Date date) {       
        return eCalibration.selectCalibrationForDate(date);
    }
        
    public void exit() {
        DB.closeConnection();
        System.exit(0);
    }
    
    public void addCalibrationAct() {
        view.addCalibration();
    }
    
    public void refreshData() {
        view.refreshData();
    }

    public List<TypeMeters> getTypeMeters() {
        return eTypeMeters.selectAll();
    }

    public int addCalibration(Calibration c) {
        return eCalibration.insert(c);
    }

    public int addMeter(Meter m) {
        return eMeter.insert(m);
    }
                    
}
