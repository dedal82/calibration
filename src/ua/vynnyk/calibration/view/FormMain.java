/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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
    private Controler controler;
    private Actions actions;
    
    private final List<Calibration> calibrations;
    private AbstractTableModel tableModel;
    
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
    
    public FormMain(Controler controler) {        
        this.controler = controler;        
        calibrations = new ArrayList(); 
        actions = new Actions(controler);
               
        initComponents();
    }         

    private void initComponents() {
        
        setLookAndFeel();
        
       //меню 
       menuCalibration = new JMenu(getRes("menu.calibration"));
       menuReport = new JMenu(getRes("menu.report"));
       menuHelp = new JMenu(getRes("menu.help"));
       
       menuCalibration.add(actions.getAction(Act.ADD_CALIBRATION));
       menuCalibration.add(actions.getAction(Act.EDIT_CALIBRATION));
       menuCalibration.add(actions.getAction(Act.DELETE_CALIBRATION));
       menuCalibration.add(actions.getAction(Act.REFRESH));
       menuCalibration.addSeparator();
       menuCalibration.add(actions.getAction(Act.EXIT));
       
       menuBar = new JMenuBar();
       menuBar.add(menuCalibration);
       menuBar.add(menuReport);
       menuBar.add(menuHelp);
       //меню
       
       //toolbar
       toolBar = new JToolBar();
       toolBar.add(new JButton(actions.getAction(Act.ADD_CALIBRATION)));
       toolBar.add(new JButton(actions.getAction(Act.EDIT_CALIBRATION)));
       toolBar.add(new JButton(actions.getAction(Act.DELETE_CALIBRATION)));
       toolBar.add(new JButton(actions.getAction(Act.REFRESH)));
       toolBar.add(new JButton(actions.getAction(Act.EXIT)));
       //toolbar              
                      
       // дерево 
       Calendar tmpDate = Calendar.getInstance();
       tmpDate.set(2012, 0, 1); 
       
       tree = new TreeDates(tmpDate.getTime(), new OpenDateInterface() {
            @Override
            public void openDate(Date date) {
                refreshData(date);
            }
        });
                                  
       scrollTree = new JScrollPane(tree);             
       
       //грід
       tableModel = new CalibrationTableModel(calibrations);
       table = new JTable(tableModel);
       scrollTable = new JScrollPane(table);
       table.setFillsViewportHeight(true);
       table.setAutoCreateRowSorter(true);
       //грід
       
       splitPane = new JSplitPane();       
       splitPane.setDividerLocation(170);
       splitPane.setLeftComponent(scrollTree);
       splitPane.setRightComponent(scrollTable);       
       //дерево і грід
       
       //панель статусу
       status1 = new JLabel("Status 1");
       status1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
       //status1.setPreferredSize(new Dimension(150, 22));
       status2 = new JLabel("Status 2");
       status2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
       //status2.setPreferredSize(new Dimension(150, 22));
       statusPanel = new JPanel();
       //statusPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));       
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
