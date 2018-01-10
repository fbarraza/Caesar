/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Usuari;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author m15
 */
public class MasterController {
    //Vars
    EntityManagerFactory emf;
    Usuari user;
    
    //Getters and Setters
    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Usuari getUser() {
        return user;
    }

    public void setUser(Usuari user) {
        this.user = user;
    }
    
}
