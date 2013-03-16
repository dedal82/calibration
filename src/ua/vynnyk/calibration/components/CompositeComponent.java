/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.components;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author vynnyk
 */
public class CompositeComponent extends JPanel {

    public CompositeComponent(JComponent component, JButton button) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));       
        setPreferredSize(component.getPreferredSize());
        add(component);        
        add(button);        
    }
    
}
