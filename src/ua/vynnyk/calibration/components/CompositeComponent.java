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
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        //setBackground(component.getBackground());
        //setBorder(component.getBorder());
        //component.setBorder(null);
        add(component);        
        add(button);        
    }
    
}
