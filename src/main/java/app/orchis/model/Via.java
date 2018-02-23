/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author m15
 */
@Entity
@Table (name = "via")
public class Via {
    
    @Id
    @Column(name = "codi_via", unique = true)
    private Integer codi_via;
    
    @Column(name = "tipus_via")
    private String tipus_via;

    //GETTERS AND SETTERS
    public Integer getCodi_via() {
        return codi_via;
    }

    public void setCodi_via(Integer codi_via) {
        this.codi_via = codi_via;
    }

    public String getTipus_via() {
        return tipus_via;
    }

    public void setTipus_via(String tipus_via) {
        this.tipus_via = tipus_via;
    }

    
    
    
}
