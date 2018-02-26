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
@Table(name = "tresoreria")
public class Tresoreria implements Serializable{
    private static final long serialVersionUID = 20;
    
    //Vars
    @Id
    @Column(name = "codi_tre", unique = true)
    private Integer codi_tre;
    
    @Column(name = "recarrec")
    private Float recarrec;
    
    @Column(name = "iban")
    private String iban;    
    
    @Column(name = "descompte")
    private Float descompte;        
    
    @Column(name = "codi_cli")
    private Integer codi_cli;
    
    @Column(name = "codi_pag")
    private Integer codi_pag;    
    
    //GETTERS AND SETTERS
    public Integer getCodi_tre() {
        return codi_tre;
    }

    public void setCodi_tre(Integer codi_tre) {
        this.codi_tre = codi_tre;
    }

    public Float getRecarrec() {
        return recarrec;
    }

    public void setRecarrec(Float recarrec) {
        this.recarrec = recarrec;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Float getDescompte() {
        return descompte;
    }

    public void setDescompte(Float descompte) {
        this.descompte = descompte;
    }

    public Integer getCodi_cli() {
        return codi_cli;
    }

    public void setCodi_cli(Integer codi_cli) {
        this.codi_cli = codi_cli;
    }

    public Integer getCodi_pag() {
        return codi_pag;
    }

    public void setCodi_pag(Integer codi_pag) {
        this.codi_pag = codi_pag;
    }
}