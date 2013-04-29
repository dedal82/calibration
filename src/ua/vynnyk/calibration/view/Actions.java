/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.view;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.EnumMap;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.Action.*;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import ua.vynnyk.calibration.controler.Controler;
import ua.vynnyk.calibration.view.icons.Icons;
import ua.vynnyk.translations.TH;

/**
 *
 * @author vynnyk
 */
class Actions {
    private static final Class ICONS = Icons.class;    
    private static final String EXT = ".png";
    private static final String BIG_EXT = "_big.png";
    private static final String ACT_NAME = ".action.name";
    private static final String ACT_SHORT_DESCRIPTION = ".action.sd"; 
    private static final String KEY_STROKE = "KeyStroke.properties";
    
    private Properties keyStrokes;
    private Controler controler;
    private EnumMap<Act, Action> actionSet;

    public Actions(Controler controler) {
        this.controler = controler;
        actionSet = new EnumMap(Act.class);
                
        createActions();                
    }

    public Action getAction(Act act) {
        return actionSet.get(act);
    }   

    private void createActions() { 
        keyStrokes = getKeyStrokes();
        
        for (Act act : Act.values()) {
            String methodName = act.getMethodName();
            Action action = new RefAction(methodName);
            configureAction(act, action);
        }
                        
        keyStrokes = null;
    }
        
    private class RefAction extends AbstractAction {
        private Method method;

        public RefAction(String methodName) {
            try {    
                Class c = controler.getClass();
                this.method = c.getMethod(methodName);
            } catch (NoSuchMethodException | SecurityException ex) {
                Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                               
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                method.invoke(controler);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    private void configureAction(Act act, Action action) {
        final String command = act.name().toLowerCase();
        final String iconFile = command + EXT;
        final String iconBigFile = command + BIG_EXT;
                
        action.putValue(NAME, getRes(command + ACT_NAME));
        action.putValue(SHORT_DESCRIPTION, getRes(command + ACT_SHORT_DESCRIPTION));                    
        action.putValue(ACCELERATOR_KEY, getKeyStroke(command));            
        action.putValue(ACTION_COMMAND_KEY, command);
        
        ImageIcon icon = getIcon(iconFile);
        if (icon != null) {            
            action.putValue(SMALL_ICON, icon);
        }
        
        icon = getIcon(iconBigFile);
        if (icon != null) { 
            action.putValue(LARGE_ICON_KEY, icon);
        }    
        
        actionSet.put(act, action);
    }
    
    private Properties getKeyStrokes() {
        Properties keys = new Properties();
        
        try {
            InputStream in = Actions.class.getResourceAsStream(KEY_STROKE);
            keys.load(in);
        } catch (IOException ex) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return keys;
    }
    
    private ImageIcon getIcon(String file) {
        URL iconURL = ICONS.getResource(file);
        
        return iconURL != null ? new ImageIcon(iconURL) : null;        
    }
    
    private KeyStroke getKeyStroke(String command) {
        try {
            String key = keyStrokes.getProperty(command);
            return KeyStroke.getKeyStroke(key);
        } catch (MissingResourceException e) {
            return null;  
        }              
    }
    
    private String getRes(String key) {
        return TH.getString(key);
    }
}
