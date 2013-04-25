/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import net.miginfocom.swing.MigLayout;
import ua.vynnyk.calibration.components.CompositeComponent;
import ua.vynnyk.calibration.components.Focus;
import ua.vynnyk.calibration.model.entity.Calibration;

/**
 *
 * @author vynnyk
 */
public class FormCalibration extends JDialog {
    private Frame frame;
    private Calibration calibration;  
    private JButton addButton;
    private JButton updButton;
    private JButton delButton;
    private JButton closeButton;
    private JTextField meterField;
    private JComboBox typesComboBox;
    private JTextField yearField;
    private JTextField error0Field;
    private JTextField error1Field;
    private JTextField error2Field;
    private JTextField error3Field;
    private JTextField meterageStField;
    private JTextField meterageEndField;
    private JTextField numberDSTUField;
    private JTextField sealDSTUField;
    
    public void setCalibration(Calibration calibration) {
        this.calibration = calibration;
    }

    public FormCalibration(Frame frame, String string, boolean bln) {
        super(frame, string, bln);
        this.frame = frame;       
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabel meterLabel = new JLabel("Заводський №");
        meterField = new JTextField(20);
        JButton meterButton = new JButton("Знайти...");
        CompositeComponent componentMeter = new CompositeComponent(meterField, meterButton);
        
        JLabel typesLabel = new JLabel("Тип водоміра");
        //typesComboBox = new JComboBox(typeMeters.selects(null).toArray());
                
        JLabel yearLabel = new JLabel("Рік виробництва");
        yearField = new JTextField(20);
        
        JLabel error0Label = new JLabel("Початкова похибка");
        error0Field = new JTextField(20);
        
        JLabel error1Label = new JLabel("Похибка 1");
        error1Field = new JTextField(20);
        
        JLabel error2Label = new JLabel("Похибка 2");
        error2Field = new JTextField(20);
        
        JLabel error3Label = new JLabel("Похибка 3");
        error3Field = new JTextField(20);
        
        JLabel meterageStLabel = new JLabel("Початковий показник");
        meterageStField = new JTextField(20);
        
        JLabel meterageEndLabel = new JLabel("Кінцевий показник");
        meterageEndField = new JTextField(20);
        
        JLabel numberDSTULabel = new JLabel("Довідка ДСТУ");
        numberDSTUField = new JTextField(20);
        
        JLabel sealDSTULabel = new JLabel("Пломба ДСТУ");
        sealDSTUField = new JTextField(20);
        
        JPanel panel = new JPanel(new MigLayout("wrap 4","[align right][][align right][]",""));        
        panel.add(meterLabel);
        panel.add(componentMeter, "grow");
        panel.add(typesLabel);
        panel.add(typesComboBox, "grow");
        panel.add(yearLabel);
        panel.add(yearField, "wrap");
        panel.add(error0Label);
        panel.add(error0Field, "grow");
        panel.add(error1Label);
        panel.add(error1Field, "grow");
        panel.add(error2Label);
        panel.add(error2Field, "grow");
        panel.add(error3Label);
        panel.add(error3Field, "grow");
        panel.add(meterageStLabel);
        panel.add(meterageStField, "grow");
        panel.add(meterageEndLabel);
        panel.add(meterageEndField, "grow");
        panel.add(numberDSTULabel);
        panel.add(numberDSTUField, "grow");
        panel.add(sealDSTULabel);
        panel.add(sealDSTUField, "grow");        
        add(panel, BorderLayout.CENTER);
        
        Dimension btnDimension = new Dimension(95, 28);
        addButton = new JButton("Добавити");
        addButton.setPreferredSize(btnDimension);
        updButton = new JButton("Змінити");
        updButton.setPreferredSize(btnDimension);
        delButton = new JButton("Видалити");
        delButton.setPreferredSize(btnDimension);
        closeButton = new JButton("Закрити");
        closeButton.setPreferredSize(btnDimension);
        
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        add(panelButtons, BorderLayout.SOUTH);
        panelButtons.add(addButton);
        panelButtons.add(updButton);
        panelButtons.add(delButton);
        panelButtons.add(closeButton);
        
        pack();
        setLocationRelativeTo(frame);
        setResizable(false);
        
        addListeners(); 
        
        Focus.setFocusNexComponentKey(panel);
        
    } 

    private void addListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ;
            }
        });
    }
}
