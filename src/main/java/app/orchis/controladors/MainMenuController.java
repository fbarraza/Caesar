/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Configuracio;
import app.orchis.model.Usuari;
import app.orchis.utils.Connexio;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author m15
 */
public class MainMenuController implements Initializable{

    //Vars element FXML
    @FXML private MenuItem adminConfig;
    @FXML private Menu mnAdminSettings;
    @FXML private Label lbUserActual;
    @FXML private Label lbServer;
    
    
    //@FXML private Label usuariActual;
    //@FXML private Label server;
        
    //Vars programa
    private static Usuari user = new Usuari();
    private static Configuracio config;
    private static EntityManagerFactory emf;
    private static Iterable<String> IP_HEADER_CANDIDATES;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            adminTool();
            lbServer.setText(Connexio.obteIPConnexio(this.emf));
            lbUserActual.setText(user.getNom());      
        });
    }
    
    private void adminTool(){
        //Crear EntityManager i CriteriaBuilder
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        
        //Obtenir dades fitxer configuració 
        CriteriaQuery<Configuracio> cbQuery = cb.createQuery(Configuracio.class);
        Root<Configuracio> c = cbQuery.from(Configuracio.class);
        cbQuery.select(c);
        cbQuery.where(cb.equal(c.get("codi"), 1));
        Query query = em.createQuery(cbQuery);  
        config = (Configuracio) query.getSingleResult();
        
        //Comprovar si l'usuari és admin
        if(!config.getNom_admin().equals(user.getLogin())){
            mnAdminSettings.setDisable(true);
        }
    }
    
    @FXML private void obrirAltaUsuari() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLAltaUsuaris.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AltaUsuarisController controller = fxmlLoader.<AltaUsuarisController>getController();

        //Vars
        controller.setEntity(emf);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle("Alta Usuaris");
        stage.show();        
        
    }
    @FXML
    private void obrirConfig() throws IOException{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLSettingsAdmin.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            SettingsAdminController controller = fxmlLoader.<SettingsAdminController>getController();
            
            //Vars
            controller.setEntity(emf);
            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.setTitle("Configuració Admin");
            stage.show();

        } catch (IOException ex) {
              System.out.println("Error en l'execució del fitxer!");
        }
        
    }
    
    //Getters and Setters per passar variables
    public void setUser(Usuari user){
        this.user = user;
    }  
    
    public void setEntity(EntityManagerFactory emf){
        this.emf = emf;
    }    
}
