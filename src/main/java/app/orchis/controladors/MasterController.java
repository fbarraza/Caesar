/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.MasterModel;
import app.orchis.model.Usuari;
import javax.persistence.EntityManagerFactory;

/**
 *  Controlador mestre, tots els controladors l'hereten
 * @author m15
 */
public class MasterController {
    //Vars
    public EntityManagerFactory emf;
    public Usuari user;
    public MasterModel<Usuari> helperU;
    
    //Getters and Setters

    public MasterModel<Usuari> getHelperU() {
        return helperU;
    }

    public void setHelperU(MasterModel<Usuari> helperU) {
        this.helperU = helperU;
    }    
    
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
