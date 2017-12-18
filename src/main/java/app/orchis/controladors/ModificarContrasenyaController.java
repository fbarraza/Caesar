/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Usuari;
import static app.orchis.utils.CryptoHelper.encripta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

/**
 *
 * @author m15
 */
public class ModificarContrasenyaController implements Initializable{
    
    //Vars FXML
    @FXML PasswordField tfOld;
    @FXML PasswordField tfNou;
    @FXML PasswordField tfNou2;
    @FXML Label notif;
    
    //Vars
    private Usuari user;
    private EntityManagerFactory emf;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }      
    
    private void comprovaVella(){
        if(encripta(tfOld.getText()).equals(user.getPasswd())){
           comprovaNoves(); 
        }
        else{
            notif.setText("La contrassenya antiga no coincideix!");
        }
    }
    
    private void comprovaNoves(){
        if(tfNou.getText().equals(tfNou2.getText())){
            actualitzaPasswd();
        }
        else{
            notif.setText("La nova contrassenya no coincideix!");
        }
    }
    
    private void actualitzaPasswd(){
            //Creació Entity Manager i del CB
            EntityManager manager = emf.createEntityManager();
            CriteriaBuilder cb = emf.getCriteriaBuilder();
            CriteriaUpdate<Usuari> update = cb.createCriteriaUpdate(Usuari.class);                        
            Root<Usuari> c = update.from(Usuari.class);
            
            
            //Sentència SQL
            update.set("password", encripta(tfNou2.getText()));
            update.where(cb.equal(c.get("login"), user.getLogin()));  
            
            //Actualitzar BBDD
            manager.getTransaction().begin();
            manager.createQuery(update).executeUpdate();
            manager.getTransaction().commit();        
    }

    //Getters and Setters
    public Usuari getUser() {
        return user;
    }

    public void setUser(Usuari user) {
        this.user = user;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    
}
