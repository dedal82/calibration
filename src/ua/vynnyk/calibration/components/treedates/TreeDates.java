/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.components.treedates;

import java.util.Date;
import javax.swing.JTree;

/**
 *
 * @author vynnyk
 */
public class TreeDates extends JTree {
    
    public TreeDates(Date date, OpenDateInterface openDate) {
        super(new NodeRoot(date, openDate));                            
    }        
}
