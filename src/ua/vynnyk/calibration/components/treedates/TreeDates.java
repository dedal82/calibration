/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.components.treedates;

import java.util.Date;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author vynnyk
 */
public class TreeDates extends JTree {
    
    public TreeDates(Date date, OpenDateInterface openDate) {
        super(new NodeRoot(date, openDate));   
        
        addListener();
        
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        ((Node) getModel().getRoot()).open();
        ((DefaultTreeModel) getModel()).reload();
    }        

    private void addListener() {
        addTreeSelectionListener(new TreeSelectionListener() {
            
            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                Node node = (Node) getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }
                node.open();    
            }            
        }); 
    }
}
