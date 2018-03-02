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
@Table(name = "departament")
public class Departament implements Serializable{

    private static final long serialVersionUID = 15;
    
    //Vars
    @Id
    @Column(name = "codi_dep", unique = true)
    private Integer codi_dep;
    
    @Column(name = "nom")
    private String nom;
    
    
    //GETTERS AND SETTERS
    public Integer getCodi_dep() {
        return codi_dep;
    }

    public void setCodi_dep(Integer codi_dep) {
        this.codi_dep = codi_dep;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    
}
