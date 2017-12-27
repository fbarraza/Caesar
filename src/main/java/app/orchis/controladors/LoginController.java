package app.orchis.controladors;


import app.orchis.model.Configuracio;
import app.orchis.model.Usuari;
import static app.orchis.utils.Alertes.info;
import static app.orchis.utils.CryptoHelper.encripta;
import static app.orchis.utils.CryptoHelper.testPassword;
import static app.orchis.utils.JavaEmail.enviarMissatge;
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
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.persistence.criteria.CriteriaUpdate;
import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.persistence.NoResultException;

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
    private static EntityManagerFactory emf;
    private static int intents_n;
    private static int intents_m;
    private static Usuari user = new Usuari();
    private static Configuracio config = new Configuracio();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            intents_n = config.obtenirIntents(emf);
            intents_m = intents_n;
        });        
    } 
    
    /**
     * Listener dels TextFields del Login.
     * @param e /Tecla premuda
     * @throws Exception 
     */
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

    protected void actualitzaBD(EntityManager manager, CriteriaUpdate<Usuari> update){
        manager.getTransaction().begin();
        manager.createQuery(update).executeUpdate();
        manager.getTransaction().commit();        
    }
    
    /**
     * Bloqueja l'aplicació i no permet que el client segueixi intentant.
     */
    protected void bloqueigApp(){
        tfUser.setEditable(false);
        tfPasswd.setEditable(false);
        btLogin.setDisable(true);
        emf.close();
    }    
    
    /**
     * Notifica el client que l'usuari introduït està bloquejat.
     * @param user /Usuari introduït
     */
    protected void usuariBloquejat(String user){
        tfInfo.setText("L'usuari està bloquejat! No el pots fer servir fins que l'admin el desbloquegi.");
        bloqueigApp();
    }
    
    protected void carregaCanvi()throws IOException{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLModificarContrasenya.fxml"));
            Parent root = (Parent) fxmlLoader.load();   
            ModificarContrasenyaController controller = fxmlLoader.<ModificarContrasenyaController>getController();
            
            //
            controller.setOpc('a');
            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.setTitle("Introduir contrasenya");
            
            stage.setOnHiding(event -> {
                user.setPasswd(encripta(controller.getPasswd()));                
            });
            stage.showAndWait();  
    }    
    
    protected void canviarContrasenya(EntityManager em) throws IOException{
        //Avisem a l'usuari    
        info("La contrasenya té més de " +config.getCaducitat()+" i s'ha de canviar!");
            
        //Interfície canviar contrasenya
        carregaCanvi();
        
        //Variables per actualitzar
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaUpdate<Usuari> update = cb.createCriteriaUpdate(Usuari.class);                        
        Root<Usuari> c = update.from(Usuari.class);     

        //Sentència SQL        
        update.set("password", user.getPasswd());     
        
        //Actualitzar BBDD
        actualitzaBD(em, update);        
        
    }    
    
    /**
     * Manager d'intents a executar si l'usuari introduït no existeix
     * @param username /Nom usuari
     * @throws MessagingException
     * @throws Exception 
     */
    protected void intents(String username) throws MessagingException, Exception{
        //Restar intent
        intents_n--;
        
        //Programa
        if (intents_n == 0){
            tfInfo.setText("Has fallat el teu login "+intents_m+" vegades! La app ha quedat bloquejada");
            enviarMissatge("Han intentat fer login amb un usuari no existent");
            bloqueigApp();
        }
        else{
            tfInfo.setText("Usuari erroni, tens "+intents_n+" intent(s) restants.");
        }            
    }
    
    /**
     * Manager d'intents a executar si l'usuari introduït existeix
     * @param usuari / Nom de l'usuari
     * @throws MessagingException
     * @throws Exception 
     */
    protected void intents(Usuari usuari) throws MessagingException, Exception {
        //Restart intent
        intents_n--;
        
        if(intents_n == 0){
            tfInfo.setText("L'usuari ha sigut bloquejat ja que has fallat " +intents_m+ " vegades! S'ha informat a l'admin i la app ha quedat bloquejada.");            
            
            //Creació Entity Manager i del CB
            EntityManager manager = emf.createEntityManager();
            CriteriaBuilder cb = emf.getCriteriaBuilder();
            CriteriaUpdate<Usuari> update = cb.createCriteriaUpdate(Usuari.class);                        
            Root<Usuari> c = update.from(Usuari.class);
            
            //Sentència SQL
            update.set("bloquejat", true);
            update.where(cb.equal(c.get("login"), usuari.getLogin()));  
            
            //Actualitzar BBDD
            actualitzaBD(manager, update);
                     
            //Informar administrador
            enviarMissatge("Han intentat fer login amb l'usuari " + usuari.getLogin() + " i ha quedat bloquejat"); 
            bloqueigApp();    
        }
        else{
            tfInfo.setText("Contrassenya errònia, tens "+intents_n+" intent(s) restants.");              
        }
              
    }
    
    /**
     * Carreguem l'interfície principal del programa
     * @param user /Usuari que ha iniciat sessió
     * @throws IOException 
     */
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
        stage.setTitle("Menú Principal");
        stage.initModality(Modality.NONE);
        stage.setOnHiding( event -> {emf.close();} );
        //emf.close();
        primaryStage.close();
        stage.showAndWait();        
    }

    /**
     * Apagar l'aplicació
     */
    @FXML
    protected void Surt(){
        emf.close();
        System.exit(0);
    }
    
    /**
     * Funció per iniciar sessió amb l'usuari que el client ha introduït
     * @throws ConfigurationException
     * @throws MessagingException
     * @throws Exception 
     */
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
        
        try{
            user = (Usuari) query.getSingleResult();
            if(user.isBloquejat()){
                usuariBloquejat(username);
            }
            else{
                //Usuari introduit OK
                if(testPassword(password,user.getPasswd())){
                    try{
                        //Iniciar app principal
                        _manager.close();
                        if(config.checkMonth(user)){
                            canviarContrasenya(_manager);
                        }
                        else{
                            iniciarPrincipal(user);
                        }
                    }catch(IOException e){
                        System.out.println("No s'ha trobat el fitxer de la pantalla principal");
                    }catch(Exception e){
                        System.out.println("Error greu de l'aplicació! Comprova que els fitxers FXML tinguin els estils correctes!");
                        System.out.println(e.getCause());
                    }                     
                }
               
                else{
                   intents(user);
                }
            }            
        }catch(NoResultException ex){
             intents(username);
        }catch(Exception Ex){
            System.out.println("Error fatal en l'execució de l'aplicacio!");
                System.err.println(Ex.getCause());
            System.err.println(Ex.getMessage());
        }
        
        //Fi manager
        if (_manager.isOpen())
            _manager.close();
    }    
    
    public void setEmf(EntityManagerFactory emf){
        this.emf = emf;
    }
}
  