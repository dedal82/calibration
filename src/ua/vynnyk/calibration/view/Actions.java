/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.view;

import java.awt.event.ActionEvent;
import java.util.EnumMap;
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
    private final Class ICONS = Icons.class;    
    private final String EXT = ".png";
    private final String BIG_EXT = "_big.png";
    private final String ACT_NAME = ".action.name";
    private final String ACT_SHORT_DESCRIPTION = ".action.sd";    
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
            configureAction(Act.EXIT, this);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("control Q"));            
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
        final ImageIcon smallIcon = new ImageIcon(ICONS.getResource(iconFile));
        final ImageIcon largeIcon = new ImageIcon(ICONS.getResource(iconBigFile));
        action.putValue(NAME, TH.getStr(command + ACT_NAME));
        action.putValue(SHORT_DESCRIPTION, TH.getStr(command + ACT_SHORT_DESCRIPTION));            
        //will be replased to store hot keys into configure file
        //action.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("control Q"));
        action.putValue(ACTION_COMMAND_KEY, command);
        action.putValue(SMALL_ICON, smallIcon);
        action.putValue(LARGE_ICON_KEY, largeIcon);
    }
    
}
