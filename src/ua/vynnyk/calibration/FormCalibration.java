/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import ua.vynnyk.calibration.database.CalibrationToData;
import ua.vynnyk.calibration.database.MeterToData;
import ua.vynnyk.calibration.entity.Calibration;

/**
 *
 * @author vynnyk
 */
public class FormCalibration extends JDialog {
    private Frame frame;
    private CalibrationToData calibtationToData;
    private MeterToData meterToData;
    private Calibration calibration;              

    public void setCalibration(Calibration calibration) {
        this.calibration = calibration;
    }

    public FormCalibration(Frame frame, String string, boolean bln, Connection con) {
        super(frame, string, bln);
        this.frame = frame;
        calibtationToData = new CalibrationToData(con);
        meterToData = new MeterToData(con);
        initComponents();
    }

    private void initComponents() {
        JLabel idLabel = new JLabel("№");
        JTextField idField = new JTextField(20);
        idField.setEditable(false);
        
        JLabel meterLabel = new JLabel("Водомір");
        JTextField meterField = new JTextField(20);
        
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
        panel.add(idLabel, "align right");
        panel.add(idField);
        panel.add(meterLabel, "align right");
        panel.add(meterField, "wrap");
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
        JButton addButton = new JButton("Добавити");
        addButton.setPreferredSize(btnDimension);
        JButton updButton = new JButton("Змінити");
        updButton.setPreferredSize(btnDimension);
        JButton delButton = new JButton("Видалити");
        delButton.setPreferredSize(btnDimension);
        JButton closeButton = new JButton("Закрити");
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
    } 
}
