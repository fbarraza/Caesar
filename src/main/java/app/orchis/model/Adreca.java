/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "codi_cli")   
    private Client cli;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "codi_pais")  
    private Pais pais;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "codi_prov")  
    private Provincia prov;    
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "codi_via")  
    private Via via;    
    
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

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public Client getCli() {
        return cli;
    }

    public void setCli(Client cli) {
        this.cli = cli;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Provincia getProv() {
        return prov;
    }

    public void setProv(Provincia prov) {
        this.prov = prov;
    }

    public Via getVia() {
        return via;
    }

    public void setVia(Via via) {
        this.via = via;
    }    
}
