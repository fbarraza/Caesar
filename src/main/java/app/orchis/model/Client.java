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
@Table(name = "client")
public class Client implements Serializable{
    private static final long serialVersionUID = 3;
    
    //Vars
    @Id
    @Column(name = "codi_cli", unique = true)
    private Integer codi_cli;
    
    @Column(name = "nif")
    private String nif;
    
    @Column(name = "nom_comer")
    private String nom_comer;    
    
    @Column(name = "nom_jur")
    private String nom_jur;        
    
    @Column(name = "codi_pais")
    private Integer codi_pais;
    
    @Column(name = "nom_adre")
    private String nom_adre;

    @Column(name = "poblacio")
    private String poblacio;    
    
    @Column(name = "cp")
    private String cp;        
    
    @Column(name = "codi_via")
    private Integer codi_via;
    
    @Column(name = "codi_prov")
    private Integer codi_prov;
    
    //GETTERS AND SETTERS
    public Integer getCodi_cli() {
        return codi_cli;
    }

    public void setCodi_cli(Integer codi_cli) {
        this.codi_cli = codi_cli;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNom_comer() {
        return nom_comer;
    }

    public void setNom_comer(String nom_comer) {
        this.nom_comer = nom_comer;
    }

    public String getNom_jur() {
        return nom_jur;
    }

    public void setNom_jur(String nom_jur) {
        this.nom_jur = nom_jur;
    }

    public Integer getCodi_pais() {
        return codi_pais;
    }

    public void setCodi_pais(Integer codi_pais) {
        this.codi_pais = codi_pais;
    }

    public String getNom_adre() {
        return nom_adre;
    }

    public void setNom_adre(String nom_adre) {
        this.nom_adre = nom_adre;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }
    
    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Integer getCodi_via() {
        return codi_via;
    }

    public void setCodi_via(Integer codi_via) {
        this.codi_via = codi_via;
    }

    public Integer getCodi_prov() {
        return codi_prov;
    }

    public void setCodi_prov(Integer codi_prov) {
        this.codi_prov = codi_prov;
    }


    
    
}
