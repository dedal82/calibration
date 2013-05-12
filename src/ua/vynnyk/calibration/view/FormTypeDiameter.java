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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;
import ua.vynnyk.calibration.components.Focus;
import ua.vynnyk.calibration.components.TypeDiematersTableModel;
import ua.vynnyk.calibration.controler.Controler;
import ua.vynnyk.calibration.model.entity.Calibration;
import ua.vynnyk.calibration.model.entity.Flow;
import ua.vynnyk.calibration.model.entity.Meter;
import ua.vynnyk.l10n.TH;

/**
 *
 * @author Admin
 */
class FormTypeDiameter  extends JDialog {
    private static final int MAX_WIDTH_ID = 50;
    
    private final Controler controler; 
    private final List<Flow> flows;
    private final AbstractTableModel tableModel;
    
    private JTable table;
    private JScrollPane scrollTable;
    private JPanel panel;
    private JPanel editPanel;   
    private JButton addButton;
    private JButton updButton;
    private JButton delButton;
    private JButton closeButton;        

    public FormTypeDiameter(Frame frame, String string, boolean bln, Controler controler) {
        super(frame, string, bln);        
        this.controler = controler;
        this.flows = new ArrayList<>();
        this.tableModel = new TypeDiematersTableModel(flows);
        
        initComponents();
        setLocationRelativeTo(frame);
        
        getData();
    }

    private void initComponents() {        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                
        table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(MAX_WIDTH_ID);
        scrollTable = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        
        editPanel = new JPanel();
               
        Dimension btnDimension = new Dimension(95, 28);
        addButton = new JButton(getRes("add"));
        addButton.setPreferredSize(btnDimension);
        updButton = new JButton(getRes("edit"));
        updButton.setPreferredSize(btnDimension);
        delButton = new JButton(getRes("delete"));
        delButton.setPreferredSize(btnDimension);
        closeButton = new JButton(getRes("close"));
        closeButton.setPreferredSize(btnDimension);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(addButton);
        buttonPanel.add(updButton);
        buttonPanel.add(delButton);
        buttonPanel.add(closeButton);
        
        panel = new JPanel(new BorderLayout());
        panel.add(editPanel);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(panel, BorderLayout.NORTH);
        add(scrollTable);
                
        pack();
        
        addListeners(); 
        
        Focus.setFocusNexComponentKey(editPanel);
    }
    
    private void getData() {     
        flows.clear();
        flows.addAll(controler.getFlows()); 
        tableModel.fireTableDataChanged();                
    }
    
    private String getRes(String key) {
        return TH.getString(key);
    }

    private void addListeners() {
//        addButton.addActionListener(new ActionListener() {
//            
//            @Override
//            public void actionPerformed(ActionEvent ae) {               
//                Calibration c = getCalibration();
//                Meter m = c.getMeter();
//                
//                m.setId(controler.addMeter(m));
//                c.setId(controler.addCalibration(c)); 
//                
//                if (c.getId() > 0) { 
//                    calibration = c;
//                    controler.refreshData();
//                    meterField.requestFocus();
//                } else {
//                    JOptionPane.showMessageDialog(FormCalibration.this, getRes("error.addcalibration"), 
//                                                  getRes("error"), JOptionPane.ERROR_MESSAGE);
//                }   
//            }           
//        });
//        
//        updButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (calibration == null) {
//                    JOptionPane.showMessageDialog(FormCalibration.this, getRes("error.updnullcalibration"), 
//                                                  getRes("error"), JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                
//                Calibration c = getUpdCalibration();
//                               
//                if (controler.updCalibration(c) == 1) {
//                    calibration = c;
//                    controler.refreshData();
//                    meterField.requestFocus();
//                } else {
//                    JOptionPane.showMessageDialog(FormCalibration.this, getRes("error.updcalibration"), 
//                                                  getRes("error"), JOptionPane.ERROR_MESSAGE);
//                }
//            }
//
//            
//        });
//        
//        delButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (calibration == null) {
//                    JOptionPane.showMessageDialog(FormCalibration.this, getRes("error.delnullcalibration"), 
//                                                  getRes("error"), JOptionPane.ERROR_MESSAGE);
//                    return;
//                }                
//                if (JOptionPane.showConfirmDialog(FormCalibration.this, getRes("message.deletecalibration"),
//                                                  getRes("message"), JOptionPane.YES_NO_OPTION)
//                                                  == JOptionPane.YES_OPTION) {                          
//                    if (controler.deleteCalibration(calibration) == 1) {
//                        calibration = null;
//                        controler.refreshData(); 
//                        meterField.requestFocus();
//                    } else {
//                        JOptionPane.showMessageDialog(FormCalibration.this, getRes("error.delcalibration"), 
//                                                   getRes("error"), JOptionPane.ERROR_MESSAGE);
//                    }
//                } 
//            }
//        });
                
        closeButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }
    
}

