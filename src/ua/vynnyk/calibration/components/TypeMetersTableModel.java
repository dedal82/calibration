/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.components;

import java.util.List;

import ua.vynnyk.calibration.model.entity.TypeMeters;

/**
 *
 * @author Admin
 */
public class TypeMetersTableModel extends AbstracGenerictListTableModel<TypeMeters>{
    
    private static final String[] TITLES = new String[] {"№",
                                                         "Тип",
                                                         "Діаметр",
                                                         "Клас",
                                                         "Період"};
    
    private static final Class[] COLUMN_CLASSES = {Integer.class,
                                                   String.class,
                                                   Integer.class,                                                  
                                                   String.class,
                                                   Integer.class};
    
    public TypeMetersTableModel(List<TypeMeters> dataList) {
        super(TITLES, dataList);
    }

    @Override
    public Object getValueAt(int i, int i1) {
        final TypeMeters typeMeters = dataList.get(i);
        switch (i1) {
            case 0: return typeMeters.getId(); 
            case 1: return typeMeters.getName();
            case 2: return typeMeters.getDiameter();
            case 3: return typeMeters.getPrecisions();
            case 4: return typeMeters.getCycle();
            default: return null;
        }                
    } 

    @Override
    public Class<?> getColumnClass(int i) {
        return COLUMN_CLASSES[i];
    }
        
}