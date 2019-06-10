package gestion_des_comptes_rendus.objets;

import java.sql.Connection;
import java.sql.Statement;

public class CompteRendu {

    private String idVisiteur;
    private int idPraticien;
    private int numRapport;
    private String dateRapport;
    //private Date dateRapport;
    private String bilan;
    private String motif;

    public CompteRendu(String idVisiteur, int idPraticien, int numRapport, String dateRapport, String bilan, String motif) {
        this.idVisiteur = idVisiteur;
        this.idPraticien = idPraticien;
        this.numRapport = numRapport;
        this.dateRapport = dateRapport;
        this.bilan = bilan;
        this.motif = motif;
    }

    public CompteRendu() {
    }

    public String getIdVisiteur() {
        return idVisiteur;
    }

    public void setIdVisiteur(String idVisiteur) {
        this.idVisiteur = idVisiteur;
    }

    public int getIdPraticien() {
        return idPraticien;
    }

    public void setIdPraticien(int idPraticien) {
        this.idPraticien = idPraticien;
    }

    public int getNumRapport() {
        return numRapport;
    }

    public void setNumRapport(int numRapport) {
        this.numRapport = numRapport;
    }

    public String getDateRapport() {
        return dateRapport;
    }

    public void setDateRapport(String dateRapport) {
        this.dateRapport = dateRapport;
    }

    public String getBilan() {
        return bilan;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public static void addRapportSQL(String id, int idPraticien, int numRapport, String dateRapport, String bilan, String motif) {
        //Connexion à la base de données
        Connexion co = new Connexion();
        try {
            co.seConnecterSecondeBDD();
            Statement st = co.getSt();
            Connection c = co.getC();

            st.executeUpdate("INSERT INTO rapport_visite VALUES ('" +
                              id + "', '" + 
                              numRapport + "', '" +
                              idPraticien + "', '" +
                              dateRapport + "', '" + 
                              bilan + "', '" +
                              motif + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
