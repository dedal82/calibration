/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.components;

import java.awt.Container;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.KeyStroke;

/**
 *
 * @author vynnyk
 */
public class Focus {
    public static void setFocusNexComponentKey (Container container) {
        Set forwardKeys = container.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
        Set newForwardKeys = new HashSet(forwardKeys);
        newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        container.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newForwardKeys);
    }
}
