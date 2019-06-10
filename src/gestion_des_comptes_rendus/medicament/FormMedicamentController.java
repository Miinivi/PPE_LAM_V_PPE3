/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_des_comptes_rendus.medicament;

import gestion_des_comptes_rendus.objets.Medicament;
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
public class FormMedicamentController implements Initializable {

    @FXML
    private Button precedent;
    @FXML
    private ComboBox<String> comboBoxMedicament = new ComboBox<String>();
    @FXML
    private TextField depotLegMedicament;
    @FXML
    private TextField familleMedicament;
    @FXML
    private TextField compoMedicament;
    @FXML
    private TextField effetsMedicament;
    @FXML
    private TextField contreIndicMedicament;
    //Obs list
    private ObservableList<String> medicamentObsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert precedent != null : "fx:id=\"precedent\" was not injected: check your FXML file 'index.fxml'.";
        assert comboBoxMedicament != null : "fx:id=\"comboBoxMedicament\" was not injected: check your FXML file 'formMedicament.fxml'.";

        //Add medicaments to combobox
        ArrayList<Medicament> medicamentList = Medicament.getMedicamentList();
        for (Medicament medicament : medicamentList) {
            medicamentObsList.add(medicament.getNomComm());
            comboBoxMedicament.getItems().setAll(medicamentObsList);
        }

        //Prevent from editing textfields
        depotLegMedicament.setEditable(false);
        familleMedicament.setEditable(false);
        compoMedicament.setEditable(false);
        effetsMedicament.setEditable(false);
        contreIndicMedicament.setEditable(false);

        //Get value of comboBox => change value of textfields
        comboBoxMedicament.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selection = comboBoxMedicament.getSelectionModel().getSelectedItem();
                for (Medicament medicament : medicamentList) {
                    if (medicament.getNomComm() == selection) {
                        depotLegMedicament.setText(medicament.getDepotLeg());
                        familleMedicament.setText(medicament.getFamille());
                        compoMedicament.setText(medicament.getCompo());
                        effetsMedicament.setText(medicament.getEffets());
                        contreIndicMedicament.setText(medicament.getContreIndic());
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
