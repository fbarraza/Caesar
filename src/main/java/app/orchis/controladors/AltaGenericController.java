/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Usuari;
import static app.orchis.utils.CryptoHelper.encripta;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author m15
 */
public class AltaGenericController implements Initializable{
    
    //Vars Controller
    Usuari user = new Usuari();
    EntityManagerFactory emf;
    char opc;
    
    //Vars FXML
    @FXML Button btAfegir;
    @FXML Button btModificar;
    @FXML TextField tfId;
    @FXML TextField tfNom;
    @FXML PasswordField tfPasswd;
    @FXML CheckBox cbBloqueig;
    @FXML TextField tfLogin;
    @FXML CheckBox cbAdmin;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            carregaApp(opc);
        });
    }   
    
    private void carregaApp(char opc){
        switch (opc){
            case 'c':
                btAfegir.setVisible(true);
                btAfegir.setDisable(false);
                break;
            
            case 'm':
                btModificar.setVisible(true);
                btModificar.setDisable(false);
                break;
                
            default:
                System.err.println("Error!");
                break;
        }
    }
    
    private void carregarUsuari(){
        tfId.setText(Long.toString(user.getCodi()));
        tfNom.setText(user.getNom());
        cbBloqueig.setSelected(user.isBloquejat());
        tfLogin.setText(user.getLogin());
        cbAdmin.setSelected(user.isAdmin());
        
    }
    
    private void modificarUsuari(){
        
    }
    
    private void inserirUsuari() throws ParseException{
        //Variables
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        //Obtenim les dades de l'usuari
        Usuari user = new Usuari();
        user.setCodi(Integer.parseInt(tfId.getText()));
        user.setNom(tfNom.getText());
        user.setPasswd(encripta(tfPasswd.getText()));
        user.setBloquejat(cbBloqueig.isSelected());
        user.setLogin(tfLogin.getText());
        user.setData(user.getAvui());
        user.setAdmin(cbAdmin.isSelected());

        //Afegim usuari a la base de dades
        em.persist(user);
        em.getTransaction().commit();    
        
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
