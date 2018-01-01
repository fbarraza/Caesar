/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Usuari;
import static app.orchis.utils.Alertes.avis;
import static app.orchis.utils.Alertes.info;
import static app.orchis.utils.Alertes.sortir;
import static app.orchis.utils.CryptoHelper.encripta;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;

/**
 *
 * @author m15
 */
public class AltaGenericController implements Initializable{
    
    //Vars Controller
    private Usuari user = new Usuari();
    private EntityManagerFactory emf;
    private char opc;
    private String passwd;
    
    //Vars FXML
    @FXML private Button btAfegir;
    @FXML private Button btModificar;
    @FXML private Button btSortir;
    @FXML private TextField tfId;
    @FXML private TextField tfNom;
    @FXML private PasswordField tfPasswd;
    @FXML private CheckBox cbBloqueig;
    @FXML private TextField tfLogin;
    @FXML private CheckBox cbAdmin;
    @FXML private Label lbId;
    @FXML private Label lbInfo;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            carregaApp(opc);
        });
    }   
    
    private void carregaApp(char opc){
        switch (opc){
            case 'c':
                btAfegir.setVisible(true);
                btAfegir.setDisable(false);
                tfId.setVisible(false);
                lbId.setVisible(false);
                break;
            
            case 'm':
                btModificar.setVisible(true);
                btModificar.setDisable(false);
                carregarUsuari();
                break;
                
            default:
                System.err.println("Error!");
                break;
        }
    }
    
    private void carregarUsuari(){
        tfId.setText(Long.toString(user.getCodi()));
        tfNom.setText(user.getNom());
        cbBloqueig.setSelected(user.isBloquejat());
        tfLogin.setText(user.getLogin());
        cbAdmin.setSelected(user.isAdmin());
        
    }
    
    @FXML
    private void modificarUsuari(){
        //Creació Entity Manager i del CB
        EntityManager manager = emf.createEntityManager();
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaUpdate<Usuari> update = cb.createCriteriaUpdate(Usuari.class);                        
        Root<Usuari> c = update.from(Usuari.class);

        //Sentència SQL        
        update.set("nom", tfNom.getText());
        update.set("bloquejat", cbBloqueig.isSelected());
        update.set("login", tfLogin.getText());
        update.set("admin", cbAdmin.isSelected());
        update.where(cb.equal(c.get("codi"), Integer.parseInt(tfId.getText())));  

        //Actualitzar BBDD
        manager.getTransaction().begin();
        manager.createQuery(update).executeUpdate();
        manager.getTransaction().commit();        
    }
    
    private boolean comprovaCamp(TextField field){
        if(field.getText().isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    
    private void carregaPasswd() throws IOException{
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
                user.setPasswd(controller.getPasswd());
            });
            stage.showAndWait();            
    }
    
    @FXML
    private void crearUsuari() throws ParseException, IOException{
        //Variables
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        //Obtenim les dades de l'usuari
        //Usuari user = new Usuari();
        //user.setCodi(Integer.parseInt(tfId.getText()));
        
        if(!comprovaCamp(tfNom)){
            
            user.setNom(tfNom.getText());
            carregaPasswd();
            user.setBloquejat(cbBloqueig.isSelected());
            
            if(!comprovaCamp(tfLogin)){
                user.setLogin(tfLogin.getText());
                user.setData(user.getAvui());
                user.setAdmin(cbAdmin.isSelected());
                
                //Afegim usuari a la base de dades
                try{
                    em.persist(user);
                    em.getTransaction().commit();   
                    info("Usuari introduït satisfactòriament");      
                }
                catch(HibernateException ex){
                    avis("Error a la hora d'inserir l'usuari! Nom d'usuari ja existeix!");
                    em.getTransaction().rollback();
                    System.out.println(ex.getMessage());
                }                         
            }
            else{
                lbInfo.setText("Falta el nom de l'usuari! (login) ");    
            }
        }       
        else{
            lbInfo.setText("Falta el nom real de l'usuari!");            
        }
        
        if(em.isOpen()){
            em.close();
        }
        
    }
    
    @FXML
    protected void sortirAction() {
        if (sortir() == ButtonType.YES) {
            //Tanca la finestra actual
            Stage stage = (Stage) btSortir.getScene().getWindow();
            stage.close();
        }
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
    
    public void setPasswd(String passwd){
        this.passwd = passwd;
    }
}