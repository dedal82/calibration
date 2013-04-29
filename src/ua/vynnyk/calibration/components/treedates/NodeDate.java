/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.components.treedates;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author vynnyk
 */
public class NodeDate extends Node {
    
    public NodeDate(Date date) {
        super(date);
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("dd.MM.yy").format(getDate());
    }

    @Override
    public void open() {
        ((NodeRoot) getRoot()).openDate(getDate());
    }
    
    
}
