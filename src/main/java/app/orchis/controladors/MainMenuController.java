/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Usuari;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author m15
 */
public class MainMenuController implements Initializable{

    //Vars element FXML
    @FXML Button btTest;
    
    //Vars programa
    private static Usuari user = new Usuari();
    private static EntityManagerFactory emf;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // throw new UnsupportedOperationException("Not supported yet."); //choose Tools | Templates.
    }
    
    //Setters per passar variables
    public void setUser(Usuari user){
        this.user = user;
    }
    
    public void setEntity(EntityManagerFactory emf){
        this.emf = emf;
    }
    
    
    
    @FXML public void test(){
        System.out.println(user.getNom());
    }
}
