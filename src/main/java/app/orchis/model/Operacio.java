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
@Table(name = "operacio")
public class Operacio implements Serializable{
    private static final long serialVersionUID = 18;
    
    //Vars
    @Id
    @Column(name = "codi_op", unique = true)
    private Integer codi_op;
    
    @Column(name = "nom", nullable=false)
    private String nom;


    //GETTERS AND SETTERS
    public Integer getCodi_op() {
        return codi_op;
    }

    public void setCodi_op(Integer codi_op) {
        this.codi_op = codi_op;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }        
}
