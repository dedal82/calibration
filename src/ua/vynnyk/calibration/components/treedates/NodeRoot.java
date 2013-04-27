/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.components.treedates;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vynnyk
 */
public class NodeRoot extends Node {
    
    private OpenDateInterface openDate;
    
    public NodeRoot(Date date, OpenDateInterface openDate) {
        super(date);
        this.openDate = openDate;
    }

    @Override
    public String toString() {
        return "Період";
    }

    @Override
    public void open() {        
        if (getChildCount() == 0) {
            List<Date> listDate = getChildDates();
            for (Date date: listDate) {                
                add(new NodeYear(date));            
            }
        }         
    }
    
    private List<Date> getChildDates() {        
        List<Date> listDate = new ArrayList<>();
        final long now = System.currentTimeMillis();
        Calendar tmpDate = Calendar.getInstance();
        tmpDate.setTime(getDate());
        while (tmpDate.getTimeInMillis() <= now) {
            listDate.add(tmpDate.getTime());
            tmpDate.add(Calendar.YEAR, 1);          
        }  
        Collections.reverse(listDate);
        return listDate;
    }
    
    @Override
    public boolean isLeaf() {
        return false;
    }

    public void openDate(Date date) {        
        openDate.openDate(date);
    }        
}
