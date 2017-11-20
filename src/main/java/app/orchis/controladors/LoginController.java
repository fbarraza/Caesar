/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;


import app.orchis.model.Usuari;
import static app.orchis.utils.CryptoHelper.encripta;
import static app.orchis.utils.CryptoHelper.testPassword;
import app.orchis.utils.EntityMan;
import app.orchis.utils.eines.AppPropertiesFileHelper;
import app.orchis.utils.eines.PropertiesHelperException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    private static int intents_n = 3;
    private static Usuari user = new Usuari();
    private static EntityMan test = new EntityMan();
    
    private static List<Usuari> llista = (List<Usuari>) manager.createQuery("FROM " + Usuari.class.getName()).getResultList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    protected void bloqueig(){
        tfUser.setEditable(false);
        tfPasswd.setEditable(false);
        btLogin.setDisable(true);
        emf.close();
    }
    
    protected void intents(boolean usuari, Usuari user){
        if(!usuari){
            intents_n--;
            if (intents_n == 0){
                tfInfo.setText("Has fallat el teu login tres vegades! S'ha informat a l'admin i app bloquejada");
                enviarmissatge();
                bloqueig();
            }
            else{
                tfInfo.setText("Usuari o contrassenya errònia, tens "+intents_n+" intent(s) restants.");
            }            
        }
    }
    
    protected void enviarmissatge(){
        // Recipient's email ID needs to be mentioned.
        String to = "m15orchisserver@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "m15orchisserver@gmail.com";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);
        try {
           // Create a default MimeMessage object.
           MimeMessage message = new MimeMessage(session);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(from));

           // Set To: header field of the header.
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

           // Set Subject: header field
           message.setSubject("This is the Subject Line!");

           // Now set the actual message
           message.setText("This is actual message");

           // Send message
           Transport.send(message);
           System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
           mex.printStackTrace();
        }        
    }
    
    //Botó login
    @FXML protected void Login(ActionEvent actionEvent) throws ConfigurationException{
        //Variables per obtenir els valors i fer el login
        String username = tfUser.getText();
        String password = encripta(tfPasswd.getText());
        boolean login=false;
        int i,x=0;
        
        
        for(i=0;i<llista.size();i++){
            if(llista.get(i).getLogin().equals(username)){
                login = true;
                if(testPassword(password,llista.get(i).getPasswd())){
                    user = llista.get(i);
                    break;
                }
                else{
                    login = false;
                }
            }
        }
        if(!login){
            intents(login,user);
        }
    }   
}
