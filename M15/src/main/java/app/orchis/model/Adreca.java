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
@Table(name = "adreca")
public class Adreca implements Serializable{
    private static final long serialVersionUID = 10;
    
    //Vars
    @Id
    @Column(name = "codi_adre", unique = true)
    private Integer codi_adre;
    
    @Column(name = "nom_adre")
    private String nom_adre;
    
    @Column(name = "poblacio")
    private String poblacio;   
    
    @Column(name = "centre")
    private String centre;        
    
    @Column(name = "cp")
    private String cp;        
    
    @Column(name = "codi_cli")
    private Integer codi_cli;
    
    @Column(name = "codi_pais")
    private Integer codi_pais;

    @Column(name = "codi_prov")
    private Integer codi_prov;    
    
    @Column(name = "codi_via")
    private Integer codi_via;    
    
    //GETTERS AND SETTERS
    public Integer getCodi_adre() {
        return codi_adre;
    }

    public void setCodi_adre(Integer codi_adre) {
        this.codi_adre = codi_adre;
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

    public Integer getCodi_cli() {
        return codi_cli;
    }

    public void setCodi_cli(Integer codi_cli) {
        this.codi_cli = codi_cli;
    }

    public Integer getCodi_pais() {
        return codi_pais;
    }

    public void setCodi_pais(Integer codi_pais) {
        this.codi_pais = codi_pais;
    }

    public Integer getCodi_prov() {
        return codi_prov;
    }

    public void setCodi_prov(Integer codi_prov) {
        this.codi_prov = codi_prov;
    }

    public Integer getCodi_via() {
        return codi_via;
    }

    public void setCodi_via(Integer codi_via) {
        this.codi_via = codi_via;
    }
    
    
    
}
