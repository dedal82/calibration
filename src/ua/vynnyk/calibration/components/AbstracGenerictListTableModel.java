/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.components;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


/**
 *
 * @author vynnyk
 */
public abstract class AbstracGenerictListTableModel<T> implements TableModel {
    private String[] titles;
    protected List<T> dataList;
    
    public AbstracGenerictListTableModel(String[] titles, List<T> dataList) {
        this.titles = titles;
        this.dataList = dataList;
    }
    
    @Override
    public int getRowCount() {
        return dataList.size();
    }

    @Override
    public int getColumnCount() {
        return titles.length;
    }

    @Override
    public String getColumnName(int i) {
        return titles[i];
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
