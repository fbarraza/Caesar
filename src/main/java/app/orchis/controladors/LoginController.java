/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;


import app.orchis.model.Usuari;
import static app.orchis.utils.CryptoHelper.encripta;
import static app.orchis.utils.CryptoHelper.testPassword;
import static app.orchis.utils.JavaEmail.enviarMissatge;
import app.orchis.utils.eines.AppPropertiesFileHelper;
import app.orchis.utils.eines.PropertiesHelperException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.commons.configuration.ConfigurationException;

/**
 *
 * @author m15
 */
public class LoginController implements Initializable{

    //Vars elements FXML
    @FXML private TextField tfUser;
    @FXML private PasswordField tfPasswd;
    @FXML private Text tfInfo;
    @FXML private Button btLogin;
    
    //Vars pel programa
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory( "app.orchis.persistencia");
    private static EntityManager manager = emf.createEntityManager();    
    private static EntityTransaction tx = manager.getTransaction();
    private static int intents_n = 3;
    private static Usuari user = new Usuari();
    private static List<Usuari> llista = (List<Usuari>) manager.createQuery("FROM " + Usuari.class.getName()).getResultList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    //Bloquejar l'aplicació i apagar la connexió EntityManager.
    protected void bloqueigApp(){
        tfUser.setEditable(false);
        tfPasswd.setEditable(false);
        btLogin.setDisable(true);
        emf.close();
    }    
    
    //Missatge a monstrar si l'usuari està bloquejat
    protected void userBloquejat(String user){
        tfInfo.setText("L'usuari està bloquejat! No el pots fer servir fins que l'admin el desbloquegi.");
        bloqueigApp();
    }
    
    //Manager d'intents
    protected void intents(boolean usuari, String username, int i) throws MessagingException{
        //Restar intent
        intents_n--;
        
        //Programa
        if(!usuari){
            if (intents_n == 0){
                tfInfo.setText("Has fallat el teu login tres vegades! S'ha informat a l'admin i la app ha quedat bloquejada");
                enviarMissatge(username);
                bloqueigApp();
            }
            else{
                tfInfo.setText("Usuari o contrassenya errònia, tens "+intents_n+" intent(s) restants.");
            }            
        }
        else{
            if(intents_n == 0){
                tfInfo.setText("L'usuari ha sigut bloquejat ja que has fallat 3 vegades! S'ha informat a l'admin i la app ha quedat bloquejada. FUCK YOU");                                              
                tx.begin();
                llista.get(i).setBloquejat(true); 
                tx.commit();
                enviarMissatge(username);
                bloqueigApp();                
            }
            else{
                tfInfo.setText("Usuari o contrassenya errònia, tens "+intents_n+" intent(s) restants.");              
            }
        }
    }
    
    //Botó login
    @FXML protected void Login(ActionEvent actionEvent) throws ConfigurationException, MessagingException{
        String username = tfUser.getText();
        String password = encripta(tfPasswd.getText());
        boolean login=false,pass=false;
        int i,pos=0;
        
        //Mirar usuaris en la llista
        for(i=0;i<llista.size();i++){
            if(llista.get(i).getLogin().equals(username)){
                //Trencar bucle for si l'usuari està bloquejat
                if(llista.get(i).isBloquejat()){
                    userBloquejat(username);
                    break;
                }
                else{
                    login = true;
                    pos = i;
                    if(testPassword(password,llista.get(i).getPasswd())){
                        pass= true;
                        user = llista.get(i);
                        break;
                    }
                }
            }         
        }
        
        if((!login)||(!pass)){
            intents(login,username,pos);
        }
        
    }   
}
