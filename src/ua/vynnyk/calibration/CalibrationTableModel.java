/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import ua.vynnyk.calibration.entity.Calibration;


/**
 *
 * @author vynnyk
 */
public class CalibrationTableModel implements TableModel {
    final private String[] column = new String[] {"№",
                                                  "Водомір",
                                                  "Початкова похибка",
                                                  "Похибка 1",
                                                  "Похибка 2",
                                                  "Похибка 3",
                                                  "Показник зняття",
                                                  "Показник встановлення",
                                                  "ДСТУ №",
                                                  "ДСТУ пломба"};
    private List<Calibration> calibrationList;
    
    public CalibrationTableModel(List<Calibration> calibrationList) {
        this.calibrationList = calibrationList;
    }
    
    @Override
    public int getRowCount() {
        return calibrationList.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public String getColumnName(int i) {
        return column[i];
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        final Calibration calibtation = calibrationList.get(i);
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

    @Override
    public void setValueAt(Object o, int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addTableModelListener(TableModelListener tl) {
        
    }

    @Override
    public void removeTableModelListener(TableModelListener tl) {
        
    }
    
}
