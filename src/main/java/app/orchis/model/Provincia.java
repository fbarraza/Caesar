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
@Table(name = "provincia")
public class Provincia implements Serializable{
    private static final long serialVersionUID = 19;
    
    //Vars
    @Id
    @Column(name = "codi_prov", unique = true)
    private Integer codi_prov;
    
    @Column(name = "nom", nullable=false)
    private String nom;
    
    @Column(name = "codi_pais")
    private Integer codi_pais;        
    
    //GETTERS AND SETTERS  
    public Integer getCodi_prov() {
        return codi_prov;
    }

    public void setCodi_prov(Integer codi_prov) {
        this.codi_prov = codi_prov;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getCodi_pais() {
        return codi_pais;
    }

    public void setCodi_pais(Integer codi_pais) {
        this.codi_pais = codi_pais;
    }


    
    
}
