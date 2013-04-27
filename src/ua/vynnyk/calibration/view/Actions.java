/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.view;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.EnumMap;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.Action.ACCELERATOR_KEY;
import static javax.swing.Action.ACTION_COMMAND_KEY;
import static javax.swing.Action.LARGE_ICON_KEY;
import static javax.swing.Action.NAME;
import static javax.swing.Action.SHORT_DESCRIPTION;
import static javax.swing.Action.SMALL_ICON;
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
    private static final String KEY_STROKE = "ua.vynnyk.calibration.view.KeyStroke";
    
    private ResourceBundle keyStroke;
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
        keyStroke = ResourceBundle.getBundle(KEY_STROKE);
        
        new ExitAction();
        new AddCalibrationAction();
        new EditCalibrationAction();
        new DeleteCalibrationAction();
        new RefreshAction();
        
        keyStroke = null;
    }

    class ExitAction extends AbstractAction {

        public ExitAction() {            
            configureAction(Act.EXIT, this);                       
        }
                
        @Override
        public void actionPerformed(ActionEvent ae) {
            controler.exit();                    
        }        
    } 
    
    private class AddCalibrationAction extends AbstractAction {

        public AddCalibrationAction() {            
            configureAction(Act.ADD_CALIBRATION, this);                       
        }
                
        @Override
        public void actionPerformed(ActionEvent ae) {
            controler.exit();                    
        }        
    }
    
    private class EditCalibrationAction extends AbstractAction {

        public EditCalibrationAction() {            
            configureAction(Act.EDIT_CALIBRATION, this);                       
        }
                
        @Override
        public void actionPerformed(ActionEvent ae) {
            controler.exit();                    
        }        
    }
    
    private class DeleteCalibrationAction extends AbstractAction {

        public DeleteCalibrationAction() {            
            configureAction(Act.DELETE_CALIBRATION, this);                        
        }
                
        @Override
        public void actionPerformed(ActionEvent ae) {
            controler.exit();                    
        }        
    }
    
    private class RefreshAction extends AbstractAction {

        public RefreshAction() {            
            configureAction(Act.REFRESH, this);                        
        }
                
        @Override
        public void actionPerformed(ActionEvent ae) {
            controler.exit();                    
        }        
    }
    
    private void configureAction(Act act, Action action) {
        final String command = act.name().toLowerCase();
        final String iconFile = command + EXT;
        final String iconBigFile = command + BIG_EXT;
        final ImageIcon smallIcon = new ImageIcon(getIcon(iconFile));
        final ImageIcon largeIcon = new ImageIcon(getIcon(iconBigFile));
        action.putValue(NAME, getRes(command + ACT_NAME));
        action.putValue(SHORT_DESCRIPTION, getRes(command + ACT_SHORT_DESCRIPTION));                    
        action.putValue(ACCELERATOR_KEY, getKeyStroke(command));            
        action.putValue(ACTION_COMMAND_KEY, command);
        action.putValue(SMALL_ICON, smallIcon);
        action.putValue(LARGE_ICON_KEY, largeIcon);
        
        actionSet.put(act, action);
    }
    
    private URL getIcon(String file) {
        return ICONS.getResource(file);
    }
    
    private KeyStroke getKeyStroke(String command) {
        try {
            return KeyStroke.getKeyStroke(keyStroke.getString(command));
        } catch (MissingResourceException e) {
           return null;  
        }              
    }
    
    private String getRes(String key) {
        return TH.getString(key);
    }
}
