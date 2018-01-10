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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.persistence.EntityManager;



/**
 *
 * @author m15
 */
public class AltaUsuarisController extends MasterController implements Initializable{
    
    //Variables FXML
    @FXML private TextField tfNom;
    @FXML private TableView<Usuari> tvUsuaris;
    @FXML private TableColumn<Usuari, Integer> colCodi;
    @FXML private TableColumn<Usuari, String> colNom;
    @FXML private TableColumn<Usuari, String> colLogin;
    @FXML private TableColumn<Usuari, Date> colData;
    @FXML private TableColumn<Usuari, Boolean> colBloquejat;
    @FXML private TableColumn<Usuari, Boolean> colAdmin;
    @FXML private Button btPrimer, btAnterior, btSeguent, btFinal;

    //Variables Programa
    private ObservableList<Usuari> dades;
    private int posTaula;
    ContextMenu contextMenu = new ContextMenu();
    MenuItem miModificar = new MenuItem("Modificar Usuari");    
    MenuItem miModificarp = new MenuItem("Modificar Contrasenya");
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColumnes();
        Platform.runLater(() -> {
            //Obtenim els usuaris
            dades = getUsuaris();
            
            //Inicialitzem i omplim la taula
            actualitzaTaula();                         
        });
        
        //Accions dels submenus
        miModificar.setOnAction(e -> {
            try {
                obrirModif();
            } catch (IOException ex) {
                Logger.getLogger(AltaUsuarisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        miModificarp.setOnAction(e -> {
            try {
                canviarContrasenya('b');
            } catch (IOException ex) {
                Logger.getLogger(AltaUsuarisController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(AltaUsuarisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        contextMenu.getItems().addAll(miModificar, miModificarp);
        tvUsuaris.setContextMenu(contextMenu);
        
        //Listener dobleclic
        tvUsuaris.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    //Pels botons <- i ->
                    posTaula = tvUsuaris.getFocusModel().getFocusedIndex();
                    comprovarControls();
                    if(mouseEvent.getClickCount() == 2){
                        try {
                            obrirModif();
                        } catch (IOException ex) {
                            Logger.getLogger(AltaUsuarisController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });        
    }   
    
    /**
     * Actualitza la taula mitjançant un ObservableList.
     */
    private void actualitzaTaula() {
        if (!tvUsuaris.getItems().isEmpty()){
            dades = getUsuaris();
        }
        tvUsuaris.setItems(dades);        
        inicialitzaTaula();
    } 
    
    /**
     * Inicialitzem la taula i afegim listener per filtrar els usuaris.
     */
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
    
    /**
     * Configura les columnes per indicar a quines variables de l'objecte pertanyen.
     */
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
    private void primerAction() {

        posTaula = 0;

        tvUsuaris.requestFocus();
        tvUsuaris.getSelectionModel().select(posTaula);
        tvUsuaris.getFocusModel().focus(posTaula);

        comprovarControls();

    }

    @FXML
    private void anteriorAction() {

        posTaula--;

        tvUsuaris.requestFocus();
        tvUsuaris.getSelectionModel().select(posTaula);
        tvUsuaris.getFocusModel().focus(posTaula);

        comprovarControls();

    }

    @FXML
    private void seguentAction() {

        posTaula++;

        tvUsuaris.requestFocus();
        tvUsuaris.getSelectionModel().select(posTaula);
        tvUsuaris.getFocusModel().focus(posTaula);

        comprovarControls();

    }

    @FXML
    private void finalAction() {

        posTaula = tvUsuaris.getItems().size() - 1;

        tvUsuaris.requestFocus();
        tvUsuaris.getSelectionModel().select(posTaula);
        tvUsuaris.getFocusModel().focus(posTaula);

        comprovarControls();

    }

    private void comprovarControls() {

        //Habilita tots els botons
        btPrimer.setDisable(false);
        btAnterior.setDisable(false);
        btSeguent.setDisable(false);
        btFinal.setDisable(false);

        //Si la posició es el primer element desabilita els botons de primer i anterior
        if (posTaula == 0) {

            btPrimer.setDisable(true);
            btAnterior.setDisable(true);

             //Si la posició es l'ultim element desabilita els botons de seguent i final
        } else if (posTaula == tvUsuaris.getItems().size() - 1) {

            btSeguent.setDisable(true);
            btFinal.setDisable(true);

        }

    }

    
    /**
     * Obre l'interfície genèrica amb la opció de crear.
     * @throws IOException 
     */
    @FXML
    private void obrirCrear() throws IOException{
        char opt = 'c';
        obrirGeneric(opt);
    }    
    
    /**
     * Obre l'interfície genèrica amb la opció de modificar.
     * @throws IOException 
     */    
    @FXML
    private void obrirModif() throws IOException{
        char opt = 'm';
        obrirGeneric(opt);
    }
    
    /**
     * Obté l'usuari seleccionat i l'elimina.
     */
    @FXML
    private void eliminarUsuari(){
        //Eliminar Usuari de la BBDD
        setSeleccionat();
        user.eliminarUsuari(emf);

        //Notificar
        info("Usuari eliminat!");

        //Recarregar taula
        actualitzaTaula();
    }
    
    protected void canviarContrasenya(char opc) throws IOException, ParseException{
        //Obtenir Usuari
        setSeleccionat();
        
        //Interfície canviar contrasenya
        carregaCanvi(opc);         
        
    }        
    
    private void obrirGeneric(char opt) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLAltaGeneric.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AltaGenericController controller = fxmlLoader.<AltaGenericController>getController();

        //Vars
        controller.setEmf(emf);
        controller.setOpc(opt);
        if(opt == 'm'){
            setSeleccionat();
            controller.setUser(user);
        }

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle("Alta Usuaris");
        stage.showAndWait();
        actualitzaTaula();
    }    
    
    protected void carregaCanvi(char opc)throws IOException{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLModificarContrasenya.fxml"));
            Parent root = (Parent) fxmlLoader.load();   
            ModificarContrasenyaController controller = fxmlLoader.<ModificarContrasenyaController>getController();
            
            //
            controller.setOpc(opc);
            controller.setUser(user);
            controller.setEmf(emf);
            
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
    
    //GETTERS AND SETTERS
    /**
     * Obtené una llista completa de tots els usuaris.
     * @return 
     */
    private ObservableList<Usuari> getUsuaris() {
        EntityManager manager = emf.createEntityManager();
        ArrayList<Usuari> llista = (ArrayList<Usuari>) manager.createQuery("FROM " + Usuari.class.getName()).getResultList();
        ObservableList<Usuari> llistaUs = FXCollections.observableArrayList(llista);
        manager.close();
        return llistaUs;
    }
    
    private void setSeleccionat(){
        this.user = tvUsuaris.getSelectionModel().getSelectedItem();
    }
}
