/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.treedates;

import java.text.DateFormatSymbols;
import java.util.Date;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author vynnyk
 */
public abstract class Node extends DefaultMutableTreeNode {
    private Date date;

    public Node(Date date) {
        super();
        this.date = date;
    }

    public Date getDate() {
        return date;
    }  

    @Override
    public abstract String toString();    
    
    public abstract void open();
    
    static DateFormatSymbols dfs = new DateFormatSymbols() {
        @Override
        public String[] getMonths() {
            return new String[] {"Січень", "Лютий", "Березень",
                                 "Квітень", "Травень", "Червень",
                                 "Липень", "Серпень", "Вересень",
                                 "Жовтень", "Листопад", "Грудень"};
        }        
    };                    
}
