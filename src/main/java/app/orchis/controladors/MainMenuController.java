/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Configuracio;
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
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author m15
 */
public class MainMenuController extends MasterController implements Initializable{

    //Vars element FXML
    @FXML private MenuItem adminConfig;
    @FXML private Menu mnAdminSettings;
    @FXML private Label lbUserActual;
    @FXML private Label lbServer;
    
        
    //Vars programa
    private static Configuracio config;
    private static Iterable<String> IP_HEADER_CANDIDATES;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            adminTool();
            lbServer.setText(Connexio.obteIPConnexio(this.emf));
            lbUserActual.setText(user.getNom());      
        });
    }
    
    /**
     * Obté usuari admin i bloqueja menú admin if usuari is not admin.
     */
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
    
    /**
     * Obre la interfície d'alta de països.
     * @throws IOException 
     */
    @FXML private void obrirAltaPais() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLAltaPais.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AltaPaisController controller = fxmlLoader.<AltaPaisController>getController();

        //Vars
        controller.setEmf(emf);        
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle("Alta Països");
        stage.setResizable(false);
        stage.show();        
        
    }    
    
    /**
     * Obre la interfície d'alta d'usuaris.
     * @throws IOException 
     */
    @FXML private void obrirAltaVia() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLAltaVia.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AltaViaController controller = fxmlLoader.<AltaViaController>getController();

        //Vars
        controller.setEmf(emf);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle("Alta Vies");
        stage.setResizable(false);
        stage.show();        
        
    } 
    
    /**
     * Obre la interfície d'alta d'usuaris.
     * @throws IOException 
     */
    @FXML private void obrirAltaImpost() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLAltaImpost.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AltaImpostController controller = fxmlLoader.<AltaImpostController>getController();

        //Vars
        controller.setEmf(emf);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle("Alta Impostos");
        stage.setResizable(false);
        stage.show();        
        
    }     
    
    /**
     * Obre la interfície d'alta d'usuaris.
     * @throws IOException 
     */
    @FXML private void obrirAltaOperacio() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLAltaOperacio.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AltaOperacioController controller = fxmlLoader.<AltaOperacioController>getController();

        //Vars
        controller.setEmf(emf);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle("Alta Impostos");
        stage.setResizable(false);
        stage.show();        
        
    }      
    
    /**
     * Obre la interfície d'alta d'usuaris.
     * @throws IOException 
     */
    @FXML private void obrirAltaUsuari() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLAltaUsuaris.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AltaUsuarisController controller = fxmlLoader.<AltaUsuarisController>getController();

        //Vars
        controller.setEmf(emf);
        controller.setUserLogin(user);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle("Alta Usuaris");
        stage.setResizable(false);
        stage.show();        
        
    }
    
        @FXML private void obrirAltaCarrec() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLAltaCarrec.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AltaCarrecController controller = fxmlLoader.<AltaCarrecController>getController();

        //Vars
        controller.setEmf(emf);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle("Alta Càrrecs");
        stage.setResizable(false);
        stage.show();        
        
    }
        
        @FXML private void obrirDepartament() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLDepartament.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AltaCarrecController controller = fxmlLoader.<AltaCarrecController>getController();

        //Vars
        controller.setEmf(emf);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle("Alta Departaments");
        stage.setResizable(false);
        stage.show();        
        
    }
    /**
     * Obre la interfície del fitxer de configuració.
     * @throws IOException 
     */
    @FXML
    private void obrirConfig() throws IOException{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLSettingsAdmin.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            SettingsAdminController controller = fxmlLoader.<SettingsAdminController>getController();
            
            //Vars
            controller.setEmf(emf);
            
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
    
}
