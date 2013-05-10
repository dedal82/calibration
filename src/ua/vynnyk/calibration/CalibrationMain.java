/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration;

import ua.vynnyk.calibration.controler.Controler;
import ua.vynnyk.calibration.model.data.Model;
import ua.vynnyk.calibration.model.data.ModelImpl;
import ua.vynnyk.calibration.view.FormMain;
import ua.vynnyk.calibration.view.View;

/**
 *
 * @author Admin
 */
public class CalibrationMain {
    public static void main(String[] args) {
        final Model model = new ModelImpl();
        final Controler controler = new Controler(model);
        final View view = new FormMain(controler);
        
        controler.setView(view);
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ((FormMain) view).setVisible(true);
            }
        });          
    }    
}

