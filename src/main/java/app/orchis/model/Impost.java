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
@Table(name = "impost")
public class Impost implements Serializable{
    private static final long serialVersionUID = 17;
    
    //Vars
    @Id
    @Column(name = "codi_impost", unique = true)
    private Integer codi_impost;
    
    @Column(name = "nom", nullable=false)
    private String nom;
    
    @Column(name = "valor")
    private Float valor;        
    
    //GETTERS AND SETTERS  
    public Integer getCodi_impost() {
        return codi_impost;
    }

    public void setCodi_impost(Integer codi_impost) {
        this.codi_impost = codi_impost;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    
    
}
