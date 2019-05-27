/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_des_comptes_rendus.rapportVisite;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lam & Rodrigues
 */
public class FormRapportVisiteController implements Initializable {

    @FXML
    private Button precedent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert precedent != null : "fx:id=\"precedent\" was not injected: check your FXML file 'index.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected
        precedent.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                Stage stage;
                Parent root;
                if (event.getSource() == precedent) {
                    //get reference to the button's stage         
                    stage = (Stage) precedent.getScene().getWindow();
                    //load up OTHER FXML document
                    try {
                        URL backPath = getClass().getClassLoader().getResource("gestion_des_comptes_rendus/main/index.fxml");
                        TitledPane page = (TitledPane) FXMLLoader.load(backPath);
                        Scene newScene = new Scene(page);
                        stage.setScene(newScene);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
