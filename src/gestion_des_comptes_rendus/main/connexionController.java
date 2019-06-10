/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_des_comptes_rendus.main;

import gestion_des_comptes_rendus.objets.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Lam & Rodrigues
 */
public class connexionController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button loginbtn;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    
    //ID
    public static String idVisiteur;

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert loginbtn != null : "fx:id=\"loginbtn\" was not injected: check your FXML file 'index.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'index.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'index.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected
        loginbtn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                Connexion co = new Connexion();
                String loginContenu = login.getText();
                String passwordContenu = password.getText();

                //View
                Stage stage;
                Parent root;

                try {
                    co.seConnecter();
                    Connection c = co.getC();
                    Statement st = co.getSt();
                    ResultSet resultIdPwdByLogin = st.executeQuery("SELECT id, mdp FROM visiteur WHERE login='" + loginContenu + "'");

                    while (resultIdPwdByLogin.next()) {
                        idVisiteur = resultIdPwdByLogin.getString("id");
                        String mdpUser = resultIdPwdByLogin.getString("mdp");

                        if (event.getSource() == loginbtn && passwordContenu.equals(mdpUser)) {
                            //get reference to the button's stage         
                            stage = (Stage) loginbtn.getScene().getWindow();
                            //load up OTHER FXML document
                            try {
                                TitledPane page = (TitledPane) FXMLLoader.load(getClass().getResource("index.fxml"));
                                Scene newScene = new Scene(page);
                                stage.setScene(newScene);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            label.setText("Erreur de connexion !");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        );
    }

}
