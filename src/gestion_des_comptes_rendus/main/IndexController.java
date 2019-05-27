/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_des_comptes_rendus.main;

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
public class IndexController implements Initializable {

    @FXML
    private Button goToComptesRendusButton;
    @FXML
    private Button goToPraticiensButton;
    @FXML
    private Button goToVisiteursButton;
    @FXML
    private Button goToMedicamentsButton;

    /**
     * Initializes the controller class.
     */
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        // Asserts
        assert goToComptesRendusButton != null : "fx:id=\"goToComptesRendusButton\" was not injected: check your FXML file 'formRapportVisite.fxml'.";
        assert goToPraticiensButton != null : "fx:id=\"goToPraticiensButton\" was not injected: check your FXML file 'formPraticien.fxml'.";
        assert goToVisiteursButton != null : "fx:id=\"goToVisiteursButton\" was not injected: check your FXML file 'formVisiteur.fxml'.";
        assert goToMedicamentsButton != null : "fx:id=\"goToMedicamentsButton\" was not injected: check your FXML file 'formMedicament.fxml'.";

        /**
         * START OF GO TO COMPTES RENDUS BUTTON
         */
        // initialize your logic here: all @FXML variables will have been injected
        goToComptesRendusButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Stage stage;
                Parent root;
                if (event.getSource() == goToComptesRendusButton) {
                    //get reference to the button's stage         
                    stage = (Stage) goToComptesRendusButton.getScene().getWindow();
                    //load up OTHER FXML document
                    try {
                        URL formRapportVisitePath = getClass().getClassLoader().getResource("gestion_des_comptes_rendus/rapportVisite/formRapportVisite.fxml");
                        TitledPane page = (TitledPane) FXMLLoader.load(formRapportVisitePath);
                        Scene newScene = new Scene(page);
                        stage.setScene(newScene);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //END OF GO TO COMPTES RENDUS BUTTON

        /**
         * START OF GO TO PRATICIENS BUTTON
         */
        // initialize your logic here: all @FXML variables will have been injected
        goToPraticiensButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Stage stage;
                Parent root;
                if (event.getSource() == goToPraticiensButton) {
                    //get reference to the button's stage         
                    stage = (Stage) goToPraticiensButton.getScene().getWindow();
                    //load up OTHER FXML document
                    try {
                        URL formPraticienPath = getClass().getClassLoader().getResource("gestion_des_comptes_rendus/praticien/formPraticien.fxml");
                        TitledPane page = (TitledPane) FXMLLoader.load(formPraticienPath);
                        Scene newScene = new Scene(page);
                        stage.setScene(newScene);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //END OF GO TO PRATICIENS BUTTON

        /**
         * START OF GO TO VISITEURS BUTTON
         */
        // initialize your logic here: all @FXML variables will have been injected
        goToVisiteursButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Stage stage;
                Parent root;
                if (event.getSource() == goToVisiteursButton) {
                    //get reference to the button's stage         
                    stage = (Stage) goToVisiteursButton.getScene().getWindow();
                    //load up OTHER FXML document
                    try {
                        URL formVisiteurPath = getClass().getClassLoader().getResource("gestion_des_comptes_rendus/visiteur/formVisiteur.fxml");
                        TitledPane page = (TitledPane) FXMLLoader.load(formVisiteurPath);
                        Scene newScene = new Scene(page);
                        stage.setScene(newScene);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //END OF GO TO VISITEURS BUTTON

        /**
         * START OF GO TO MEDICAMENTS BUTTON
         */
        // initialize your logic here: all @FXML variables will have been injected
        goToMedicamentsButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Stage stage;
                Parent root;
                if (event.getSource() == goToMedicamentsButton) {
                    //get reference to the button's stage         
                    stage = (Stage) goToMedicamentsButton.getScene().getWindow();
                    //load up OTHER FXML document
                    try {
                        URL formMedicamentPath = getClass().getClassLoader().getResource("gestion_des_comptes_rendus/medicament/formMedicament.fxml");
                        TitledPane page = (TitledPane) FXMLLoader.load(formMedicamentPath);
                        Scene newScene = new Scene(page);
                        stage.setScene(newScene);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //END OF GO TO MEDICAMENTS BUTTON
    }

}
