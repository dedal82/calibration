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
public class NodeYear extends Node {

    public NodeYear(Date date) {
        super(date);
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("YYYY").format(getDate());
    }    

    @Override
    public void open() {
        if (getChildCount() == 0) {
            List<Date> listDate = getChildDates();
            for (Date date: listDate) {
                add(new NodeMonth(date));            
            }
        }    
    }
    
    private List<Date> getChildDates() {        
        List<Date> listDate = new ArrayList<>(12); 
        final long now = System.currentTimeMillis();
        Calendar tmpDate = Calendar.getInstance();
        tmpDate.setTime(getDate());
        for (int i = 0; i < 12 && tmpDate.getTimeInMillis() < now ; i++) {        
            listDate.add(tmpDate.getTime());
            tmpDate.add(Calendar.MONTH, 1);            
        }  
        Collections.reverse(listDate);
        return listDate;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }            
}