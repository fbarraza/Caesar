/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author m15
 */
@Entity
@Table(name = "admin_config")
public class Configuracio implements Serializable {

    private static final long serialVersionUID = 2;

    @Id
    @Column(name = "ID", unique = true)
    private int codi;

    @Column(name = "bloqueig_intents")
    private int intents;

    @Column(name = "usuari_admin")
    private String nom_admin;

    @Column(name = "mail_admin")
    private String mail;

    @Column(name = "caducitat")
    private int caducitat;

    public Configuracio(int codi, int intents, String nom_admin, String mail, int caducitat) {
        this.codi = codi;
        this.intents = intents;
        this.nom_admin = nom_admin;
        this.mail = mail;
        this.caducitat = caducitat;
    }

    public Configuracio() {
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public int getIntents() {
        return intents;
    }

    public void setIntents(int intents) {
        this.intents = intents;
    }

    public String getNom_admin() {
        return nom_admin;
    }

    public void setNom_admin(String nom_admin) {
        this.nom_admin = nom_admin;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getCaducitat() {
        return caducitat;
    }

    public void setCaducitat(int caducitat) {
        this.caducitat = caducitat;
    }

}
