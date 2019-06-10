/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_des_comptes_rendus.visiteur;

import gestion_des_comptes_rendus.objets.Visiteur;
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
public class FormVisiteurController implements Initializable {

    @FXML
    private Button precedent;
    @FXML
    private ComboBox<String> comboBoxVisiteur = new ComboBox<String>();
    @FXML
    private TextField nomVisiteur;
    @FXML
    private TextField prenomVisiteur;
    @FXML 
    private TextField adresseVisiteur;
    @FXML
    private TextField cpVisiteur;
    @FXML
    private TextField villeVisiteur;
    @FXML
    private TextField secteurVisiteur;
    //Obs list
    private ObservableList<String> visiteurObsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert precedent != null : "fx:id=\"precedent\" was not injected: check your FXML file 'index.fxml'.";
        assert comboBoxVisiteur != null : "fx:id=\"comboBoxVisiteur\" was not injected: check your FXML file 'formVisiteur.fxml'.";

        //Add visiteurs to combobox
        ArrayList<Visiteur> visiteurList = Visiteur.getVisiteurList();
        for (Visiteur visiteur : visiteurList) {
            visiteurObsList.add(visiteur.getNom());
            comboBoxVisiteur.getItems().setAll(visiteurObsList);
        }
        
        //Prevent from editing textfields
        nomVisiteur.setEditable(false);
        prenomVisiteur.setEditable(false);
        adresseVisiteur.setEditable(false);
        cpVisiteur.setEditable(false);
        villeVisiteur.setEditable(false);
        secteurVisiteur.setEditable(false);

        //Get value of comboBox => change value of textfields
        comboBoxVisiteur.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selection = comboBoxVisiteur.getSelectionModel().getSelectedItem();
                for (Visiteur visiteur : visiteurList) {
                    if(visiteur.getNom() == selection) {
                        nomVisiteur.setText(selection);
                        prenomVisiteur.setText(visiteur.getPrenom());
                        adresseVisiteur.setText(visiteur.getAdresse());
                        cpVisiteur.setText(visiteur.getCodePostal());
                        villeVisiteur.setText(visiteur.getVille());
                        secteurVisiteur.setText(visiteur.getSecteur());
                    }
                }
            }
        });

        //Initialize your logic here: all @FXML variables will have been injected
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
