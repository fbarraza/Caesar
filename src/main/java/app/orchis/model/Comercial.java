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
@Table(name = "comercial")
public class Comercial implements Serializable{
    private static final long serialVersionUID = 12;
    
    //Vars
    @Id
    @Column(name = "codi_com", unique = true)
    private Integer codi_com;
    
    @Column(name = "nom")
    private String nom;
    
    @Column(name = "cog1")
    private String cog1;    
    
    @Column(name = "cog2")
    private String cog2;        
    
    @Column(name = "codi_cli")
    private Integer codi_cli;
    
    //GETTERS AND SETTERS
    public Integer getCodi_com() {
        return codi_com;
    }

    public void setCodi_com(Integer codi_com) {
        this.codi_com = codi_com;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCog1() {
        return cog1;
    }

    public void setCog1(String cog1) {
        this.cog1 = cog1;
    }

    public String getCog2() {
        return cog2;
    }

    public void setCog2(String cog2) {
        this.cog2 = cog2;
    }

    public Integer getCodi_cli() {
        return codi_cli;
    }

    public void setCodi_cli(Integer codi_client) {
        this.codi_cli = codi_client;
    }

    
    
}
