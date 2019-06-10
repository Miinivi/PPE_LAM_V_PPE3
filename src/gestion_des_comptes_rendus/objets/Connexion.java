package gestion_des_comptes_rendus.objets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connexion {

    private String url;
    private String log;
    private String mdp;
    private Connection c;
    private Statement st;

    // = "jdbc:mysql://localhost/gsb_valide";
    // = "root";
    // = "";
    //A changer pour une BDD sur serveur de l'école
    //String url = "jdbc:mysql://172.16.0.102:3307/gsb_valide";
    //String log = "groupe5";
    //String mdp = "rootroot";
    public Connexion() {
        url = "jdbc:mysql://localhost/gsb_valide";
        log = "root";
        mdp = "";
    }

    public Connexion(String url, String log, String mdp, Connection c, Statement st) {
        this.url = url;
        this.log = log;
        this.mdp = mdp;
        this.c = c;
        this.st = st;
    }

    public void seConnecter() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = (Connection) DriverManager.getConnection(url, log, mdp);
            st = (Statement) c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seConnecterSecondeBDD() {
        url = "jdbc:mysql://localhost/movedb"; 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = (Connection) DriverManager.getConnection(url, log, mdp);
            st = (Statement) c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Statement getSt() {
        return st;
    }

    public Connection getC() {
        return c;
    }
}
