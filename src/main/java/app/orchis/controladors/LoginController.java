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
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaUpdate;

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
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory( "app.orchis.persistencia");
    private static final EntityManager manager = emf.createEntityManager();    
    private static final EntityTransaction tx = manager.getTransaction();
    private static final CriteriaBuilder cb = emf.getCriteriaBuilder();
    private static final CriteriaUpdate<Usuari> update = cb.createCriteriaUpdate(Usuari.class);
    private static final CriteriaQuery<Usuari> cbQuery = cb.createQuery(Usuari.class);
    private static int intents_n = 3;
    private static Usuari user = new Usuari();
    //private static List<Usuari> llista = (List<Usuari>) manager.createQuery("FROM " + Usuari.class.getName()).getResultList();
    
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
    protected void usuariBloquejat(String user){
        tfInfo.setText("L'usuari està bloquejat! No el pots fer servir fins que l'admin el desbloquegi.");
        bloqueigApp();
    }
    
    //Manager d'intents
    protected void intents(String username) throws MessagingException{
        //Restar intent
        intents_n--;
        
        //Programa
        if (intents_n == 0){
            tfInfo.setText("Has fallat el teu login tres vegades! S'ha informat a l'admin i la app ha quedat bloquejada");
            enviarMissatge(username);
            bloqueigApp();
        }
        else{
            tfInfo.setText("Usuari o contrassenya errònia, tens "+intents_n+" intent(s) restants.");
        }            
    }
    
    protected void intents(String username, List<Usuari> userlist) throws MessagingException{
        //Usuari existeix
        if(intents_n == 0){
            tfInfo.setText("L'usuari ha sigut bloquejat ja que has fallat 3 vegades! S'ha informat a l'admin i la app ha quedat bloquejada. FUCK YOU");            
            /*tx.begin();
            userlist.get(0).setBloquejat(true); 
            tx.commit();*/
            Root<Usuari> c = update.from(Usuari.class);
            update.set("bloquejat", true);
            update.where(cb.equal(c.get("login"), username));
            enviarMissatge(username);
            bloqueigApp();                
        }
        else{
            tfInfo.setText("Usuari o contrassenya errònia, tens "+intents_n+" intent(s) restants.");              
        }
              
    }
    
    //Botó login
    @FXML protected void Login(ActionEvent actionEvent) throws ConfigurationException, MessagingException{
        //Variables del programa
        String username = tfUser.getText();
        String password = encripta(tfPasswd.getText());
        boolean login=false;

        //Obtenir dades de l'usuari introduit
        Root<Usuari> c = cbQuery.from(Usuari.class);
        cbQuery.select(c);
        cbQuery.where(cb.equal(c.get("login"), username));

        Query query = manager.createQuery(cbQuery);

        List<Usuari> llista = query.getResultList();
        
        //Mirar usuaris en la llista
        
        if(!llista.isEmpty()){
            //L'suari introduit existeix
            if(llista.get(0).isBloquejat()){
                usuariBloquejat(username);
            }
            else{
                //L'usuari introduit existeix
                login = true;
                if(testPassword(password,llista.get(0).getPasswd())){
                    //Login OK
                    user = llista.get(0); //Necessari?
                    //TODO: Obrir app principal
                }
                else{
                    intents(username,llista);
                }
            }
        }
        
        else{
            intents(username);
        }
        
    }   
}
