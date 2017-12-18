/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Usuari;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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

/**
 *
 * @author m15
 */
public class AltaUsuarisController implements Initializable{
    
    //Variables FXML
    @FXML private TextField tfNom;
    @FXML private TableView<Usuari> tvUsuaris;
    @FXML private TableColumn<Usuari, Integer> colId;
    @FXML private TableColumn<Usuari, String> colNom;
    @FXML private TableColumn<Usuari, String> colLogin;
    @FXML private TableColumn<Usuari, Date> colData;
    @FXML private TableColumn<Usuari, Boolean> colBloqueig;
    @FXML private TableColumn<Usuari, Boolean> colAdmin;

    //Variables Programa
    private EntityManagerFactory emf;
    private Usuari user;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColumnes();
        /*Platform.runLater(() -> {
            adminTool();
            For later
        });*/
    }   
    
    @FXML
    protected void mnuConnectaOnAction (ActionEvent actionEvent) {
        actualitzaTaula();
        goTableItem(0);
    }
    
    @FXML
    protected void mnuSobreOnAction (ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SobreFXML.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ex) {
            //Logger.getLogger(DataGridLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualitzaTaula() {
        if (!tvUsuaris.getItems().isEmpty())
            tvUsuaris.getItems().clear();
        tvUsuaris.getItems().setAll(getUsuaris());
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
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colBloqueig.setCellValueFactory(new PropertyValueFactory<>("bloqueig"));
        colAdmin.setCellValueFactory(new PropertyValueFactory<>("admin"));
    }

    private void goTableItem(int row) {
        tvUsuaris.requestFocus();
        tvUsuaris.scrollTo(row);
        tvUsuaris.getSelectionModel().select(row);
        tvUsuaris.getFocusModel().focus(row);
    }
    
    
    private void tancaBBDD() {
        if (emf.isOpen())
            emf.close();
    }    
    
    private void obrirCrear() throws IOException{
        char opt = 'c';
        obrirGeneric(opt);
    }    
    
    private void obrirModif() throws IOException{
        char opt = 'm';
        obrirGeneric(opt);
    }
    
    private void obrirGeneric(char opt) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLAltaGeneric.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AltaGenericController controller = fxmlLoader.<AltaGenericController>getController();

        //Vars
        controller.setEmf(emf);
        controller.setOpc(opt);
        if(opt == 'm'){
            user = tvUsuaris.getSelectionModel().getSelectedItem();
            controller.setUser(user);
        }

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle("Alta Usuaris");
        stage.show();    
    }    

    /**
     * Getters & Setters
     **/
    private List<Usuari> getUsuaris() {
        EntityManager manager = emf.createEntityManager();
        List<Usuari> llista = (List<Usuari>) manager.createQuery("FROM " + Usuari.class.getName()).getResultList();
        manager.close();
        return llista;
    }
    public EntityManagerFactory getEntity() {
        return emf;
    }

    public void setEntity(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    
    
}
