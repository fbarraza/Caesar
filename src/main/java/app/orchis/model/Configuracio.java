/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
    
    //Constructors
    public Configuracio(int codi, int intents, String nom_admin, String mail, int caducitat) {
        this.codi = codi;
        this.intents = intents;
        this.nom_admin = nom_admin;
        this.mail = mail;
        this.caducitat = caducitat;
    }

    public Configuracio() {
    }

    
    //Funcions
    public int obtenirIntents(EntityManagerFactory emf){
        //Manager local
        EntityManager _manager = emf.createEntityManager();

        //Obtenir dades de l'usuari introduit
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaQuery<Configuracio> cbQuery = cb.createQuery(Configuracio.class);
        Root<Configuracio> c = cbQuery.from(Configuracio.class);
        cbQuery.select(c);
        Query query = _manager.createQuery(cbQuery);  
        
        Configuracio config = (Configuracio) query.getSingleResult();
        return config.getIntents();
    }    
    
    //GETTERS AND SETTERS
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
