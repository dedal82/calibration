/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.components;

import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author vynnyk
 */
public abstract class AbstracGenerictListTableModel<T> extends AbstractTableModel {
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
}
