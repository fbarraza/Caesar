/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Configuracio;
import app.orchis.model.Usuari;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

    //Vars programa
    private static EntityManagerFactory emf;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            omplirText();
        });

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
        update.set("nom_admin", cmbNomAdmin.getSelectionModel().getSelectedItem());
        update.set("caducitat", Integer.parseInt(tfDataCaducitat.getText()));
      
        //Efectuar el commit a la base de dades
        manager.getTransaction().begin();
        manager.createQuery(update).executeUpdate();
        manager.getTransaction().commit();
        manager.close();

    }

    @FXML
    protected void sortirAction() {
        //Tanca la finestra actual
        Stage stage = (Stage) btnSortir.getScene().getWindow();
        stage.close();
     

    }

    private void omplirText() {

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
        omplirCombo();
        //Assignar per defecte al combobox l'usuari actual
        cmbNomAdmin.getSelectionModel().select(configuracio.getNom_admin());
        tfMailAdmin.setText(String.valueOf(configuracio.getMail()));
        tfMaxIntents.setText(String.valueOf(configuracio.getIntents()));
        tfDataCaducitat.setText(String.valueOf(configuracio.getCaducitat()));
        manager.close();

    }

    private void omplirCombo() {

        EntityManager manager = emf.createEntityManager();
        CriteriaBuilder cb = emf.getCriteriaBuilder();

        CriteriaQuery<Usuari> cbQuery = cb.createQuery(Usuari.class);
        Root<Usuari> consulta = cbQuery.from(Usuari.class);
        cbQuery.select(consulta);
        Query query = manager.createQuery(cbQuery);

        List<Usuari> llista = query.getResultList();
        ObservableList<String> data = FXCollections.observableArrayList();

        for (int i = 0; i < llista.size(); i++) {

            data.add(llista.get(i).getLogin());

        }

        cmbNomAdmin.setItems(data);

        manager.close();

    }

    //Setter
    public void setEntity(EntityManagerFactory emf){
        this.emf = emf;
    }
    
    

}
