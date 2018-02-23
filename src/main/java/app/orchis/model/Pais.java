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

@Entity
@Table(name = "pais")
public class Pais implements Serializable{
    
    private static final long serialVersionUID = 5;
    
    //Vars
    @Id
    @Column(name = "codi_pais", unique = true)
    private Integer codi_pais;
    
    @Column(name = "abreviatura", unique = true)
    private String abreviatura;
    
    @Column(name = "nom", unique = true)
    private String nom;

    public Integer getCodi_pais() {
        return codi_pais;
    }

    //Getters and Setters
    public void setCodi_pais(Integer codi_pais) {
        this.codi_pais = codi_pais;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
}
