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
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
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
    
    protected void intents(boolean usuari, String username) throws MessagingException{
        if(!usuari){
            intents_n--;
            if (intents_n == 0){
                tfInfo.setText("Has fallat el teu login tres vegades! S'ha informat a l'admin i app bloquejada");
                enviarmissatge(username);
                bloqueig();
            }
            else{
                tfInfo.setText("Usuari o contrassenya errònia, tens "+intents_n+" intent(s) restants.");
            }            
        }
    }
    
    protected void enviarmissatge(String username) throws AddressException, MessagingException{
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage generateMailMessage;
        
        //Configuració servidor Gmail
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        //Configuració missatge
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("m15orchisserver@gmail.com"));
        generateMailMessage.setSubject("Intent de login");
        String emailBody = "Han intentat iniciar sessió amb l'usuari: " +username + " i ha quedat bloquejat. <br><br> Localhost <br>";
        generateMailMessage.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");
        Transport transport = getMailSession.getTransport("smtp");

        //Enviar missatge amb el compte de gmail.
        transport.connect("smtp.gmail.com", "m15orchisserver@gmail.com", "5t34mWindows");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
	
    }
    
    //Botó login
    @FXML protected void Login(ActionEvent actionEvent) throws ConfigurationException, MessagingException{
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
            intents(login,username);
        }
    }   
}
