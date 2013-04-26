/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.view;

import java.awt.event.ActionEvent;
import java.util.EnumMap;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import ua.vynnyk.calibration.controler.Controler;
import ua.vynnyk.translations.TH;

/**
 *
 * @author vynnyk
 */
class Actions {
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

    private void setAction(Act act, Action action) {
        actionSet.put(act, action);
    }

    private void createActions() {
        setAction(Act.EXIT, new ExitAction());
    }

    private class ExitAction extends AbstractAction {

        public ExitAction() {
            final String command = "exit";
            final ImageIcon smallIcon = null;
            final ImageIcon largeIcon = null;
            putValue(NAME, TH.getStr("menu.calibration.exit"));
            putValue(SHORT_DESCRIPTION, TH.getStr("menu.calibration.exit.d"));            
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("control Q"));
            putValue(ACTION_COMMAND_KEY, command);
            putValue(SMALL_ICON, smallIcon);
            putValue(LARGE_ICON_KEY, largeIcon);
        }
                
        @Override
        public void actionPerformed(ActionEvent ae) {
            controler.exit();                    
        }        
    } 
    
}
