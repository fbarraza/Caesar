/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.MasterModel;
import app.orchis.model.Usuari;
import static app.orchis.utils.Alertes.avis;
import static app.orchis.utils.Alertes.info;
import static app.orchis.utils.CryptoHelper.encripta;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author m15
 */
public class ModificarContrasenyaController extends MasterController implements Initializable{
    
    //Vars FXML
    @FXML private AnchorPane Panel;
    @FXML private PasswordField pfAnterior;
    @FXML private PasswordField pfNou;
    @FXML private PasswordField pfNou2;
    @FXML private Button btAfegir;
    @FXML private Button btModificar;
    @FXML private Label lbNotif;
    @FXML private Label lbAnterior;
    
    //Vars
    private char opc;
    private String passwd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            inicialitzaGeneric();
            helperU = new MasterModel(emf, Usuari.class);
        });        
    }      
    
    /**
     * Carrega certs elements depenent de l'opció que s'ha passat.
     */
    private void inicialitzaGeneric(){
        if(opc=='a'){
            btAfegir.setVisible(true);
            pfAnterior.setVisible(false);
            lbAnterior.setVisible(false);
            pfAnterior.requestFocus();
        }
        else{
            btModificar.setVisible(true);
        }
    }
    /**
     * Verifica contrasenya usuari amb la introduïda. 
     * @throws IOException 
     */
    @FXML
    private void comprovaVella() throws IOException{
        System.out.println(pfAnterior.getText());
        System.out.println(user.getPasswd());
        if(encripta(pfAnterior.getText()).equals(user.getPasswd())){
           comprovaNoves(); 
        }
        else{
            lbNotif.setText("La contrasenya antiga no coincideix!");
        }
    }
    
    @FXML
    private void tencarProces(){
        Stage secondaryStage = (Stage)Panel.getScene().getWindow();    
        this.passwd = null;
        secondaryStage.close();        
    }
    
    /**
     * Tenca la finestra i retorna la contrasenya
     */
    private void tencarFinestra(){
        Stage secondaryStage = (Stage)Panel.getScene().getWindow();    
        this.passwd = encripta(pfNou2.getText());        
        secondaryStage.close();
        
    }
    
    /**
     * Comprova si la contrasenya introduïda en els dos camps coincideix.
     * @throws IOException 
     */
    @FXML
    private void comprovaNoves() throws IOException{
        if(pfNou.getText().equals(pfNou2.getText())){
            if(opc == 'a'){
                tencarFinestra();
            }
            else{
                String encriptat = encripta(pfNou2.getText());
                actualitzaPasswd(encriptat);
            }
        }
        else{
            lbNotif.setText("Les contrasenyes no coincideixen!");
        }
    }
    
    /**
     * Actualitza la contrasenya del usuari.
     * @param nou 
     */
    private void actualitzaPasswd(String nou){
        user.setPasswd(nou);
        try{
            helperU.actualitzar(user, false);
            
            info("Contrasenya actualitzada!");
        }catch(Exception ex){
            avis("Error al actualitzar la contrasenya!");
        }
    }
    
    /**
     * "Listener" botons.
     * @param e
     * @throws Exception 
     */
    @FXML
    private void keyPress(KeyEvent e) throws Exception {
        if (e.getSource().equals(pfAnterior)) {
            if (e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.TAB))
                    pfNou.requestFocus();
            }
        
        else if(e.getSource().equals(pfNou)){
            if (e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.TAB)) {
                pfNou2.requestFocus();
            } 
        }
        else if(e.getSource().equals(pfNou2)){
            if (e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.TAB)){
                if(!pfNou.getText().isEmpty()){
                    if(opc=='a'){
                        comprovaNoves();
                    }
                    else{
                        comprovaVella();
                    }
                }
            }
        }
    }      

    //Getters and Setters    
    public char getOpc() {
        return opc;
    }

    public void setOpc(char opc) {
        this.opc = opc;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
