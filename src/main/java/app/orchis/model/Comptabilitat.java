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
@Table(name = "comptabilitat")
public class Comptabilitat implements Serializable{
    private static final long serialVersionUID = 13;
    
    //Vars
    @Id
    @Column(name = "codi_comp", unique = true)
    private Integer codi_comp;
    
    @Column(name = "tipus")
    private String tipus;
    
    @Column(name = "cog1")
    private Integer codi_cli;    
    
    @Column(name = "cog2")
    private Integer codi_impost;        
    
    @Column(name = "codi_client")
    private Integer codi_tre;
    
    //GETTERS AND SETTERS
    public Integer getCodi_comp() {
        return codi_comp;
    }

    public void setCodi_comp(Integer codi_comp) {
        this.codi_comp = codi_comp;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public Integer getCodi_cli() {
        return codi_cli;
    }

    public void setCodi_cli(Integer codi_cli) {
        this.codi_cli = codi_cli;
    }

    public Integer getCodi_impost() {
        return codi_impost;
    }

    public void setCodi_impost(Integer codi_impost) {
        this.codi_impost = codi_impost;
    }

    public Integer getCodi_tre() {
        return codi_tre;
    }

    public void setCodi_tre(Integer codi_tre) {
        this.codi_tre = codi_tre;
    }

    
    
}
