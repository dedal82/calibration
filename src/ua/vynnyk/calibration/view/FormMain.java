/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;
import ua.vynnyk.calibration.components.CalibrationTableModel;
import ua.vynnyk.calibration.components.treedates.Node;
import ua.vynnyk.calibration.components.treedates.OpenDateInterface;
import ua.vynnyk.calibration.components.treedates.TreeDates;
import ua.vynnyk.calibration.controler.Controler;
import ua.vynnyk.calibration.model.entity.Calibration;
import ua.vynnyk.l10n.TH;

/**
 *
 * @author vynnyk
 */
public class FormMain extends JFrame implements View {   
    private static final int MAX_WIDTH_ID = 50;
    
    private final Controler controler;
    private final Actions actions;    
    private final List<Calibration> calibrations;
    private final AbstractTableModel tableModel;
    
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private JSplitPane splitPane;
    private JScrollPane scrollTree;
    private JTree tree;
    private JScrollPane scrollTable;
    private JTable table;
    private JPanel statusPanel;
    private JLabel status1;
    private JLabel status2;
    private JMenu menuCalibration;
    private JMenu menuReport;
    private JMenu menuHelp;     
    private JMenu menuDictionaries;
    
    public FormMain(Controler controler) {        
        this.controler = controler;        
        calibrations = new ArrayList();
        tableModel = new CalibrationTableModel(calibrations);
        actions = new Actions(controler);
               
        initComponents();
    }         

    private void initComponents() {
        
        setLookAndFeel();
        
        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
        
        setTitle(getRes("title"));
        //меню 
        menuCalibration = new JMenu(getRes("menu.calibration"));
        menuDictionaries = new JMenu(getRes("menu.dictionaries"));
        menuReport = new JMenu(getRes("menu.report"));
        menuHelp = new JMenu(getRes("menu.help"));
       
        menuCalibration.add(actions.getAction(Act.ADD_CALIBRATION));
        menuCalibration.add(actions.getAction(Act.EDIT_CALIBRATION));
        menuCalibration.add(actions.getAction(Act.DELETE_CALIBRATION));
        menuCalibration.add(actions.getAction(Act.REFRESH));
        menuCalibration.addSeparator();
        menuCalibration.add(actions.getAction(Act.EXIT));
        
        menuDictionaries.add(actions.getAction(Act.TYPE_DIAMETERS));
        menuDictionaries.add(actions.getAction(Act.TYPE_METERS));
        
        menuHelp.add(actions.getAction(Act.ABOUT));
        
        menuBar = new JMenuBar();
        menuBar.add(menuCalibration);
        menuBar.add(menuDictionaries);
        menuBar.add(menuReport);
        menuBar.add(menuHelp);
        //меню
        
        //toolbar
        toolBar = new JToolBar();
        toolBar.add(new JButton(actions.getAction(Act.ADD_CALIBRATION)));
        toolBar.add(new JButton(actions.getAction(Act.EDIT_CALIBRATION)));
        toolBar.add(new JButton(actions.getAction(Act.DELETE_CALIBRATION)));
        toolBar.add(new JButton(actions.getAction(Act.REFRESH)));
        toolBar.addSeparator();
        
        toolBar.add(new JButton(actions.getAction(Act.TYPE_DIAMETERS)));
        toolBar.add(new JButton(actions.getAction(Act.TYPE_METERS)));
        toolBar.addSeparator();
        
        toolBar.add(new JButton(actions.getAction(Act.EXIT)));       
        setFocusableFalse(toolBar);
        //toolbar              
                      
        // дерево 
        Calendar tmpDate = Calendar.getInstance();
        tmpDate.set(2013, 4, 1); 
       
        tree = new TreeDates(tmpDate.getTime(), new OpenDateInterface() {
            @Override
            public void openDate(Date date) {
                refreshData(date);
            }
        });
                                  
        scrollTree = new JScrollPane(tree);             
       
        //грід       
        table = new JTable(tableModel);
        scrollTable = new JScrollPane(table);
        table.getColumnModel().getColumn(0).setPreferredWidth(MAX_WIDTH_ID);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    controler.editCalibrationAct();
                }
            }            
        });
        //грід
       
        splitPane = new JSplitPane();       
        splitPane.setDividerLocation(170);
        splitPane.setLeftComponent(scrollTree);
        splitPane.setRightComponent(scrollTable);       
        //дерево і грід
       
        //панель статусу
        status1 = new JLabel(getRes("count_calibrations"));
        status1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        status2 = new JLabel("");
        status2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        statusPanel = new JPanel();       
        statusPanel.setLayout(new GridLayout(1, 5, 3, 3));      
        statusPanel.add(status1);
        statusPanel.add(status2);
        //панель статусу
       
        //головна форма
        addWindowListener(new WinListener());
       
        setPreferredSize(new Dimension(1024, 750));       
        setJMenuBar(menuBar);        
        add(toolBar, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
        //
        pack();
        setLocationRelativeTo(null);
    }
    
            
    private void refreshData(Date date) {     
        calibrations.clear();
        calibrations.addAll(controler.getCalibrations(date)); 
        tableModel.fireTableDataChanged();        
        status1.setText(getRes("count_calibrations") + Integer.toString(calibrations.size()));
    }

    @Override
    public void refreshData() { 
        final Node node = (Node) tree.getLastSelectedPathComponent();
        if (node != null) {
            node.open();
        }        
    }

    @Override
    public void addCalibration() {                
        new FormCalibration(this , getRes("calibration.title"), true, controler).setVisible(true);
    }

    @Override
    public void editCalibration() {
        int index = table.getSelectedRow();
        if (index != -1) {
            index = table.convertRowIndexToModel(index);
            Calibration c = calibrations.get(index);
            new FormCalibration(this , getRes("calibration.title"), true, controler, c).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, getRes("message.selectcalibration"), 
                                          getRes("message"), JOptionPane.INFORMATION_MESSAGE);
        }       
    }

    @Override
    public void deleteCalibration() {                   
        int index[] = table.getSelectedRows();
        if ((index.length != 0) && (JOptionPane.showConfirmDialog(this, 
                                          String.format(getRes("message.deletecalibrations"), index.length),
                                          getRes("message"), JOptionPane.YES_NO_OPTION)
                                          == JOptionPane.YES_OPTION)) {              
            for (int i = 0; i < index.length; i++) {
                int dataIndex = table.convertRowIndexToModel(index[i]);
                Calibration c = calibrations.get(dataIndex);
                if (controler.deleteCalibration(c) != 1) {                                    
                    JOptionPane.showMessageDialog(this, getRes("error.delcalibration"), 
                                                  getRes("error"), JOptionPane.ERROR_MESSAGE);
                    break;
                }                    
            }            
            controler.refreshData();
        }          
    }

    private void setFocusableFalse(JToolBar toolBar) {
        for (int i = 0; i < toolBar.getComponentCount(); i++) {
            toolBar.getComponent(i).setFocusable(false);                    
        }
    }

    @Override
    public void typeMetersDictionary() {
        new FormTypeMeters(this , getRes("type_meters.title"), true, controler).setVisible(true);
    }
    
    @Override
    public void typeDiameters() {
        new FormTypeDiameter(this , getRes("type_diameters.title"), true, controler).setVisible(true);
    }

    @Override
    public void showAbout() {
        new FormAbout(this, true).setVisible(true);
    }
    
    private class WinListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent we) {
            controler.exit();
        }
           
    } 
    
    private String getRes(String key) {
        return TH.getString(key);
    }
    
    private static void setLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
