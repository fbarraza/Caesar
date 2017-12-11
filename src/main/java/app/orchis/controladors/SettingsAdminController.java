/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Configuracio;
import app.orchis.model.Usuari;
import static app.orchis.utils.eines.AppPropertiesFileHelper.llegirFitxerPropietats;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

/**
 *
 * @author infot
 */
public class SettingsAdminController implements Initializable {

    //Variables dels elements en la vista
    @FXML
    private TextField tfID;
    @FXML
    private ComboBox<String> cmbNomAdmin;
    @FXML
    private TextField tfMailAdmin;
    @FXML
    private TextField tfMaxIntents;
    @FXML
    private TextField tfDataCaducitat;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnSortir;

    // @FXML private Button btLogin;
    //Crear un map per agafar les dades del arxiu properties
    private static Map properties = llegirFitxerPropietats("app.properties");

    /**
     * Generador de l'arxiu de persistència amb contrassenya encriptada
     *
     * @return / Retorna null si no pot llegir el fitxer / Retorna el
     * EntityManager amb la contrassenya encriptada si troba els fitxers.
     */
    public static EntityManagerFactory generar() {
        if (properties == null) {
            System.out.println("Error greu. Contacti amb l'administrador");
        } else {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("app.orchis.persistencia", properties);
            return emf;
        }
        return null;
    }

    //Crea el EntityManager q7ue utilitzarem a l'aplicacció
    private static final EntityManagerFactory emf = generar();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        omplirText();

    }

    @FXML
    protected void modificarAction() throws ParseException {

        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

        //Creació Entity Manager i del CB
        EntityManager manager = emf.createEntityManager();
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        //Criteria per el update
        CriteriaUpdate<Configuracio> update = cb.createCriteriaUpdate(Configuracio.class);
        Root<Configuracio> consultaUpdate = update.from(Configuracio.class);

        update.set("mail", tfMailAdmin.getText());
        update.set("intents", Integer.parseInt(tfMaxIntents.getText()));

        //  String dataString = tfDataCaducitat.getText();
        //  Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataString); 
        //  update.set("caducitat", data);
        //   update.set("caducitat", df.parse(tfDataCaducitat.getText()).toString());
        //Efectuar el commit a la base de dades
        manager.getTransaction().begin();
        manager.createQuery(update).executeUpdate();
        manager.getTransaction().commit();
        manager.close();

        omplirText();

    }

    private void omplirText() {

        netejarText();

        //Creació Entity Manager i del CB
        EntityManager manager = emf.createEntityManager();
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        //Criteria per el select
        CriteriaQuery<Configuracio> cbQuery = cb.createQuery(Configuracio.class);
        Root<Configuracio> consulta = cbQuery.from(Configuracio.class);
        cbQuery.select(consulta);
        Query query = manager.createQuery(cbQuery);
        //Ficar totes les dades en un objecte ce configuració
        Configuracio configuracio = (Configuracio) query.getSingleResult();
        //Omplir tots els textField amb el seu valor corresponent
        tfID.setText(Integer.toString(configuracio.getCodi()));

        tfMailAdmin.setText(String.valueOf(configuracio.getMail()));
        tfMaxIntents.setText(String.valueOf(configuracio.getIntents()));
        tfDataCaducitat.setText(String.valueOf(configuracio.getCaducitat()));

    }

    private void omplirCombo() {

        EntityManager manager = emf.createEntityManager();
        CriteriaBuilder cb = emf.getCriteriaBuilder();

        CriteriaQuery<Usuari> cbQuery = cb.createQuery(Usuari.class);
        Root<Usuari> consulta = cbQuery.from(Usuari.class);
        cbQuery.select(consulta);
        Query query = manager.createQuery(cbQuery);

        List<Usuari> llista = query.getResultList();

        for (int i = 0; i < llista.size(); i++) {
            
            cmbNomAdmin.setItems(llista);

        }

    }

    private void netejarText() {

        tfID.clear();

        tfMailAdmin.clear();
        tfMaxIntents.clear();
        tfDataCaducitat.clear();

    }

}
