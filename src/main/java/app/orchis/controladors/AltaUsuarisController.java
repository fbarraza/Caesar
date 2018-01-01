/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Usuari;
import static app.orchis.utils.Alertes.info;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

/**
 *
 * @author m15
 */
public class AltaUsuarisController implements Initializable{
    
    //Variables FXML
    @FXML private TextField tfNom;
    @FXML private TableView<Usuari> tvUsuaris;
    @FXML private TableColumn<Usuari, Integer> colCodi;
    @FXML private TableColumn<Usuari, String> colNom;
    @FXML private TableColumn<Usuari, String> colLogin;
    @FXML private TableColumn<Usuari, Date> colData;
    @FXML private TableColumn<Usuari, Boolean> colBloquejat;
    @FXML private TableColumn<Usuari, Boolean> colAdmin;

    //Variables Programa
    private EntityManagerFactory emf;
    private Usuari usuari;
    private ObservableList<Usuari> dades;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColumnes();
        Platform.runLater(() -> {
            dades = getUsuaris();
            actualitzaTaula();  
        });
    }   
    
    @FXML
    protected void mnuConnectaOnAction (ActionEvent actionEvent) {
        actualitzaTaula();
        goTableItem(0);
    }
    


    private void actualitzaTaula() {
        if (!tvUsuaris.getItems().isEmpty()){
            dades = getUsuaris();
        }
        tvUsuaris.setItems(dades);

        inicialitzaTaula();
    } 
    
    private void inicialitzaTaula() {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Usuari> filteredData = new FilteredList<>(tvUsuaris.getItems(), p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        tfNom.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(productes -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (productes.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // El filtre coincideix amb el nom
                } else if ((String.valueOf(productes.getCodi()).indexOf(lowerCaseFilter) != -1)) {
                    return true; // El filtre conincideix amb el codi
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Usuari> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tvUsuaris.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tvUsuaris.setItems(sortedData);
    }
    
    
    private void configuraColumnes() {
        colCodi.setCellValueFactory(new PropertyValueFactory<>("codi"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colBloquejat.setCellValueFactory(new PropertyValueFactory<>("bloquejat"));
        colAdmin.setCellValueFactory(new PropertyValueFactory<>("admin"));
    }

    private void goTableItem(int row) {
        tvUsuaris.requestFocus();
        tvUsuaris.scrollTo(row);
        tvUsuaris.getSelectionModel().select(row);
        tvUsuaris.getFocusModel().focus(row);
    }  
    
    @FXML
    private void obrirCrear() throws IOException{
        char opt = 'c';
        obrirGeneric(opt);
    }    
    
    @FXML
    private void obrirModif() throws IOException{
        char opt = 'm';
        obrirGeneric(opt);
    }
    
    @FXML
    private void eliminarUsuari(){
            //Creació Entity Manager i del CB
            EntityManager em = emf.createEntityManager();
            CriteriaBuilder cb = emf.getCriteriaBuilder();
            CriteriaDelete<Usuari> delete = cb.createCriteriaDelete(Usuari.class);                        
            Root<Usuari> c = delete.from(Usuari.class);
            
            //Sentència SQL
            getSeleccionat();
            delete.where(cb.equal(c.get("codi"), usuari.getCodi()));
            
            //Actualitzar BBDD
            em.getTransaction().begin();
            em.createQuery(delete).executeUpdate();
            em.getTransaction().commit();  
            
            //Notificar
            info("Usuari eliminat!");
            
            //Recarregar taula
            actualitzaTaula();
            
            //Tencar Entity
            em.close();
    }
    
    private void obrirGeneric(char opt) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLAltaGeneric.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AltaGenericController controller = fxmlLoader.<AltaGenericController>getController();

        //Vars
        controller.setEmf(emf);
        controller.setOpc(opt);
        if(opt == 'm'){
            getSeleccionat();
            controller.setUser(usuari);
        }

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle("Alta Usuaris");
        stage.showAndWait();
        actualitzaTaula();
    }    

    /**
     * Getters & Setters
     **/
    private ObservableList<Usuari> getUsuaris() {
        EntityManager manager = emf.createEntityManager();
        ArrayList<Usuari> llista = (ArrayList<Usuari>) manager.createQuery("FROM " + Usuari.class.getName()).getResultList();
        ObservableList<Usuari> llistaUs = FXCollections.observableArrayList(llista);
        manager.close();
        return llistaUs;
    }
    
    public EntityManagerFactory getEntity() {
        return emf;
    }

    public void setEntity(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private void getSeleccionat(){
        this.usuari = tvUsuaris.getSelectionModel().getSelectedItem();
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }
    
}