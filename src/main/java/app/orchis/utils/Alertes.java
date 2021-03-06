/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author m15
 */
public class Alertes {
    
    //Missatges de informació
    public static void info(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, text, ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Informació");
        alert.showAndWait();
    }
    
    public static void avis(String text){
        Alert alert = new Alert(Alert.AlertType.WARNING, text, ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.showAndWait();
    }    
    
    public static ButtonType advertir(String text){
        //Alerta
        Alert alert = new Alert(Alert.AlertType.WARNING, text, ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(null);
        alert.setTitle("Alerta!");
        alert.showAndWait();
        
        return alert.getResult();
        
    }    
    
    
    public static ButtonType sortir(){
        //Alerta
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Segur que vols sortir?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(null);
        alert.setTitle("Sortir");
        alert.showAndWait();
        
        return alert.getResult();
        
    }
}
