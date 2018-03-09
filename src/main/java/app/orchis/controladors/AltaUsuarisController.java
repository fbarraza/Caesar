/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.MasterModel;
import app.orchis.model.Usuari;
import static app.orchis.model.Usuari.obteDisp;
import static app.orchis.utils.Alertes.advertir;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    @FXML private Button btPrimer, btAnterior, btSeguent, btFinal, btAfegir, btModificar, btEliminar;

    //Variables Programa
    private ObservableList<Usuari> dades;
    private int posTaula;
    private boolean admin;
    private Usuari userLogin;
    ContextMenu contextMenu = new ContextMenu();
    MenuItem miModificar = new MenuItem("Modificar Usuari");    
    MenuItem miModificarp = new MenuItem("Modificar Contrasenya");
    MasterModel helperU;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColumnes();
        Platform.runLater(() -> {
            //Obtenim els usuaris
            helperU = new MasterModel(emf, Usuari.class);
            dades = getUsuaris();
            
            //Inicialitzem i omplim la taula
            actualitzaTaula();    
            primerAction();
            
        });
        
        //Assigna acció de modificar usuari als submenú miModificar
        miModificar.setOnAction(e -> {
            try {
                obrirModif();
            } catch (IOException ex) {
                Logger.getLogger(AltaUsuarisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //Assigna acció de modificar la contrasenya als submenú miModificararp
        miModificarp.setOnAction(e -> {
            try {
                canviarContrasenya('b');
            } catch (IOException ex) {
                Logger.getLogger(AltaUsuarisController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(AltaUsuarisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //Assignem els submenús al contextMenu
        contextMenu.getItems().addAll(miModificar, miModificarp);
    }   
    /**
     * Comprova si hi ha un usuari admin en la taula.
     */
    private void comprovaAdmin(){
        int i;
        admin = false;
        for(i=0; i<tvUsuaris.getItems().size();i++){
            if(tvUsuaris.getItems().get(i).isAdmin()){
                admin = true;
            }
        }
    }
    
    /**
     * Actualitza la taula mitjançant un ObservableList.
     */
    private void actualitzaTaula() {
        //Obtenim usuaris
        dades = getUsuaris();
        
        //Inserim usuaris en la taula
        tvUsuaris.setItems(dades);   
        
        //Inicialitzem la taula
        inicialitzaTaula();
        
        //Comprova si hi ha un admin
        comprovaAdmin();
    } 
    
    /**
     * Inicialitza la taula i afegeix listener per filtrar els usuaris.
     */
    private void inicialitzaTaula() {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Usuari> filteredData = new FilteredList<>(tvUsuaris.getItems(), p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        tfNom.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(usuaris -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (usuaris.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // El filtre coincideix amb el nom
                } else if (usuaris.getLogin().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // El filtre conincideix amb el codi
                }
                return false; // Does not match.
            });
        });

        
        
        //Assignem el contextMenu a la taula.
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
        
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Usuari> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tvUsuaris.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tvUsuaris.setItems(sortedData);
    }
    
    @FXML
    private void keyPress(KeyEvent e){
        if(e.getCode().equals(KeyCode.UP)){
            if(posTaula >= 1){
                e.consume();
                anteriorAction();
            }
        }
        else if(e.getCode().equals(KeyCode.DOWN)){
            if(posTaula != tvUsuaris.getItems().size() - 1){
                e.consume();
                seguentAction();
            }
        }        
        
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

    //UNUSED
    private void goTableItem(int row) {
        tvUsuaris.requestFocus();
        tvUsuaris.scrollTo(row);
        tvUsuaris.getSelectionModel().select(row);
        tvUsuaris.getFocusModel().focus(row);
    }  
    /**
     * Selecciona el primer usuari de la taula.
     */
    @FXML
    private void primerAction() {

        posTaula = 0;

        tvUsuaris.requestFocus();
        tvUsuaris.getSelectionModel().select(posTaula);
        tvUsuaris.getFocusModel().focus(posTaula);

        comprovarControls();

    }

    /**
     * Selecciona l'usuari anterior de la taula.
     */
    @FXML
    private void anteriorAction() {

        posTaula--;

        tvUsuaris.requestFocus();
        tvUsuaris.getSelectionModel().select(posTaula);
        tvUsuaris.getFocusModel().focus(posTaula);

        comprovarControls();

    }

    /**
     * Selecciona l'usuari següent de la taula.
     */
    @FXML
    private void seguentAction() {

        posTaula++;

        tvUsuaris.requestFocus();
        tvUsuaris.getSelectionModel().select(posTaula);
        tvUsuaris.getFocusModel().focus(posTaula);

        comprovarControls();

    }

    /**
     * Selecciona l'últim usuari de la taula.
     */
    @FXML
    private void finalAction() {

        posTaula = tvUsuaris.getItems().size() - 1;

        tvUsuaris.requestFocus();
        tvUsuaris.getSelectionModel().select(posTaula);
        tvUsuaris.getFocusModel().focus(posTaula);

        comprovarControls();

    }

    /**
     * Activa o desactiva botons depenent del usuari seleccionat.
     */
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
        
        if(comprovaLogin()){
            btEliminar.setDisable(true);
        }
        else{
            btEliminar.setDisable(false);
        }

    }

    private boolean comprovaLogin(){
        if(this.getSeleccionat().getLogin().equals(userLogin.getLogin())){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Obre la interfície genèrica amb la opció de crear.
     * @throws IOException 
     */
    @FXML
    private void obrirCrear() throws IOException{
        char opt = 'c';
        obrirGeneric(opt);
    }    
    
    /**
     * Obre la interfície genèrica amb la opció de modificar.
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
        //Avisar a l'admin
        if (advertir("Segur que vols eliminar l'usuari?") == ButtonType.YES) {
            //Eliminar Usuari de la BBDD
            setSeleccionat();
            helperU.eliminar(user, true);


            //Recarregar taula
            actualitzaTaula();

            //Posició
            if(posTaula!=0){
                anteriorAction();
            }
            else{
                primerAction();
            } 
        }        
    }
    
    /**
     * Obté usuari seleccionat i obra interfície canvi contrasenya.
     * @param opc /Opció per seleccionar si volem modificar o afegir
     * @throws IOException /Excepció de Fitxer
     * @throws ParseException /Excepció de Parse
     */
    protected void canviarContrasenya(char opc) throws IOException, ParseException{
        //Obtenir Usuari
        setSeleccionat();
        
        //Interfície canviar contrasenya
        carregaCanvi(opc);         
        
    }        
    
    /**
     * Obre la interfície genèrica.
     * @param opt
     * @throws IOException 
     */
    private void obrirGeneric(char opt) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLAltaGeneric.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AltaGenericController controller = fxmlLoader.<AltaGenericController>getController();

        //Vars
        controller.setEmf(emf);
        controller.setOpc(opt);
        controller.setUserLogin(userLogin);
        controller.setAdmin(admin);
        setSeleccionat();
        controller.setUser(user);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle("Alta Usuaris");
        stage.showAndWait();
        actualitzaTaula();
    }    
    
    /**
     * Obre la interfície canvi de contrasenya.
     * @param opc
     * @throws IOException 
     */
    protected void carregaCanvi(char opc)throws IOException{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistes/FXMLModificarContrasenya.fxml"));
            Parent root = (Parent) fxmlLoader.load();   
            ModificarContrasenyaController controller = fxmlLoader.<ModificarContrasenyaController>getController();
                        
            controller.setOpc(opc);
            controller.setUser(user);
            controller.setEmf(emf);
            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.setTitle("Introduir contrasenya");
            
            stage.showAndWait();  
    }        
    
    //GETTERS AND SETTERS
    /**
     * Obté una llista completa de tots els usuaris.
     * @return 
     */
    private ObservableList<Usuari> getUsuaris() {
        ArrayList<Usuari> llista = (ArrayList) helperU.getAll();
        llista = (ArrayList) obteDisp(llista);
        ObservableList<Usuari> llistaUs = FXCollections.observableArrayList(llista);
        
        return llistaUs;
    }
    
    private void setSeleccionat(){
        this.user = tvUsuaris.getSelectionModel().getSelectedItem();
    }
    
    private Usuari getSeleccionat(){
        return tvUsuaris.getSelectionModel().getSelectedItem();
    }

    public Usuari getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(Usuari userLogin) {
        this.userLogin = userLogin;
    }
}