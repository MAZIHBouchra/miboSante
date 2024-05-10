/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.raven.swing.ScrollBar;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import swingDesign.Button;
import swingDesign.MyTextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 *
 * @author RAVEN
 */
public class Form_2 extends javax.swing.JPanel {
    
    private DefaultTableModel tableModel;

    /**
     * Creates new form Form_1
     */
    public Form_2() {
        initComponents();
        initRegister();
        loadActivityData();
        Panel.setVisible(true);
    }
    
        // Méthode pour initialiser le formulaire d'enregistrement
    private void initRegister() {
        Panel.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("PHYSICAL ACTIVITY TRACK");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(0, 0, 128));
        Panel.add(label);
        MyTextField TextDate = new MyTextField();
        TextDate.setHint("Date");
        Panel.add(TextDate, "w 60%");
        MyTextField txtType = new MyTextField();
        txtType.setHint("Type");
        Panel.add(txtType, "w 60%");
        MyTextField txtDuree = new MyTextField();
        txtDuree.setHint("duration of physical activity");
        Panel.add(txtDuree, "w 60%");
        
        Button cmd = new Button();
        cmd.setBackground(new Color(0, 0, 128));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SAVE");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = TextDate.getText();
                String type = txtType.getText();
                String duree = txtDuree.getText();
                
                saveActivityData(date, type, duree);
                loadActivityData();
            }
        });
        Panel.add(cmd, "w 40%, h 40");

        // Table to display activity data
        
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    }
    
    /*private void loadActivityData() {
        tableModel.setRowCount(0); // Clear table
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miboSanté", "root", "");
            String sql = "SELECT * FROM ActivitePhysique";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString("date"));
                row.add(rs.getString("type"));
                row.add(rs.getString("duree"));
                tableModel.addRow(row);
            }
            //rs.close();
            //statement.close();
            //connection.close();
        } catch (SQLException ex) {
            System.out.println("Error loading activity data: " + ex.getMessage());
        }
    }*/
    
    private void loadActivityData() {
    // Effacez les données actuelles de la table
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0);
    
    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miboSanté", "root", "");
        String sql = "SELECT * FROM ActivitePhysique";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            // Récupérez les données de la base de données
            String date = rs.getString("date");
            String type = rs.getString("type");
            String duree = rs.getString("duree");
            
            // Créez une ligne pour contenir les données et les boutons
            Vector<Object> row = new Vector<>();
            row.add(date);
            row.add(type);
            row.add(duree);
            
            // Créez deux boutons pour la colonne "Action"
            JButton buttonModifier = new JButton("Modifier");
            JButton buttonSupprimer = new JButton("Supprimer");
            
            // Ajoutez les boutons à la ligne
            row.add(buttonModifier);
            row.add(buttonSupprimer);
            
            // Ajoutez la ligne au modèle de table
            model.addRow(row);
        }
        // Utilisez le rendu personnalisé pour les boutons dans la colonne "Action"
        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        
        // Fermez les ressources
        rs.close();
        statement.close();
        connection.close();
    } catch (SQLException ex) {
        System.out.println("Erreur lors du chargement des données de l'activité physique : " + ex.getMessage());
    }
}


// Méthode pour enregistrer les données dans la base de données
  private void saveActivityData(String date, String type, String duree) {
    try {
        // Établir une connexion à la base de données
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miboSanté", "root", "");
        
        // Préparer la requête SQL d'insertion
        String sql = "INSERT INTO ActivitePhysique (date, type, duree) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, date);
        statement.setString(2, type);
        statement.setString(3, duree);
        
        // Exécuter la requête SQL
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Les données de l'activité physique ont été insérées avec succès !");
        }
        
        // Fermer la connexion et la déclaration
        //statement.close();
        //connection.close();
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'insertion des données de l'activité physique : " + ex.getMessage());
    }
  }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.raven.swing.Table();

        Panel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 242, Short.MAX_VALUE)
        );

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Activity Summary");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Type", "Duration", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(spTable))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
