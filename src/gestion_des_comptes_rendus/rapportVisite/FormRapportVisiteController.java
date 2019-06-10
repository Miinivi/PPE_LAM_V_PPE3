/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_des_comptes_rendus.rapportVisite;

import static gestion_des_comptes_rendus.main.connexionController.idVisiteur;
import gestion_des_comptes_rendus.objets.CompteRendu;
import gestion_des_comptes_rendus.objets.Medicament;
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
public class FormRapportVisiteController implements Initializable {

    @FXML
    private Button precedent;
    @FXML
    private ComboBox<String> comboBoxPraticienRapVisite = new ComboBox<String>();
    @FXML
    private ComboBox<String> comboBoxMotifRapVisite = new ComboBox<String>();
    @FXML
    private ComboBox<String> comboBoxMedocRapVisite1 = new ComboBox<String>();
    @FXML
    private ComboBox<String> comboBoxMedocRapVisite2 = new ComboBox<String>();
    @FXML
    private TextField textFieldNumRapVisite;
    @FXML
    private TextField textFieldBilanRapVisite;
    @FXML
    private TextField textFieldDateRapVisite;
    @FXML 
    private Button envoyer;
    //Obs list
    private ObservableList<String> praticienObsList = FXCollections.observableArrayList();
    private ObservableList<String> medicamentObsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert precedent != null : "fx:id=\"precedent\" was not injected: check your FXML file 'index.fxml'.";
        assert comboBoxPraticienRapVisite != null : "fx:id=\"comboBoxPraticienRapVisite\" was not injected: check your FXML file 'formRapportVisite.fxml'.";
        assert comboBoxMedocRapVisite1 != null : "fx:id=\"comboBoxMedocRapVisite1\" was not injected: check your FXML file 'formRapportVisite.fxml'.";
        assert comboBoxMedocRapVisite2 != null : "fx:id=\"comboBoxMedocRapVisite2\" was not injected: check your FXML file 'formRapportVisite.fxml'.";
        
        //Add praticiens to comboBox
        ArrayList<Praticien> praticienList = Praticien.getPraticienList();
        for (Praticien praticien : praticienList) {
            praticienObsList.add(praticien.getNom());
            comboBoxPraticienRapVisite.getItems().setAll(praticienObsList);
        }
        
        //Add medicaments to comboBox
        ArrayList<Medicament> medicamentList = Medicament.getMedicamentList();
        for (Medicament medicament : medicamentList) {
            medicamentObsList.add(medicament.getNomComm());
            comboBoxMedocRapVisite1.getItems().setAll(medicamentObsList);
            comboBoxMedocRapVisite2.getItems().setAll(medicamentObsList);
        }
        
        //Add motifs to comboBox
        comboBoxMotifRapVisite.getItems().setAll("Actualisation annuelle", "Rapport Annuel", "Baisse activité");
        
        //Getting datas from rapport visite form when clicking on "Envoyer"
        envoyer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Getting all the values to insert in bdd
                String nomPraticien = comboBoxPraticienRapVisite.getValue();
                Praticien praticien = new Praticien();
                praticien.setNom(nomPraticien);
                int idPraticien = praticien.getIdFromNom();
                int numRapport = Integer.parseInt(textFieldNumRapVisite.getText());
                String dateRapport = textFieldDateRapVisite.getText();
                String bilan = textFieldBilanRapVisite.getText();
                String motif = comboBoxMotifRapVisite.getValue();
                System.out.println(idVisiteur + idPraticien + numRapport + dateRapport + bilan + motif);
                //Adding values to addRapportSQL
                CompteRendu.addRapportSQL(idVisiteur, idPraticien, numRapport, dateRapport, bilan, motif);
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
