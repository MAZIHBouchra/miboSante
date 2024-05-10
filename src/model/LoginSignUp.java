/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package model;


import View.FormView;
import com.raven.main.Main;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import swingDesign.Button;
import swingDesign.MyPasswordField;
import swingDesign.MyTextField;


public class LoginSignUp extends javax.swing.JLayeredPane {
    public static String currentUserName;
    public static String currentUserEmail; // A

    public LoginSignUp() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
    }


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
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mibosanté", "root", "");
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
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginschema", "root", "1234");

            // Prepare the SQL query to search for the user with the given email and matching password
            String sql = "SELECT * FROM users WHERE email = ? AND mot_de_passe = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            // Execute the SQL query
            ResultSet resultSet = statement.executeQuery();

            // Check if a matching user was found
            if (resultSet.next()) {
                authenticated = true; // The user is successfully authenticated
                String userName = resultSet.getString("nom"); // Store the user's name
                email = resultSet.getString("email"); // Store the user's email
                currentUserName = userName; // Set the current user's name
                currentUserEmail = email; // Set the current user's email

                // Create a Profile instance and display the user's name and email


            }

            // Close the resources
            //resultSet.close();
            //statement.close();
            //connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle exceptions appropriately
        }
        return authenticated;
    }
    public static void updateUser(String newEmail, String newPassword) {
    try {
        // Establish a connection to the database
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mibosanté", "root", "");

        // Prepare the SQL update statement
        String sql = "UPDATE users SET email = ?, mot_de_passe = ? WHERE nom = ?";

        // Create a PreparedStatement
        PreparedStatement statement = connection.prepareStatement(sql);

        // Set the parameters
        statement.setString(1, newEmail);
        statement.setString(2, newPassword);
        statement.setString(3, currentUserName);

        // Execute the update
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("User's email and password were updated successfully!");
            JOptionPane.showMessageDialog(null, "User's email and password were updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        }

        // Close the resources
        statement.close();
        connection.close();
    } catch (SQLException ex) {
        System.out.println("Error while updating the user's email and password: " + ex.getMessage());
    }
}
    private void showDashboard() {
        // Afficher le tableau de bord en changeant l'affichage ou en chargeant une nouvelle fenêtre
        // Exemple :
        FormView dashboard = new FormView();
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
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
    }// </editor-fold>


    // Variables declaration - do not modify
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration
}