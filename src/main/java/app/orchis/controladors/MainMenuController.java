/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Usuari;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author m15
 */
public class MainMenuController implements Initializable{

    //Vars element FXML
    @FXML private MenuItem adminConfig;
    
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
    
    public void obrirConfig() throws IOException{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLSettingsAdmin.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            SettingsAdminController controller = fxmlLoader.<SettingsAdminController>getController();
            
            //Vars
            controller.setEntity(emf);
            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ex) {
              System.out.println("Error en l'execució del fitxer!");
        }
        
    }
}
