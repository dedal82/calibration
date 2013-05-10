/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.components.treedates;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vynnyk
 */
public class NodeMonth extends Node {

    public NodeMonth(Date date) {
        super(date);
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("MMMM", dfs).format(getDate());
    }        

    @Override
    public void open() {  
        if (getChildCount() == 0) {
            List<Date> listDate = getChildDates();
            for (Date date: listDate) {
                add(new NodeDate(date));            
            }                
        }
    }
    
    private List<Date> getChildDates() {        
        List<Date> listDate = new ArrayList<>(12);
        final long now = System.currentTimeMillis();
        Calendar tmpDate = Calendar.getInstance();
        tmpDate.setTime(getDate());
        int max = tmpDate.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < max && tmpDate.getTimeInMillis() < now; i++) {        
            listDate.add(tmpDate.getTime());
            tmpDate.add(Calendar.DAY_OF_MONTH, 1);          
        }  
        Collections.reverse(listDate);
        return listDate;
    }
    
    @Override
    public boolean isLeaf() {
        return false;
    }
}
