/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import ua.vynnyk.l10n.TH;

/**
 *
 * @author Admin
 */
public class FormAbout extends JDialog {
    private static final int FONT_SIZE = 18;
    private static final int BORDER_SIZE = 10;

    public FormAbout(Frame owner, boolean modal) {
        super(owner, modal);        
        initComponents();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        setTitle(TH.getString("about.title"));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(new JLabel(TH.getString("about.name")));
        panel.add(Box.createVerticalStrut(BORDER_SIZE));
        panel.add(new JLabel(TH.getString("about.author")));
        panel.add(Box.createVerticalStrut(BORDER_SIZE));
        panel.add(new JLabel(TH.getString("about.copyright")));
        panel.add(Box.createVerticalStrut(BORDER_SIZE));
        
        Font font = new Font(null, Font.PLAIN, FONT_SIZE);
        
        for (int i = 0; i < panel.getComponentCount(); i++) {
            Component c = panel.getComponent(i);
            if (c instanceof JLabel) {
                JLabel label = (JLabel) c;
                label.setFont(font);
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
            }        
        }
        
        JButton button = new JButton(TH.getString("close"));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
        
        add(panel);
        
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
                                
        pack();
    }
        
}