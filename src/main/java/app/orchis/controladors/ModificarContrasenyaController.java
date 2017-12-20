/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Usuari;
import static app.orchis.utils.Alertes.info;
import static app.orchis.utils.CryptoHelper.encripta;
import java.awt.Panel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    @FXML private AnchorPane Panel;
    @FXML private PasswordField tfOld;
    @FXML private PasswordField tfNou;
    @FXML private PasswordField tfNou2;
    @FXML private Button btAfegir;
    @FXML private Button btModificar;
    @FXML private Label lbNotif;
    @FXML private Label lbAnterior;
    
    //Vars
    private Usuari user;
    private EntityManagerFactory emf;
    private char opc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            inicialitzaGeneric();
        });        
    }      
    
    private void inicialitzaGeneric(){
        if(opc=='a'){
            tfOld.setVisible(false);
            btModificar.setVisible(false);
        }
        else{
            btAfegir.setVisible(true);
        }
    }
    
    private void comprovaVella() throws IOException{
        if(encripta(tfOld.getText()).equals(user.getPasswd())){
           comprovaNoves(); 
        }
        else{
            lbNotif.setText("La contrasenya antiga no coincideix!");
        }
    }
    
    private void retornaContrassenya() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLAltaGeneric.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AltaUsuarisController controller = fxmlLoader.<AltaUsuarisController>getController();
        
        controller.getUsuari().setPasswd(encripta(tfNou.getText()));
        info("Contrasenya afegida!");
    }
    
    private void tencarFinestra(){
        Stage secondaryStage = (Stage)Panel.getScene().getWindow();
        secondaryStage.close();
    }
    
    private void comprovaNoves() throws IOException{
        if(tfNou.getText().equals(tfNou2.getText())){
            if(opc == 'a'){
                retornaContrassenya();
                tencarFinestra();
            }
            else{
                actualitzaPasswd();
            }
        }
        else{
            lbNotif.setText("Les contrasenyes no coincideixen!");
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
            
            info("Conteasenya actualitzada!");
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
    
    public char getOpc() {
        return opc;
    }

    public void setOpc(char opc) {
        this.opc = opc;
    }    
}
