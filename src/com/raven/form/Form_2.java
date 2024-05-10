/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.raven.model.StatusType;
import com.raven.swing.ScrollBar;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JPanel;
import swingDesign.Button;
import swingDesign.MyTextField;


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
                
                // Vider les champs de texte après la sauvegarde
                TextDate.setText("");
                txtType.setText("");
                txtDuree.setText("");
            }
        });
        Panel.add(cmd, "w 20%, h 20");
        Button Update = new Button();
        Update.setBackground(new Color(0, 0, 128));
        Update.setForeground(new Color(250, 250, 250));
        Update.setText("Update");

       Update.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtenez les nouvelles valeurs des champs de texte
        String newDate = TextDate.getText();
        String newType = txtType.getText();
        String newDuree = txtDuree.getText();
        
        // Obtenez l'index de la ligne sélectionnée
        int selectedRow = table.getSelectedRow();
        
        // Vérifiez si une ligne est sélectionnée
        if (selectedRow != -1) {
            // Obtenez les anciennes valeurs des champs de la ligne sélectionnée
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String date = (String) model.getValueAt(selectedRow, 0);
            String type = (String) model.getValueAt(selectedRow, 1);
            String duree = (String) model.getValueAt(selectedRow, 2);
            
            // Mettez à jour les données dans la base de données avec les nouvelles valeurs
            //updateActivityData(date, type, duree, newDate, newType, newDuree);
            
            // Mettez à jour les valeurs dans le modèle de table avec les nouvelles valeurs
            model.setValueAt(newDate, selectedRow, 0);
            model.setValueAt(newType, selectedRow, 1);
            model.setValueAt(newDuree, selectedRow, 2);
            
            // Réinitialisez les champs de texte
            TextDate.setText("");
            txtType.setText("");
            txtDuree.setText("");
            
            // Activez à nouveau le bouton "Save" et désactivez le bouton "Update"
            cmd.setEnabled(true);
            Update.setEnabled(false);
        }
    }
});

      Panel.add(Update, "w 20%, h 20");
      Button  Delete = new Button();
      Delete.setBackground(new Color(0, 0, 128));
      Delete.setForeground(new Color(250, 250, 250));
      Delete.setText("Delete");
        
        
        
       Delete.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
    // Obtenez l'index de la ligne sélectionnée
    int selectedRow = table.getSelectedRow();
    
    // Vérifiez si une ligne est sélectionnée
    if (selectedRow != -1) {
        // Obtenez les données de la ligne sélectionnée avant de la supprimer du modèle de table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String date = (String) model.getValueAt(selectedRow, 0);
        String type = (String) model.getValueAt(selectedRow, 1);
        String duree = (String) model.getValueAt(selectedRow, 2);
        
        // Supprimez la ligne sélectionnée du modèle de table
        model.removeRow(selectedRow);
        
        // Supprimez les données correspondantes de la base de données
        deleteActivityData(date, type, duree);
    }
    }
        });
        Panel.add(Delete, "w 20%, h 20");
        

        // Table to display activity data
        
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        
        
        
        
        
    }
    

    private void deleteActivityData(String date, String type, String duree) {
    try {
        // Établir une connexion à la base de données
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miboSanté", "root", "");
        
        // Préparer la requête SQL de suppression
        String sql = "DELETE FROM ActivitePhysique WHERE date = ? AND type = ? AND duree = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, date);
        statement.setString(2, type);
        statement.setString(3, duree);
        
        // Exécuter la requête SQL
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Les données de l'activité physique ont été supprimées avec succès !");
        }
        
        // Fermer la connexion et la déclaration
        //statement.close();
        //connection.close();
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression des données de l'activité physique : " + ex.getMessage());
    }
}

    
    
    
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
            
            
            
            // Ajoutez la ligne au modèle de table
            model.addRow(row);
        }
        
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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 286, Short.MAX_VALUE)
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
                "Date", "Type", "Duration"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
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
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
