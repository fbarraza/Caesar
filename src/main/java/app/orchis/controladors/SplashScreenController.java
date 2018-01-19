/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.classes.AppConfig;
import app.orchis.model.MasterModel;
import app.orchis.model.Usuari;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.persistence.EntityManagerFactory;


/**
 *
 * @author m15
 */
public class SplashScreenController extends MasterController implements Initializable{

    //Vars FXML
    @FXML private AnchorPane rootPane;
    
    //Vars programa
    private final long SLEEP = 3000;
    
    private void obteDadesApp() {
        AppConfig appConfig = AppConfig.getInstance();
        appConfig.setPersistenceUnit("app.orchis.persistencia");

        try {
            emf = appConfig.loadAppConfig();
        } catch (Exception e){
            System.out.println("No s'ha pogut inicialitzar l'EMF!");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obteDadesApp();

        new SplashScreen().start();
    }

    // Creen una classe interna (inner class) --> Una classe dins d'una altra
    // Aquesta la definim com un nou Thread d'execució
    class SplashScreen extends Thread {
        // Tota classe que esten de la classe Thread ha de sobreescriure, al menys, el mètode run()
        @Override
        public void run() {
            try {
                // Esperem SLEEP milisegons ...
                Thread.sleep(SLEEP);
            
                // Executa un Runnable en una aplicació JavaFX en un temps no especificat i en el futur.
                // L'objecte s'insereix a una cua d'esdeveniments i retornarà el control, una vegada executat, 
                // immediatament a qui l'ha cridat
                // Els objectes Runnable s'execurtaran en l'ordre de crida
                
                // Les expressions Lambda són una novetat de Java 8.0
                // Si volem utilitzar una expressió Lambda, la signatura del següent mètode seria
                //      Platform.runLater(() -> {
                // Per ser una mica més senzill d'entedre ho deixem en el format anterior
                Platform.runLater(() -> {
                    Parent root = null;
                    try {
                        Stage primaryStage = (Stage)rootPane.getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLLogin.fxml"));
                        root = (Parent)fxmlLoader.load();
                        LoginController controller = fxmlLoader.<LoginController>getController();

                        controller.setEmf(emf);                            
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.setScene(scene);
                        
                        stage.show();
                        primaryStage.close();                        
                    } catch (IOException ex) {
                        Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
}
