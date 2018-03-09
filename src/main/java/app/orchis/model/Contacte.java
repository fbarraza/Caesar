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
@Table(name = "contacte")
public class Contacte implements Serializable{
    private static final long serialVersionUID = 14;
    
    //Vars
    @Id
    @Column(name = "codi_ctte", unique = true)
    private Integer codi_ctte;
    
    @Column(name = "nom")
    private String nom;
    
    @Column(name = "cog1")
    private String cog1;    
    
    @Column(name = "cog2")
    private String cog2;        
    
    @Column(name = "tel1")
    private String tel1;
    
    @Column(name = "tel2")
    private String tel2;

    @Column(name = "fax")
    private String fax;    
    
    @Column(name = "email")
    private String email;        
    
    @Column(name = "principal")
    private boolean principal;
    
    @Column(name = "codi_cli")
    private Integer codi_cli;
    
    @Column(name = "codi_carrec")
    private Integer codi_carrec;
    
    @Column(name = "codi_dep")
    private Integer codi_dep;
    
    //GETTERS AND SETTERS
    public Integer getCodi_ctte() {
        return codi_ctte;
    }

    public void setCodi_ctte(Integer codi_ctte) {
        this.codi_ctte = codi_ctte;
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

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public Integer getCodi_cli() {
        return codi_cli;
    }

    public void setCodi_cli(Integer codi_cli) {
        this.codi_cli = codi_cli;
    }

    public Integer getCodi_carrec() {
        return codi_carrec;
    }

    public void setCodi_carrec(Integer codi_carrec) {
        this.codi_carrec = codi_carrec;
    }

    public Integer getCodi_dep() {
        return codi_dep;
    }

    public void setCodi_dep(Integer codi_dep) {
        this.codi_dep = codi_dep;
    }




    
}
