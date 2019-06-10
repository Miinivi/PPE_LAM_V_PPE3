/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_des_comptes_rendus.praticien;

import gestion_des_comptes_rendus.objets.Praticien;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lam & Rodrigues
 */
public class FormPraticienController implements Initializable {

    @FXML
    private Button precedent;
    @FXML
    private ComboBox<String> comboBoxPraticien = new ComboBox<String>();
    @FXML
    private TextField nomPraticien;
    @FXML
    private TextField prenomPraticien;
    @FXML
    private TextField adressePraticien;
    @FXML
    private TextField cpPraticien;
    @FXML
    private TextField villePraticien;
    @FXML
    private TextField professionPraticien;
    @FXML
    private TextField lieuTravPraticien;
    //Obs list
    private ObservableList<String> praticienObsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert precedent != null : "fx:id=\"precedent\" was not injected: check your FXML file 'index.fxml'.";
        assert comboBoxPraticien != null : "fx:id=\"comboBoxPraticien\" was not injected: check your FXML file 'formPraticien.fxml'.";

        //Add praticiens to comboBox
        ArrayList<Praticien> praticienList = Praticien.getPraticienList();
        for (Praticien praticien : praticienList) {
            praticienObsList.add(praticien.getNom());
            comboBoxPraticien.getItems().setAll(praticienObsList);
        }

        //Prevent from editing textfields
        nomPraticien.setEditable(false);
        prenomPraticien.setEditable(false);
        adressePraticien.setEditable(false);
        cpPraticien.setEditable(false);
        villePraticien.setEditable(false);
        professionPraticien.setEditable(false);
        lieuTravPraticien.setEditable(false);

        //Get value of comboBox => change value of textfields
        comboBoxPraticien.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selection = comboBoxPraticien.getSelectionModel().getSelectedItem();
                for (Praticien praticien : praticienList) {
                    if (praticien.getNom() == selection) {
                        nomPraticien.setText(praticien.getNom());
                        prenomPraticien.setText(praticien.getPrenom());
                        adressePraticien.setText(praticien.getAdresse());
                        cpPraticien.setText(praticien.getCp());
                        villePraticien.setText(praticien.getVille());
                        professionPraticien.setText(praticien.getProfession());
                        lieuTravPraticien.setText(praticien.getLieuTravail());
//                        coeffNotPraticien.setText(praticien.getCoeffNotoriete());
                    }
                }
            }
        });

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
