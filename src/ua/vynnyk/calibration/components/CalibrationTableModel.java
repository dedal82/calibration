/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.components;

import java.math.BigDecimal;
import java.util.List;
import ua.vynnyk.calibration.model.entity.Calibration;

/**
 *
 * @author vynnyk
 */
public class CalibrationTableModel extends AbstracGenerictListTableModel<Calibration> {
    
    private static final String[] TITLES = new String[] {"№",
                                                         "Водомір",
                                                         "Початкова похибка",
                                                         "Похибка 1",
                                                         "Похибка 2",
                                                         "Похибка 3",
                                                         "Показник зняття",
                                                         "Показник встановлення",
                                                         "ДСТУ №",
                                                         "ДСТУ пломба"};
    
    private static final Class[] COLUMN_CLASSES = {Integer.class,
                                                   String.class,
                                                   BigDecimal.class,
                                                   BigDecimal.class,
                                                   BigDecimal.class,
                                                   BigDecimal.class,
                                                   BigDecimal.class,
                                                   BigDecimal.class,
                                                   Integer.class,
                                                   String.class};
    
    
    public CalibrationTableModel(List<Calibration> dataList) {
        super(TITLES, dataList);
    }

    @Override
    public Object getValueAt(int i, int i1) {
        final Calibration calibtation = dataList.get(i);
        switch (i1) {
            case 0: return calibtation.getId(); 
            case 1: return calibtation.getMeter().getNumber();
            case 2: return calibtation.getError0();
            case 3: return calibtation.getError1();
            case 4: return calibtation.getError2();
            case 5: return calibtation.getError3();
            case 6: return calibtation.getMeterageSt();
            case 7: return calibtation.getMeterageEnd();
            case 8: return calibtation.getDstuNumber();
            case 9: return calibtation.getDstuSeal();
            default: return null;
        }                
    } 

    @Override
    public Class<?> getColumnClass(int i) {
        return COLUMN_CLASSES[i];
    }
        
}
