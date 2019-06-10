package gestion_des_comptes_rendus.objets;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Medicament {

    private String nomComm;
    private String depotLeg;
    private String famille;
    private String compo;
    private String effets;
    private String contreIndic;
    //private float prix;

    public Medicament(String nomComm, String depotLeg, String famille, String compo, String effets, String contreIndic) {
        this.nomComm = nomComm;
        this.depotLeg = depotLeg;
        this.famille = famille;
        this.compo = compo;
        this.effets = effets;
        this.contreIndic = contreIndic;
    }

    public Medicament() {
    }

    public String getNomComm() {
        return nomComm;
    }

    public void setNomComm(String nomComm) {
        this.nomComm = nomComm;
    }

    public String getDepotLeg() {
        return depotLeg;
    }

    public void setDepotLeg(String depotLeg) {
        this.depotLeg = depotLeg;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public String getCompo() {
        return compo;
    }

    public void setCompo(String compo) {
        this.compo = compo;
    }

    public String getEffets() {
        return effets;
    }

    public void setEffets(String effets) {
        this.effets = effets;
    }

    public String getContreIndic() {
        return contreIndic;
    }

    public void setContreIndic(String contreIndic) {
        this.contreIndic = contreIndic;
    }

    public static ArrayList<Medicament> getMedicamentList() {
        //Liste des médicaments
        ArrayList<Medicament> medicamentList = new ArrayList<Medicament>();
        //Connexion à la base de données
        Connexion co = new Connexion();
        try {
            co.seConnecterSecondeBDD();
            Statement st = co.getSt();
            Connection c = co.getC();
            ResultSet resultMedicamentList = st.executeQuery("SELECT * FROM medicament M LEFT JOIN famille F on M.FAM_CODE = F.FAM_CODE");
            while (resultMedicamentList.next()) {
                //Récupération des données
                String nom = resultMedicamentList.getString("MED_NOMCOMMERCIAL");
                String depotLeg = resultMedicamentList.getString("MED_DEPOTLEGAL");
                String famille = resultMedicamentList.getString("FAM_LIBELLE");
                String compo = resultMedicamentList.getString("MED_COMPOSITION");
                String effets = resultMedicamentList.getString("MED_EFFETS");
                String contreIndic = resultMedicamentList.getString("MED_CONTREINDIC");
                //Ajout des données à un objet medicament
                Medicament medicament = new Medicament(nom, depotLeg, famille, compo, effets, contreIndic);
                //Ajout de l'objet medicament à la liste
                medicamentList.add(medicament);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medicamentList;
    }
}
