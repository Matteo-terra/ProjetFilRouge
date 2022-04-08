package com.filrouge.projet_filrouge.controller;

import com.filrouge.projet_filrouge.HelloApplication;
import com.filrouge.projet_filrouge.helpers.DbConnect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class Login {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnConnexion;

    public Login() {

    }

    @FXML
    void fermeture(ActionEvent event) {
        System.exit(0);
    }

    public void validConnexion(javafx.event.ActionEvent actionEvent) {
        JFrame jFrame = new JFrame();
        String username = this.txtUsername.getText();
        String password = this.txtPassword.getText();
        FXMLLoader fxmlLoader;
        Scene scene;
        Stage stage;

        try {
            DbConnect db = new DbConnect();

            ResultSet rs = db.searchQuery("SELECT * FROM utilisateurs WHERE login='" + username + "' AND password='" + password + "'");
            if (rs.isBeforeFirst()) {
                System.out.println("Identification réussie !");
                try {
                    fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FilRouge2.fxml"));
                    scene = new Scene(fxmlLoader.load());
                    stage = new Stage();
                    stage.setTitle("Bibliothèque!");
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception var8) {
                    var8.printStackTrace();
                }
            } else {
                System.out.println("Nom d'utilisateur / Mot de passe incorrect");
                JOptionPane.showMessageDialog(jFrame, "Nom d'utilisateur / Mot de passe incorrect");
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}


