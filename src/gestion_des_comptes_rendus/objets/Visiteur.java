package gestion_des_comptes_rendus.objets;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Visiteur {

    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String secteur;

    public Visiteur(String nom, String prenom, String adresse, String codePostal, String ville, String secteur) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.secteur = secteur;
    }

    public Visiteur() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    ///////////////////////////////////////////////////////////////
    /////////-----------------METHODES-----------------////////////
    ///////////////////////////////////////////////////////////////
    public static ArrayList<Visiteur> getVisiteurList() {
        //Liste des visiteurs
        ArrayList<Visiteur> visiteurList = new ArrayList<Visiteur>();
        //Connexion à la base de données
        Connexion co = new Connexion();
        try {
            co.seConnecterSecondeBDD();
            Statement st = co.getSt();
            Connection c = co.getC();
            ResultSet resultVisiteurList = st.executeQuery("SELECT VIS_NOM, Vis_PRENOM, VIS_ADRESSE, VIS_CP, VIS_VILLE, SEC_LIBELLE FROM visiteur V LEFT JOIN secteur S on V.SEC_CODE = S.SEC_CODE ORDER BY VIS_NOM ASC");
            while (resultVisiteurList.next()) {
                //Récupération des données
                String nom = resultVisiteurList.getString("VIS_NOM");
                String prenom = resultVisiteurList.getString("Vis_PRENOM");
                String adresse = resultVisiteurList.getString("VIS_ADRESSE");
                String cp = resultVisiteurList.getString("VIS_CP");
                String ville = resultVisiteurList.getString("VIS_VILLE");
                String secteur = resultVisiteurList.getString("SEC_LIBELLE");
                if(secteur == null){ 
                    secteur = "Pas de secteur enregistré pour ce visiteur";
                }
                //Ajout des données à un objet visiteur
                Visiteur visiteur = new Visiteur(nom, prenom, adresse, cp, ville, secteur);
                //Ajout de l'objet visiteur à la liste
                visiteurList.add(visiteur);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return visiteurList;
    }
}
