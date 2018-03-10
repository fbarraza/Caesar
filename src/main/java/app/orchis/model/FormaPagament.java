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
@Table(name = "forma_pagament")
public class FormaPagament implements Serializable{
    private static final long serialVersionUID = 16;
    
    //Vars
    @Id
    @Column(name = "codi_pag", unique = true)
    private Integer codi_pag;
    
    @Column(name = "nom", nullable=false)
    private String nom;
    
    @Column(name = "formula")
    private String formula;        
    
    //GETTERS AND SETTERS
    public Integer getCodi_pag() {
        return codi_pag;
    }

    public void setCodi_pag(Integer codi_pag) {
        this.codi_pag = codi_pag;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    
    
}
