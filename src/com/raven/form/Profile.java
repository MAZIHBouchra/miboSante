package com.raven.form;

import com.raven.swing.ImageAvatar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import swingDesign.TextField;
import swingDesign.Button;
import model.LoginSignUp;
public class Profile extends JPanel {

    public Profile() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Add this line to add margin

        setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageAvatar avatarImage = new ImageAvatar();
        avatarImage.setPreferredSize(new Dimension(150, 150));
        String userName = LoginSignUp.currentUserName; // Fetch the user's name
        JLabel weclome = new JLabel("Welcome, " + userName); // Display the user's name
        weclome.setFont(new Font("sansserif",1,30));
        weclome.setForeground(Color.black);
        add(weclome);


        Button btnImportImage=new Button();
        btnImportImage.setBackground(new Color(0, 0, 128));
        btnImportImage.setForeground(new Color(250,250,250));
        btnImportImage.setText("    Import image   ");
        btnImportImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "png", "gif", "bmp"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getPath()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
                    avatarImage.setImage(imageIcon);
                    avatarImage.repaint();
                }
            }
        });
        add(btnImportImage);

        JPanel avatarPanel = new JPanel();
        avatarPanel.setMaximumSize(new Dimension(200, 250));
        avatarPanel.add(avatarImage);
        add(avatarPanel);
        JLabel editInfoLabel = new JLabel("Edit Information");
        editInfoLabel.setFont(new Font("sansserif",1,30));
        editInfoLabel.setForeground(new Color(0, 0, 128));
        add(editInfoLabel);

        String userEmail = LoginSignUp.currentUserEmail; // Fetch the user's email
        JLabel emailLabel = new JLabel("current Email: " + userEmail); // Display the user's email
        emailLabel.setFont(new Font("sansserif",1,20));
        emailLabel.setForeground(Color.black);
        add(emailLabel);


        JLabel editEmailLabel = new JLabel("Edit Email");
        editEmailLabel.setFont(new Font("sansserif",1,20));
        editEmailLabel.setForeground(new Color(0, 0, 128));
        add(editEmailLabel);

       TextField txtEmail = new TextField();

        txtEmail.setMaximumSize(new Dimension(400, 60)); // Set the maximum size
        add(txtEmail);




        JLabel editPasswordLabel = new JLabel("Edit Password");
        editPasswordLabel.setFont(new Font("sansserif",1,20));
        editPasswordLabel.setForeground(new Color(0, 0, 128));

        add(editPasswordLabel);
        TextField txtPassword = new TextField();
        txtPassword.setMaximumSize(new Dimension(400, 60));
        add(txtPassword);


        Button btnEdit=new Button();
        btnEdit.setBackground(new Color(0, 0, 128));
        btnEdit.setForeground(new Color(250,250,250));
        btnEdit.setText("         EDIT         ");
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the new email and password
                String newEmail = txtEmail.getText();
                String newPassword = txtPassword.getText();

                // Update the user's email and password
                LoginSignUp.updateUser(newEmail, newPassword);
                txtEmail.setText("");
                txtPassword.setText("");
            }
        });
        add(btnEdit);
    }


    }
