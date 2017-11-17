/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;


import app.orchis.model.Usuari;
import app.orchis.utils.EntityMan;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.configuration.ConfigurationException;

/**
 *
 * @author m15
 */
public class LoginController implements Initializable{

    @FXML private TextField tfUser;
    @FXML private PasswordField tfPasswd;
    @FXML private Text tfInfo;
    @FXML private Button btLogin;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory( "app.orchis.persistencia");
    private static EntityManager manager = emf.createEntityManager();    
    private static int intents_n = 0;
    private static Usuari user = new Usuari();
    private static EntityMan test = new EntityMan();
    
    private static List<Usuari> llista = (List<Usuari>) manager.createQuery("FROM " + Usuari.class.getName()).getResultList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //emf = Persistence.createEntityManagerFactory( "app.orchis.persistencia");
        //manager = emf.createEntityManager();
        
    }
    
    @FXML protected void Login(ActionEvent actionEvent) throws ConfigurationException{
        // Creem el gestor de la persistència
        
        String username = tfUser.getText();        
        boolean found=false;
        int i,x=0;
        
        for(i=0;i<llista.size();i++){
            if(llista.get(i).getLogin().equals(username)){
                found = true;
                x = i;
            }
        }
        
        if(!found){
            intents_n++;
            if (intents_n == 3){
                tfInfo.setText("Has introduit un usuari erroni tres vegades! S'ha informat a l'admin i app bloquejada");
                tfUser.setEditable(false);
                tfPasswd.setEditable(false);
                btLogin.setDisable(true);
                emf.close();
            }
            else{
                tfInfo.setText("Usuari no trobat, tens "+intents_n+" intents restants.");
            }
        }
        
        else{
            user = llista.get(x);
        }
        /*
        for(Usuari u : llista)
        {
            System.out.println(u);
        }

        if (manager.isOpen()) {
            manager.close();
        }
        emf.close();   */     
    }
    
}
