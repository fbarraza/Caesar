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
@Table(name = "carrec")
public class Carrec implements Serializable{
    
    private static final long serialVersionUID = 11;
    
    //Vars
    @Id
    @Column(name = "codi_carrec", unique = true)
    private Integer codi_carrec;
    
    @Column(name = "nom")
    private String nom;             

    //GETTERS AND SETTERS
    public Integer getCodi_carrec() {
        return codi_carrec;
    }

    public void setCodi_carrec(Integer codi_carrec) {
        this.codi_carrec = codi_carrec;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }    
}
