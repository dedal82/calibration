/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.Action.SHORT_DESCRIPTION;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import ua.vynnyk.calibration.database.CalibrationToData;
import ua.vynnyk.calibration.entity.Calibration;
import ua.vynnyk.calibration.treedates.Node;
import ua.vynnyk.calibration.treedates.OpenDateInterface;
import ua.vynnyk.calibration.treedates.TreeDates;

/**
 *
 * @author vynnyk
 */
public class FormMain extends JFrame {    
    
    public FormMain() {
        con = openConnection();
        calibrationToData = new CalibrationToData(con);
        initComponents();
    }
    
    
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormMain().setVisible(true);
            }
        });
    }    

    private void initComponents() {
        
        setLookAndFeel();
        
       //Actions
       Action exitAction = new AbstractAction("Вихід") {{
            putValue(SHORT_DESCRIPTION, "Завершення роботи програми");    
            }
            @Override
            public void actionPerformed(ActionEvent ae) {
                closeConnection(con);
                System.exit(0);
            }
        };
       
       Action addCalibrationAction = new AbstractAction("Добавити") {{
            putValue(SHORT_DESCRIPTION, "Внесення інформації про повірку");    
            }
            @Override
            public void actionPerformed(ActionEvent ae) {
                new FormCalibration(FormMain.this, "Повірка", true, con).setVisible(true);
            }
        };
       
       Action refreshAction = new AbstractAction("Оновити") {{
            putValue(SHORT_DESCRIPTION, "Оновлення інформації з бази даних");    
            }
            @Override
            public void actionPerformed(ActionEvent ae) {
                ((Node) tree.getLastSelectedPathComponent()).open();
            }
        };
       
       //Actions
       
        //меню 
       menuCalibration = new JMenu("Повірка");
       menuReport = new JMenu("Звіти");
       menuHelp = new JMenu("Допомога");
       
       menuCalibration.add(new JMenuItem(addCalibrationAction));
       menuCalibration.add(new JMenuItem("Змінити"));
       menuCalibration.add(new JMenuItem("Видалити"));
       menuCalibration.add(new JMenuItem("Оновити"));
       menuCalibration.addSeparator();
       menuCalibration.add(exitAction);
       
       menuBar = new JMenuBar();
       menuBar.add(menuCalibration);
       menuBar.add(menuReport);
       menuBar.add(menuHelp);
       //меню
        
       // дерево 
       Calendar tmpDate = Calendar.getInstance();
       tmpDate.set(2012, 0, 1); 
       
       tree = new TreeDates(tmpDate.getTime(), new OpenDateInterface() {
            @Override
            public void openDate(Date date) {
                refreshData(date);
            }
        });
       
       tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
       ((Node) tree.getModel().getRoot()).open();
       ((DefaultTreeModel) tree.getModel()).reload();
       tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                treeDatesValueChanged(tse);    
            }            
        });              
       scrollTree = new JScrollPane(tree);             
       
       //грід
       table = new JTable(new CalibrationTableModel(new ArrayList<Calibration>()));
       scrollTable = new JScrollPane(table);
       table.setFillsViewportHeight(true);
       table.setAutoCreateRowSorter(true);
       //грід
       
       splitPane = new JSplitPane();       
       splitPane.setDividerLocation(170);
       splitPane.setLeftComponent(scrollTree);
       splitPane.setRightComponent(scrollTable);       
       //дерево і грід
       
       // панель кнопок
       btnDimension = new Dimension(95, 28);
       addButton = new JButton(addCalibrationAction);
       addButton.setPreferredSize(btnDimension);
       updButton = new JButton("Змінити");
       updButton.setPreferredSize(btnDimension);
       delButton = new JButton("Видалити");               
       delButton.setPreferredSize(btnDimension);
       buttonPanel = new JPanel();       
       buttonPanel.setPreferredSize(new Dimension(103,0));
       buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
              
       buttonPanel.add(addButton);
       buttonPanel.add(updButton);
       buttonPanel.add(delButton);
       
       // панель кнопок
       
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
       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
       setPreferredSize(new Dimension(1024, 750));       
       setJMenuBar(menuBar);
       add(splitPane, BorderLayout.CENTER);
       add(buttonPanel, BorderLayout.EAST);
       add(statusPanel, BorderLayout.SOUTH);
       //
       pack();
       setLocationRelativeTo(null);
    }
    
    private void treeDatesValueChanged(TreeSelectionEvent tse) {
        Node node = (Node) tree.getLastSelectedPathComponent();
        if (node == null) {
            return;
        }
        node.open(); 
    }
    
    private Connection openConnection() {        
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            return DriverManager.getConnection("jdbc:hsqldb:file:./data/calibration", "sa", "");            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
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
    
    private void refreshData(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calibrations = calibrationToData.selects("where dates = \'" + sdf.format(date) + "\'");        
    }
    
    public Connection getConnection() {
        return con;
    }
    
    private Connection con;
    
    private CalibrationToData calibrationToData;
    private List<Calibration> calibrations;
    
    private Dimension btnDimension;
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private JSplitPane splitPane;
    private JScrollPane scrollTree;
    private JTree tree;
    private JScrollPane scrollTable;
    private JTable table;
    private JPanel buttonPanel;
    private JButton addButton;
    private JButton updButton;
    private JButton delButton;
    private JPanel statusPanel;
    private JLabel status1;
    private JLabel status2;
    private JMenu menuCalibration;
    private JMenu menuReport;
    private JMenu menuHelp;     
}
