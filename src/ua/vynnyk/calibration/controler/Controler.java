/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.controler;

import java.sql.Connection;
import java.text.SimpleDateFormat;
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
import ua.vynnyk.calibration.view.FormCalibration;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return eCalibration.selects("where dates = \'" + sdf.format(date) + "\'");
    }
        
    public void exit() {
        DB.closeConnection();
        System.exit(0);
    }
    
    public void addCalibration() {
        new FormCalibration(null , "Повірка", true).setVisible(true);
    }
    
    public void refreshData() {
        view.refreshData();
    }
                    
}
