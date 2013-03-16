/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration;

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
import ua.vynnyk.calibration.database.CalibrationToData;
import ua.vynnyk.calibration.database.MeterToData;
import ua.vynnyk.calibration.database.TypeMetersToData;
import ua.vynnyk.calibration.entity.Calibration;

/**
 *
 * @author vynnyk
 */
public class FormCalibration extends JDialog {
    private Frame frame;
    private CalibrationToData calibtationToData;
    private MeterToData meterToData;
    private TypeMetersToData typeMeters;
    private Calibration calibration;  
    private JButton addButton;
    private JButton updButton;
    private JButton delButton;
    private JButton closeButton;

    public void setCalibration(Calibration calibration) {
        this.calibration = calibration;
    }

    public FormCalibration(Frame frame, String string, boolean bln, Connection con) {
        super(frame, string, bln);
        this.frame = frame;
        calibtationToData = new CalibrationToData(con);
        meterToData = new MeterToData(con);
        typeMeters = new TypeMetersToData(con);
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabel meterLabel = new JLabel("Заводський №");
        JTextField meterField = new JTextField(20);
        JButton meterButton = new JButton("Знайти...");
        CompositeComponent componentMeter = new CompositeComponent(meterField, meterButton);
        
        JLabel typesLabel = new JLabel("Тип водоміра");
        JComboBox typesComboBox = new JComboBox(typeMeters.selects(null).toArray());
                
        JLabel error0Label = new JLabel("Початкова похибка");
        JTextField error0Field = new JTextField(20);
        
        JLabel error1Label = new JLabel("Похибка 1");
        JTextField error1Field = new JTextField(20);
        
        JLabel error2Label = new JLabel("Похибка 2");
        JTextField error2Field = new JTextField(20);
        
        JLabel error3Label = new JLabel("Похибка 3");
        JTextField error3Field = new JTextField(20);
        
        JLabel meterageStLabel = new JLabel("Початковий показник");
        JTextField meterageStField = new JTextField(20);
        
        JLabel meterageEndLabel = new JLabel("Кінцевий показник");
        JTextField meterageEndField = new JTextField(20);
        
        JLabel numberDSTULabel = new JLabel("Довідка ДСТУ");
        JTextField numberDSTUField = new JTextField(20);
        
        JLabel sealDSTULabel = new JLabel("Пломба ДСТУ");
        JTextField sealDSTUField = new JTextField(20);
        
        JPanel panel = new JPanel(new MigLayout());        
        panel.add(meterLabel, "align right");
        panel.add(componentMeter);
        panel.add(typesLabel, "align right");
        panel.add(typesComboBox, "growx, wrap");
        panel.add(error0Label, "align right");
        panel.add(error0Field);
        panel.add(error1Label, "align right");
        panel.add(error1Field, "wrap");
        panel.add(error2Label, "align right");
        panel.add(error2Field);
        panel.add(error3Label, "align right");
        panel.add(error3Field, "wrap");
        panel.add(meterageStLabel, "align right");
        panel.add(meterageStField);
        panel.add(meterageEndLabel, "align right");
        panel.add(meterageEndField, "wrap");
        panel.add(numberDSTULabel, "align right");
        panel.add(numberDSTUField);
        panel.add(sealDSTULabel, "align right");
        panel.add(sealDSTUField);        
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
