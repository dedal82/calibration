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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import net.miginfocom.swing.MigLayout;
import ua.vynnyk.calibration.components.Focus;
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
    private static final int FIELD_WIDTH = 20;
    private static final String[] PRECISIONS = {"A", "B", "C", "D", "E"};
    
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
    private JTextField typeField;
    private JTextField diameterField;
    private JTextField cycleField;
    private JComboBox precisionCombo;
    private JLabel typeLabel;
    private JLabel diameterLabel;
    private JLabel precisionLabel;
    private JLabel cycleLabel;

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
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        typeLabel = new JLabel(getRes("type_metters.type"));
        typeField = new JTextField(FIELD_WIDTH);
        
        diameterLabel = new JLabel(getRes("type_metters.diameter"));
        diameterField = new JTextField(FIELD_WIDTH);
        
        precisionLabel = new JLabel(getRes("type_metters.precision"));
        precisionCombo = new JComboBox(PRECISIONS);
        
        cycleLabel = new JLabel(getRes("type_metters.cycle"));
        cycleField = new JTextField(FIELD_WIDTH);
        
        editPanel = new JPanel(new MigLayout("wrap 4","[align right][][align right][]",""));
        editPanel.add(typeLabel);
        editPanel.add(typeField, "grow");
        editPanel.add(diameterLabel);
        editPanel.add(diameterField, "grow");
        editPanel.add(precisionLabel);
        editPanel.add(precisionCombo, "grow");
        editPanel.add(cycleLabel);
        editPanel.add(cycleField, "grow");
               
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
        typeMeters.clear();
        typeMeters.addAll(controler.getTypeMeters()); 
        tableModel.fireTableDataChanged();                
    }
    
    private void setFields() {
        int index = table.getSelectedRow();
        if (index != -1) {
            index = table.convertRowIndexToModel(index);
            TypeMeters tm = typeMeters.get(index);

            typeField.setText(tm.getName());
            diameterField.setText(Integer.toString(tm.getDiameter()));
            precisionCombo.setSelectedItem(tm.getPrecisions());
            cycleField.setText(Integer.toString(tm.getCycle()));
        }
    }
    
    private String getRes(String key) {
        return TH.getString(key);
    }
        
    private TypeMeters getTypeMeters() {
        TypeMeters tm = new TypeMeters();
        try {            
            tm.setName(typeField.getText());
            tm.setDiameter(Integer.parseInt(diameterField.getText()));
            tm.setPrecisions(precisionCombo.getSelectedItem().toString());
            tm.setCycle(Integer.parseInt(cycleField.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(FormTypeMeters.this, e.toString(), 
                                          getRes("error"), JOptionPane.ERROR_MESSAGE);
            return null;
        }    
        return tm;    
    }
    
    private TypeMeters getUpdTypeMeters() {
        TypeMeters tm = getTypeMeters();
        int index = table.getSelectedRow();
        index = table.convertRowIndexToModel(index);
        tm.setId(typeMeters.get(index).getId());
        return tm;    
    }

    private void addListeners() {
        addButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {               
                TypeMeters tm = getTypeMeters();
                tm.setId(controler.addTypeMeters(tm));                                
                if (tm.getId() > 0) {                     
                    getData();
                    typeField.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(FormTypeMeters.this, getRes("error.addtypemeters"), 
                                                  getRes("error"), JOptionPane.ERROR_MESSAGE);
                }   
            }           
        });
        
        updButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(FormTypeMeters.this, getRes("error.updnulltypemeters"), 
                                                  getRes("error"), JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                TypeMeters tm = getUpdTypeMeters();
                               
                if (controler.editTypeMeters(tm) == 1) {
                    getData();                    
                    typeField.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(FormTypeMeters.this, getRes("error.updtypemeters"), 
                                                  getRes("error"), JOptionPane.ERROR_MESSAGE);
                }
            }

            
        });
        
        delButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(FormTypeMeters.this, getRes("error.delnulltypemeters"), 
                                                  getRes("error"), JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (JOptionPane.showConfirmDialog(FormTypeMeters.this, getRes("message.deletetypemeters"),
                                                  getRes("message"), JOptionPane.YES_NO_OPTION)
                                                  == JOptionPane.YES_OPTION) {                          
                    TypeMeters tm = getUpdTypeMeters();
                    if (controler.deleteTypeMeters(tm) == 1) {
                        getData();
                        typeField.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(FormTypeMeters.this, getRes("error.deltypemeters"), 
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
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                setFields();
            }
        });
    }
    
}
