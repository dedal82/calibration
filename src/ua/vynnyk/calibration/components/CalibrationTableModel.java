/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.components;

import java.util.List;
import ua.vynnyk.calibration.entity.Calibration;

/**
 *
 * @author vynnyk
 */
public class CalibrationTableModel extends AbstracGenerictListTableModel<Calibration> {

    public CalibrationTableModel(List<Calibration> dataList) {
        super(Calibration.title, dataList);
    }

    @Override
    public Object getValueAt(int i, int i1) {
        final Calibration calibtation = dataList.get(i);
        switch (i1) {
            case 0: return calibtation.getId(); 
            case 1: return calibtation.getMeters().getNumber();
            case 2: return calibtation.getError0();
            case 3: return calibtation.getError1();
            case 4: return calibtation.getError2();
            case 5: return calibtation.getError3();
            case 6: return calibtation.getMeterageSt();
            case 7: return calibtation.getMeterageEnd();
            case 8: return calibtation.getDstuNumber();
            case 9: return calibtation.getDstuSeal();
        }
        return null;
    }   
}
