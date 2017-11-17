package app.orchis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;



@Entity
@Table (name = "user")
public class Usuari implements Serializable{
 

    private static final long serialVersionUID = 1;

    @Id
    @Column(name = "id", unique = true)
    private int codi;

    @Column(name = "nom")
    private String nom;

    @Column(name = "password")
    private String passwd;
    
    @Column(name = "bloquejat")
    private boolean bloquejat;
    
    @Column(name = "login")
    private String login;

    //test
    
    public Usuari() {
    }

    public Usuari(int codi, String nom, String passwd, boolean bloquejat, String login) {
        this.codi = codi;
        this.nom = nom;
        this.passwd = passwd;
        this.bloquejat = bloquejat;
        this.login = login;
    }

    public long getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isBloquejat() {
        return bloquejat;
    }

    public void setBloquejat(boolean bloquejat) {
        this.bloquejat = bloquejat;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "Usuari{" +
                "codi=" + codi +
                ", nom='" + nom + '\'' +
                ", login='" + login + '\'' +
                ", bloquejat=" + bloquejat +
                '}';
    }
    
}
