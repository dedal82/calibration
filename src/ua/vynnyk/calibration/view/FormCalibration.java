/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import net.miginfocom.swing.MigLayout;
import ua.vynnyk.calibration.components.CompositeComponent;
import ua.vynnyk.calibration.components.Focus;
import ua.vynnyk.calibration.controler.Controler;
import ua.vynnyk.calibration.model.entity.Calibration;
import ua.vynnyk.calibration.model.entity.Meter;
import ua.vynnyk.calibration.model.entity.TypeMeters;
import ua.vynnyk.l10n.TH;

/**
 *
 * @author vynnyk
 */
public class FormCalibration extends JDialog {

    private final Controler controler;
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
        
    public FormCalibration(Frame frame, String string, boolean bln, Controler controler) {
        super(frame, string, bln);     
        this.controler = controler;
        initComponents();
        setLocationRelativeTo(frame);
    }
    
    public FormCalibration(Frame frame, String string, boolean bln, Controler controler, Calibration c) {
        super(frame, string, bln);
        this.controler = controler;
        this.calibration = c;
        
        initComponents();
        setLocationRelativeTo(frame);
        
        setCalibration(c);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabel meterLabel = new JLabel("Заводський №");
        meterField = new JTextField(20);
        JButton meterButton = new JButton("Знайти...");
        CompositeComponent componentMeter = new CompositeComponent(meterField, meterButton);
        
        JLabel typesLabel = new JLabel("Тип водоміра");
        typesComboBox = new JComboBox(controler.getTypeMeters().toArray());
                
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
        addButton = new JButton(getRes("add"));
        addButton.setPreferredSize(btnDimension);
        updButton = new JButton(getRes("edit"));
        updButton.setPreferredSize(btnDimension);
        delButton = new JButton(getRes("delete"));
        delButton.setPreferredSize(btnDimension);
        closeButton = new JButton(getRes("close"));
        closeButton.setPreferredSize(btnDimension);
        
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        add(panelButtons, BorderLayout.SOUTH);
        panelButtons.add(addButton);
        panelButtons.add(updButton);
        panelButtons.add(delButton);
        panelButtons.add(closeButton);
        
        pack();       
        setResizable(false);             
        
        addListeners(); 
        
        Focus.setFocusNexComponentKey(panel);
        
    } 
    
    private String getRes(String key) {
        return TH.getString(key);
    }

    private void addListeners() {
        Selecter s = new Selecter();
        meterField.addFocusListener(s);    
        yearField.addFocusListener(s);
        error0Field.addFocusListener(s);
        error1Field.addFocusListener(s);
        error2Field.addFocusListener(s);
        error3Field.addFocusListener(s);
        meterageStField.addFocusListener(s);
        meterageEndField.addFocusListener(s);
        numberDSTUField.addFocusListener(s);
        sealDSTUField.addFocusListener(s);
                
        addButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {               
                Calibration c = getCalibration();
                Meter m = c.getMeter();
                
                m.setId(controler.addMeter(m));
                c.setId(controler.addCalibration(c)); 
                
                if (c.getId() > 0) { 
                    calibration = c;
                    controler.refreshData();
                    meterField.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(FormCalibration.this, getRes("error.addcalibration"), 
                                                  getRes("error"), JOptionPane.ERROR_MESSAGE);
                }   
            }           
        });
        
        updButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (calibration == null) {
                    JOptionPane.showMessageDialog(FormCalibration.this, getRes("error.updnullcalibration"), 
                                                  getRes("error"), JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Calibration c = getUpdCalibration();
                               
                if (controler.updCalibration(c) == 1) {
                    calibration = c;
                    controler.refreshData();
                    meterField.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(FormCalibration.this, getRes("error.updcalibration"), 
                                                  getRes("error"), JOptionPane.ERROR_MESSAGE);
                }
            }

            
        });
        
        delButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (calibration == null) {
                    JOptionPane.showMessageDialog(FormCalibration.this, getRes("error.delnullcalibration"), 
                                                  getRes("error"), JOptionPane.ERROR_MESSAGE);
                    return;
                }                
                if (JOptionPane.showConfirmDialog(FormCalibration.this, getRes("message.deletecalibration"),
                                                  getRes("message"), JOptionPane.YES_NO_OPTION)
                                                  == JOptionPane.YES_OPTION) {                          
                    if (controler.deleteCalibration(calibration) == 1) {
                        calibration = null;
                        controler.refreshData(); 
                        meterField.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(FormCalibration.this, getRes("error.delcalibration"), 
                                                   getRes("error"), JOptionPane.ERROR_MESSAGE);
                    }
                } 
            }
        });
                
        closeButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }

    // Setting data in form's fields from entity object
    private void setCalibration(Calibration c) {
        final Meter m = c.getMeter();
        meterField.setText(m.getNumber());
        typesComboBox.setSelectedItem(m.getTypesMeters());
        yearField.setText(Integer.toString(m.getYearProduce()));
        error0Field.setText(c.getError0().toString());
        error1Field.setText(c.getError1().toString());
        error2Field.setText(c.getError2().toString());
        error3Field.setText(c.getError3().toString());
        meterageStField.setText(c.getMeterageSt().toString());
        meterageEndField.setText(c.getMeterageEnd().toString());
        sealDSTUField.setText(c.getDstuSeal());
        numberDSTUField.setText(Integer.toString(c.getDstuNumber()));
    }
    
    //Getting Meter entity from form's fields
    private Meter getMeter() {
        Meter m = new Meter();
        try {
            TypeMeters tm = (TypeMeters) typesComboBox.getSelectedItem();                
            m.setTypesMeters(tm);
            m.setNumber(meterField.getText());
            m.setYearProduce(Integer.parseInt(yearField.getText())); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(FormCalibration.this, e.toString(), 
                                          getRes("error"), JOptionPane.ERROR_MESSAGE);
            return null;
        }    
        return m;
    }
    
    //Getting Calibration entity from form's fields
    private Calibration getCalibration() {
        Calibration c = new Calibration();
        try {                                                            
            c.setDates(new Date());
            c.setMeter(getMeter());
            c.setError0(new BigDecimal(error0Field.getText()));
            c.setError1(new BigDecimal(error1Field.getText()));
            c.setError2(new BigDecimal(error2Field.getText()));
            c.setError3(new BigDecimal(error3Field.getText()));
            c.setMeterageSt(new BigDecimal(meterageStField.getText()));
            c.setMeterageEnd(new BigDecimal(meterageEndField.getText()));
            c.setDstuNumber(Integer.parseInt(numberDSTUField.getText()));
            c.setDstuSeal(sealDSTUField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(FormCalibration.this, e.toString(), 
                                          getRes("error"), JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return c;
    }
    
    //Getting Calibration entity from form's fields and set ID from database;
    private Calibration getUpdCalibration() {
        Calibration c = getCalibration();
        c.setId(calibration.getId());
        c.getMeter().setId(calibration.getMeter().getId());        
        return c;        
    }
    
    private class Selecter implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            Component c = e.getComponent();
            if (c instanceof JTextField) {
                ((JTextField) c).selectAll();
            }            
        }

        @Override
        public void focusLost(FocusEvent e) { }
        
    }
}
