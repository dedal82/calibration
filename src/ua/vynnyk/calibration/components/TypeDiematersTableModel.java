/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.components;

import java.math.BigDecimal;
import java.util.List;
import ua.vynnyk.calibration.model.entity.Flow;

/**
 *
 * @author Admin
 */
public class TypeDiematersTableModel extends AbstracGenerictListTableModel<Flow>{
    
    private static final String[] TITLES = new String[] {"№",
                                                         "Діаметр",
                                                         "Об'єм 1",
                                                         "Потік 1",
                                                         "Об'єм 2",
                                                         "Потік 2",
                                                         "Об'єм 3",
                                                         "Потік 3"};
    
    private static final Class[] COLUMN_CLASSES = {Integer.class,
                                                   Integer.class,
                                                   BigDecimal.class,                                                  
                                                   BigDecimal.class,
                                                   BigDecimal.class,                                                  
                                                   BigDecimal.class,
                                                   BigDecimal.class,                                                  
                                                   BigDecimal.class};
    
    public TypeDiematersTableModel(List<Flow> dataList) {
        super(TITLES, dataList);
    }

    @Override
    public Object getValueAt(int i, int i1) {
        final Flow flow = dataList.get(i);
        switch (i1) {
            case 0: return flow.getId(); 
            case 1: return flow.getDiameter();
            case 2: return flow.getCapacity1();
            case 3: return flow.getFlow1();
            case 4: return flow.getCapacity2();
            case 5: return flow.getFlow2();
            case 6: return flow.getCapacity3();
            case 7: return flow.getFlow3();
            default: return null;
        }                
    } 

    @Override
    public Class<?> getColumnClass(int i) {
        return COLUMN_CLASSES[i];
    }
        
}