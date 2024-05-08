/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import swingForm.Button;
import swingForm.MyPasswordField;
import swingForm.MyTextField;

public class Form_1 extends javax.swing.JPanel {

    /**
     * Creates new form Form_1
     */
    public Form_1() {
        initComponents();
        initRegister();
        Panel.setVisible(true);
    }
private void initRegister(){
        Panel.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]25[]push"));
        JLabel label=new JLabel("SLEEP TRACK");
        label.setFont(new Font("sansserif",1,30));
        label.setForeground(new Color(0, 0, 128));
        Panel.add(label);
        MyTextField TextDate=new MyTextField();
//        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        TextDate.setHint("Date");
        Panel.add(TextDate, "w 60%");
        MyTextField txtBed=new MyTextField();
//        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
        txtBed.setHint("I want to go to bed at");
        Panel.add(txtBed, "w 60%");
        MyPasswordField txtWake=new MyPasswordField();
//        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtWake.setHint("I want to get up at");
        Panel.add(txtWake, "w 60%");
        Button cmd=new Button();
        cmd.setBackground(new Color(0, 0, 128));
        cmd.setForeground(new Color(250,250,250));
        cmd.setText("SAVE");
        Panel.add(cmd,"w 40%, h 40");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    // End of variables declaration//GEN-END:variables
}
