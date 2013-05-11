/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;
import ua.vynnyk.calibration.components.TypeMetersTableModel;
import ua.vynnyk.calibration.controler.Controler;
import ua.vynnyk.calibration.model.entity.TypeMeters;
import ua.vynnyk.l10n.TH;

/**
 *
 * @author Admin
 */
class FormTypeMeters extends JDialog {
    private static final int MAX_WIDTH_ID = 50;
    
    private final Controler controler; 
    private final List<TypeMeters> typeMeters;
    private final AbstractTableModel tableModel;
    
    private JTable table;
    private JScrollPane scrollTable;
    private JPanel panel;
    private JPanel editPanel;   
    private JButton addButton;
    private JButton updButton;
    private JButton delButton;
    private JButton closeButton;        

    public FormTypeMeters(Frame frame, String string, boolean bln, Controler controler) {
        super(frame, string, bln);        
        this.controler = controler;
        this.typeMeters = new ArrayList<>();
        this.tableModel = new TypeMetersTableModel(typeMeters);
        
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
    }
    
    private void getData() {     
        typeMeters.clear();
        typeMeters.addAll(controler.getTypeMeters()); 
        tableModel.fireTableDataChanged();                
    }
    
    private String getRes(String key) {
        return TH.getString(key);
    }
    
}
