package com.raven.form;
import java.awt.*;
import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;
import net.miginfocom.swing.MigLayout;
import raven.datetime.component.time.TimeSelectionListener;
import swingForm.Button;
import swingForm.MyPasswordField;
import swingForm.MyTextField;
import Fromulaire.DatePickerDesign;
import com.raven.swing.TimPicker.TimePicker ;

public class Form_1 extends javax.swing.JPanel {
    private javax.swing.JPanel jPanel7;
    private raven.datetime.component.date.DatePicker datePicker;

    private javax.swing.JPanel Panel;
    private javax.swing.JPanel Panel2;
    private javax.swing.JPanel Panel3;

private TimeSelectionListener listner ;

    public Form_1() {
        initComponents();
        initRegister();
        Panel.setVisible(true);
    }

    private void initRegister(){
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("Failed to load look and feel: " + e.getMessage());
        }

        Panel.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]25[]push"));
        JLabel label=new JLabel("SLEEP TRACK");
        label.setFont(new Font("sansserif",1,30));
        label.setForeground(new Color(0, 0, 128));
        Panel.add(label);
        JLabel datesleep=new JLabel();
        datesleep.setText("add date to sleep:");
        datesleep.setFont(new Font("sansserif",1,18));
        datesleep.setForeground(new Color(0, 0, 128));
        Panel.add(datesleep);
        jPanel7 = new javax.swing.JPanel();
        jPanel7.setLayout(new MigLayout());
        jPanel7.setBackground(Color.white);
        Panel.add(jPanel7, "w 60%");

        JFormattedTextField editor2 = new JFormattedTextField();
        editor2.setFont(new Font("SansSerif", Font.PLAIN, 16));
        editor2.setForeground(new Color(51, 51, 51));
        editor2.setBackground(new Color(255,255,255));
        datePicker = new raven.datetime.component.date.DatePicker();



        editor2.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        editor2.setBorder(BorderFactory.createCompoundBorder(
                editor2.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        datePicker.setEditor(editor2);
        DatePickerDesign.styleDatePicker(datePicker);
        jPanel7.setLayout(new MigLayout());
        jPanel7.add(editor2, "width 550");
        jPanel7.setBackground(Color.white);
        Panel.add(jPanel7, "w 60%");

        Panel3 = new javax.swing.JPanel();
        Panel3.setLayout(new MigLayout());
        Panel3.setBackground(Color.white);
        JLabel txtBed=new JLabel();
        txtBed.setFont(new Font("sansserif",1,18));
        txtBed.setForeground(new Color(0, 0, 128));
        txtBed.setText("I want to go to bed at:                ");
        Panel3.add(txtBed);

        JLabel txtWake=new JLabel();
        txtWake.setText("I want to get up at:");
        txtWake.setFont(new Font("sansserif",1,18));
        txtWake.setForeground(new Color(0, 0, 128));
        Panel3.add(txtWake);

        Panel.add(Panel3, "w 20%");

        TimePicker timePicker = new TimePicker();
        TimePicker timePicker2 = new TimePicker();
        Panel2 = new javax.swing.JPanel();
        Panel2.setLayout(new MigLayout());
        Panel2.setBackground(Color.white);
        Panel2.add(timePicker, "gapright 25");
        Panel2.add(timePicker2);
        Panel.add(Panel2, "w 55%");

        Button cmd=new Button();
        cmd.setBackground(new Color(0, 0, 128));
        cmd.setForeground(new Color(250,250,250));
        cmd.setText("SAVE");
        Panel.add(cmd,"w 40%, h 20");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        Panel = new javax.swing.JPanel();
        Panel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
                PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 732, Short.MAX_VALUE)
        );
        PanelLayout.setVerticalGroup(
                PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 509, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
}