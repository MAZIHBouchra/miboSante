/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package component;


import java.sql.ResultSet;
import com.mycompany.Main.MainDash;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import swingForm.Button;
import swingForm.MyPasswordField;
import swingForm.MyTextField;


public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
    }

    /*private void initRegister(){
        register.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]25[]push"));
        JLabel label=new JLabel("Create Account");
        label.setFont(new Font("sansserif",1,30));
        label.setForeground(new Color(0, 0, 128));
        register.add(label);
        MyTextField txtUser=new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        txtUser.setHint("Name");
        register.add(txtUser, "w 60%");
        MyTextField txtEmail=new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 60%");
        MyPasswordField txtPass=new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtPass.setHint("Password");
        register.add(txtPass, "w 60%");
        Button cmd=new Button();
        cmd.setBackground(new Color(0, 0, 128));
        cmd.setForeground(new Color(250,250,250));
        cmd.setText("SIGN UP");
        register.add(cmd,"w 40%, h 40");
    }*/
    private void initRegister(){
    register.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]25[]push"));
    JLabel label=new JLabel("Create Account");
    label.setFont(new Font("sansserif",1,30));
    label.setForeground(new Color(0, 0, 128));
    register.add(label);
    MyTextField txtUser=new MyTextField();
    txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
    txtUser.setHint("Name");
    register.add(txtUser, "w 60%");
    MyTextField txtEmail=new MyTextField();
    txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
    txtEmail.setHint("Email");
    register.add(txtEmail, "w 60%");
    MyPasswordField txtPass=new MyPasswordField();
    txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
    txtPass.setHint("Password");
    register.add(txtPass, "w 60%");
    Button cmd=new Button();
    cmd.setBackground(new Color(0, 0, 128));
    cmd.setForeground(new Color(250,250,250));
    cmd.setText("SIGN UP");
    cmd.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = txtUser.getText();
            String email = txtEmail.getText();
            String password = new String(txtPass.getPassword());
            
            // Insérer les données dans la base de données
            insertUserData(name, email, password);
        }
    });
    register.add(cmd,"w 40%, h 40");
}

    private void insertUserData(String name, String email, String password) {
    try {
        // Créer une connexion à la base de données et préparer la requête d'insertion
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/miboSanté", "root", "");
        String query = "INSERT INTO users (nom, email, mot_de_passe) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        
        // Définir les valeurs des paramètres dans la requête d'insertion
        statement.setString(1, name);
        statement.setString(2, email);
        statement.setString(3, password);
        
        // Exécuter la requête d'insertion
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Les données de l'utilisateur ont été insérées avec succès !");
        }
        
        // Fermer la connexion et la déclaration
        //statement.close();
        //connection.close();
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'insertion des données de l'utilisateur : " + ex.getMessage());
    }
}
    
    /*private void initLogin(){
        login.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]25[]push"));
        JLabel label=new JLabel("Sign In");
        label.setFont(new Font("sansserif",1,30));
        label.setForeground(new Color(0, 0, 128));
        login.add(label);
        MyTextField txtEmail=new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%");
        MyPasswordField txtPass=new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");
        JButton cmdForget=new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100,100,100));
        cmdForget.setFont(new Font("sansserif",1,12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        Button cmd=new Button();
        cmd.setBackground(new Color(0, 0, 128));
        cmd.setForeground(new Color(250,250,250));
        cmd.setText("SIGN IN");
        login.add(cmd,"w 40%, h 40");
    }*/
    
    private void initLogin(){
        login.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]25[]push"));
        JLabel label=new JLabel("Sign In");
        label.setFont(new Font("sansserif",1,30));
        label.setForeground(new Color(0, 0, 128));
        login.add(label);
        MyTextField txtEmail=new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%");
        MyPasswordField txtPass=new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");
        JButton cmdForget=new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100,100,100));
        cmdForget.setFont(new Font("sansserif",1,12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        Button cmd=new Button();
        cmd.setBackground(new Color(0, 0, 128));
        cmd.setForeground(new Color(250,250,250));
        cmd.setText("SIGN IN");
        cmd.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Récupérer les informations d'identification fournies par l'utilisateur
            String email = txtEmail.getText();
            String password = new String(txtPass.getPassword());
            
            // Vérifier les informations d'identification dans la base de données
            if (authenticateUser(email, password)) {
                // Si l'authentification réussit, afficher le tableau de bord
                showDashboard();
            } else {
                // Sinon, afficher un message d'erreur ou une notification à l'utilisateur
                JOptionPane.showMessageDialog(null, "Invalid email or password. Please try again.", "Authentication Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
    login.add(cmd,"w 40%, h 40");
    }
    
    private boolean authenticateUser(String email, String password) {
    boolean authenticated = false;
    try {
        // Établir une connexion à la base de données
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miboSanté", "root", "");
        
        // Préparer la requête SQL pour rechercher l'utilisateur avec l'email donné et le mot de passe correspondant
        String sql = "SELECT * FROM users WHERE email = ? AND mot_de_passe = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        
        // Exécuter la requête SQL
        ResultSet resultSet = statement.executeQuery();
        
        // Vérifier si un utilisateur correspondant a été trouvé
        if (resultSet.next()) {
            authenticated = true; // L'utilisateur est authentifié avec succès
        }
        
        // Fermer les ressources
        //resultSet.close();
        //statement.close();
        //connection.close();
    } catch (SQLException ex) {
        ex.printStackTrace(); // Gérer les exceptions appropriées
    }
    return authenticated;
}
    private void showDashboard() {
    // Afficher le tableau de bord en changeant l'affichage ou en chargeant une nouvelle fenêtre
    // Exemple :
    MainDash dashboard = new MainDash();
    dashboard.setVisible(true);
}
    
    public void showRegister(boolean show){
        if(show){
            register.setVisible(true);
            login.setVisible(false);
        }else{
            register.setVisible(false);
            login.setVisible(true);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
