/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package component;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import swingForm.ButtonOutLine;


public class PanelCover extends javax.swing.JPanel {

    private final DecimalFormat df=new DecimalFormat("##0.###");
    private ActionListener event;
    private MigLayout layout;
    private JLabel title;
    private JLabel description;
    private JLabel description1;
    private ButtonOutLine button;
    private boolean isLogin;
    
    public PanelCover() {
        initComponents();
        setOpaque(false);
        layout=new MigLayout("wrap, fill","[center]","push[]25[]10[]25[]push");
        setLayout(layout);
        init();
    }

    private void init(){
        title=new JLabel("Welcome to MiBo Santé!");
        title.setFont(new Font("sansserif",1,30));
        title.setForeground(new Color(245,245,245));
        add(title);
        description=new JLabel("Stay healthy with MiBo Santé");
        description.setForeground(new Color(245,245,245));
        add(description);
        description1=new JLabel("Please log in to access personalized health insights.");
        description1.setForeground(new Color(245,245,245));
        add(description1);
        button=new ButtonOutLine();
        button.setBackground(new Color(255,255,255));
        button.setForeground(new Color(255,255,255));
        button.setText("SIGN IN");
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                event.actionPerformed(e);
            }
        });
        add(button,"w 60%, h 40");
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs){
        Graphics2D g2=(Graphics2D)grphcs;
        GradientPaint gra=new GradientPaint(0,0,new Color(117,216,230),0,getHeight(),new Color(117,216,230));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }
    public void addEvent(ActionListener event){
        this.event=event;
    }
    
    public void registerLeft(double v){
        try {
            String formattedString = df.format(v).replace(',', '.');
            v = Double.valueOf(formattedString);
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException: " + e.getMessage());
        }
        login(false);
        layout.setComponentConstraints(title, "pad 0 -"+v+"% 0 0");
        layout.setComponentConstraints(description, "pad 0 -"+v+"% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -"+v+"% 0 0");
    }
    
    public void registerRight(double v){
        try {
            String formattedString = df.format(v).replace(',', '.');
            v = Double.valueOf(formattedString);
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException: " + e.getMessage());
        }
        login(false);
        layout.setComponentConstraints(title, "pad 0 -"+v+"% 0 0");
        layout.setComponentConstraints(description, "pad 0 -"+v+"% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -"+v+"% 0 0");
    }
    
    public void loginLeft(double v){
        try {
            String formattedString = df.format(v).replace(',', '.');
            v = Double.valueOf(formattedString);
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException: " + e.getMessage());
        }
        login(true);
        layout.setComponentConstraints(title, "pad 0 "+v+"% 0 "+v+"%");
        layout.setComponentConstraints(description, "pad 0 "+v+"% 0 "+v+"%");
        layout.setComponentConstraints(description1, "pad 0 "+v+"% 0 "+v+"%");
    }
    
    public void loginRight(double v){
        try {
            String formattedString = df.format(v).replace(',', '.');
            v = Double.valueOf(formattedString);
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException: " + e.getMessage());
        }
        login(true);
        layout.setComponentConstraints(title, "pad 0 "+v+"% 0 "+v+"%");
        layout.setComponentConstraints(description, "pad 0 "+v+"% 0 "+v+"%");
        layout.setComponentConstraints(description1, "pad 0 "+v+"% 0 "+v+"%");
    }
    
    private void login(boolean login){
        if(this.isLogin!=login){
            if(login){
                title.setText("Welcome to MiBo Santé!");
                description.setText("Unlock a healthier you!");
                description1.setText("Share your details, start your wellness journey");
                button.setText("SIGN UP");
            }else{
                title.setText("Welcome");
                description.setText("to keep connected with us please");
                description1.setText("login with your personal info");
                button.setText("SIGN IN");
            }
            this.isLogin=login;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
