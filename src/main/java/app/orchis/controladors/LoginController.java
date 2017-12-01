/*
 * Somebody
 * once
 * told me
 */
package app.orchis.controladors;


import app.orchis.model.Usuari;
import static app.orchis.utils.CryptoHelper.encripta;
import static app.orchis.utils.CryptoHelper.testPassword;
import static app.orchis.utils.JavaEmail.enviarMissatge;
import static app.orchis.utils.eines.AppPropertiesFileHelper.llegirFitxerPropietats;
import java.net.URL;
import java.util.ResourceBundle;
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
import java.util.List;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.StageStyle;
import javax.persistence.criteria.CriteriaUpdate;
import app.orchis.controladors.MainMenuController;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.apache.commons.configuration.ConfigurationException;

/**
 *
 * @author m15
 */
public class LoginController implements Initializable{

    //Vars elements FXML
    @FXML private AnchorPane Panel;
    @FXML private TextField tfUser;
    @FXML private PasswordField tfPasswd;
    @FXML private Text tfInfo;
    @FXML private Button btLogin;
    
    //Vars pel programa
    private static Map properties = llegirFitxerPropietats("app.properties");
    
    /**
     * Generador de l'arxiu de persistència amb contrassenya encriptada
     * @return / Retorna null si no pot llegir el fitxer
     *         / Retorna el EntityManager amb la contrassenya encriptada si troba els fitxers.
     */
    public static EntityManagerFactory generar(){
        if (properties == null) {
            System.out.println("Error greu. Contacti amb l'administrador");
        } else {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("app.orchis.persistencia", properties);
            return emf;
        }
        return null;
    }
    
    private static final EntityManagerFactory emf = generar();
    private static int intents_n = 3;
    private static Usuari user = new Usuari(); //?
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    } 
    
    //Listener
    @FXML
    private void keyPress(KeyEvent e) throws Exception {
        if (e.getSource().equals(tfUser)) {
            if (e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.TAB))
                if (tfPasswd.getText().isEmpty()) {
                    tfPasswd.requestFocus();
                } else {
                    Login();
                }            
        } else {
            if (e.getCode().equals(KeyCode.ENTER)) {
                Login();
            } 
        }
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
    protected void intents(String username) throws MessagingException, Exception{
        //Restar intent
        intents_n--;
        
        //Programa
        if (intents_n == 0){
            tfInfo.setText("Has fallat el teu login tres vegades! La app ha quedat bloquejada");
            enviarMissatge("Han intentat fer login amb un usuari no existent");
            bloqueigApp();
        }
        else{
            tfInfo.setText("Usuari erroni, tens "+intents_n+" intent(s) restants.");
        }            
    }
    
    /**
     * 
     * @param username / Nom de l'usuari
     * @param userlist / Llista del usuaris
     * @throws MessagingException
     * @throws Exception 
     */
    protected void intents(String username, List<Usuari> userlist) throws MessagingException, Exception {
        intents_n--;
        //Usuari existeix
        if(intents_n == 0){
            tfInfo.setText("L'usuari ha sigut bloquejat ja que has fallat 3 vegades! S'ha informat a l'admin i la app ha quedat bloquejada.");            
            
            //Creació Entity Manager i del CB
            EntityManager manager = emf.createEntityManager();
            CriteriaBuilder cb = emf.getCriteriaBuilder();
            CriteriaUpdate<Usuari> update = cb.createCriteriaUpdate(Usuari.class);                        
            Root<Usuari> c = update.from(Usuari.class);
            
            //Sentència SQL
            update.set("bloquejat", true);
            update.where(cb.equal(c.get("login"), username));  
            
            //Actualitzar BBDD
            manager.getTransaction().begin();
            manager.createQuery(update).executeUpdate();
            manager.getTransaction().commit();
                     
            //Informar administrador
            enviarMissatge("Han intentat fer login amb l'usuari " + username + " i ha quedat bloquejat"); 
            bloqueigApp();    
        }
        else{
            tfInfo.setText("Contrassenya errònia, tens "+intents_n+" intent(s) restants.");              
        }
              
    }
    
    protected void iniciarPrincipal(Usuari user) throws IOException{
        //Vars
        Stage primaryStage = (Stage)Panel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLMainMenu.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        MainMenuController controller = fxmlLoader.<MainMenuController>getController();
        
        //Passar valors de variables
        controller.setUser(user);
        controller.setEntity(emf);
        
        //Iniciar nova finestra i RIP login.
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Sobre l'aplicació");
        stage.initModality(Modality.NONE);
        emf.close();
        primaryStage.close();
        stage.showAndWait();        
    }

    //Botó Sortir
    @FXML
    protected void Surt(){
        emf.close();
        System.exit(0);
    }
    
    //Botó login
    @FXML
    protected void Login() throws ConfigurationException, MessagingException, Exception{
        //Variables del programa
        String username = tfUser.getText();
        String password = encripta(tfPasswd.getText());
        
        //Manager local
        EntityManager _manager = emf.createEntityManager();

        //Obtenir dades de l'usuari introduit
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaQuery<Usuari> cbQuery = cb.createQuery(Usuari.class);
        Root<Usuari> c = cbQuery.from(Usuari.class);
        cbQuery.select(c);
        cbQuery.where(cb.equal(c.get("login"), username));
        Query query = _manager.createQuery(cbQuery);                       
        
        List<Usuari> llista = query.getResultList();
        
        //Mirar si hi han usuaris en la llista      
        if(!llista.isEmpty()){
            //L'usuari introduit existeix
            if(llista.get(0).isBloquejat()){
                usuariBloquejat(username);
            }
            else{
                //Usuari introduit OK
                if(testPassword(password,llista.get(0).getPasswd())){
                    //Passwd OK
                    user = llista.get(0); //Necessari?
                    
                    try{
                            _manager.close();
                            iniciarPrincipal(user);
                    }catch(IOException e){
                        System.out.println("No s'ha trobat el fitxer de la pantalla principal");
                    }catch(Exception e){
                        System.out.println("Error greu de l'aplicació! Comprova que els fitxers FXML tinguin els estils correctes!");
                        System.out.println(e.getCause());
                    }                     
                }
               
                else{
                    intents(username,llista);
                }
            }
        }
        //No s'ha trobat l'usuari,
        else{
            intents(username);
        }
        
        //Fi manager
        if (_manager.isOpen())
            _manager.close();
    }     
}
  