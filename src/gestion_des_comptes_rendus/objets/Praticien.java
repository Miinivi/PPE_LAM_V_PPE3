package gestion_des_comptes_rendus.objets;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Praticien {

    private String nom;
    private String prenom;
    private String adresse;
    private String cp;
    private String ville;
    private String profession;
    private String lieuTravail;
    private float coeffNotoriete;

    public Praticien(String nom, String prenom, String adresse, String cp, String ville, String profession, String lieuTravail, float coeffNotoriete) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.profession = profession;
        this.lieuTravail = lieuTravail;
        this.coeffNotoriete = coeffNotoriete;
    }

    public Praticien() {
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

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getLieuTravail() {
        return lieuTravail;
    }

    public void setLieuTravail(String lieuTravail) {
        this.lieuTravail = lieuTravail;
    }

    public float getCoeffNotoriete() {
        return coeffNotoriete;
    }

    public void setCoeffNotoriete(float coeffNotoriete) {
        this.coeffNotoriete = coeffNotoriete;
    }

    public static ArrayList<Praticien> getPraticienList() {
        //Liste des praticiens
        ArrayList<Praticien> praticienList = new ArrayList<Praticien>();
        //Connexion à la base de données
        Connexion co = new Connexion();
        try {
            co.seConnecterSecondeBDD();
            Statement st = co.getSt();
            Connection c = co.getC();
            ResultSet resultPraticienList = st.executeQuery("SELECT * FROM praticien P LEFT JOIN type_praticien TP on P.TYP_CODE = TP.TYP_CODE ORDER BY PRA_NOM ASC");
            while (resultPraticienList.next()) {
                //Récupération des données
                String nom = resultPraticienList.getString("PRA_NOM");
                String prenom = resultPraticienList.getString("PRA_PRENOM");
                String adresse = resultPraticienList.getString("PRA_ADRESSE");
                String cp = resultPraticienList.getString("PRA_CP");
                String ville = resultPraticienList.getString("PRA_VILLE");
                String profession = resultPraticienList.getString("TYP_LIBELLE");
                String lieuTravail = resultPraticienList.getString("TYP_LIEU");
                float coeffNotoriete = resultPraticienList.getFloat("PRA_COEFNOTORIETE");

                //Ajout des données à un objet praticien
                Praticien praticien = new Praticien(nom, prenom, adresse, cp, ville, profession, lieuTravail, coeffNotoriete);
                //Ajout de l'objet praticien à la liste
                praticienList.add(praticien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return praticienList;
    }

    public int getIdFromNom() {
        int id = 0;
        //Connexion à la base de données
        Connexion co = new Connexion();
        try {
            co.seConnecterSecondeBDD();
            Statement st = co.getSt();
            Connection c = co.getC();
            ResultSet resultPraticienId = st.executeQuery("SELECT PRA_NUM FROM praticien WHERE PRA_NOM = '" + nom + "'");
            while (resultPraticienId.next()) {
                id = resultPraticienId.getInt("PRA_NUM");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
