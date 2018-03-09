/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Carrec;
import app.orchis.model.Client;
import app.orchis.model.Contacte;
import app.orchis.model.Departament;
import app.orchis.model.MasterModel;
import static app.orchis.utils.Alertes.advertir;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author m15
 */
public class AltaContacteController extends MasterController implements Initializable {

    @FXML
    private TableView<Contacte> tvTipusContacte;
    @FXML
    private TableColumn<Contacte, Integer> colCodi;
    @FXML
    private TableColumn<Contacte, String> colNom;
    @FXML
    private TableColumn<Contacte, String> colCog1;
    @FXML
    private TableColumn<Contacte, String> colCog2;
    @FXML
    private TableColumn<Contacte, String> colTel1;
    @FXML
    private TableColumn<Contacte, String> colTel2;
    @FXML
    private TableColumn<Contacte, String> colFax;
    @FXML
    private TableColumn<Contacte, String> colEmail;
    @FXML
    private TableColumn<Contacte, Boolean> colPrincipal;
    @FXML
    private TableColumn<Contacte, String> colCli;
    @FXML
    private TableColumn<Contacte, String> colCa;
    @FXML
    private TableColumn<Contacte, String> colDep;

    @FXML
    private TextField tfCodi;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfCog1;
    @FXML
    private TextField tfCog2;
    @FXML
    private TextField tfTel1;
    @FXML
    private TextField tfTel2;
    @FXML
    private TextField tfFax;
    @FXML
    private TextField tfEmail;

    @FXML
    private ComboBox<Client> cbCli;
    @FXML
    private ComboBox<Carrec> cbCa;
    @FXML
    private ComboBox<Departament> cbDep;

    @FXML
    private CheckBox chkPrincipal;

    @FXML
    private Button btnNou, btnGuardar, btnEliminar, btnCancelar;

    private static final int FIRST = 0;
    private MasterModel<Contacte> helperCon;
    private MasterModel<Client> helperCli;
    private MasterModel<Carrec> helperCa;
    private MasterModel<Departament> helperDep;
    private List<Client> aClient;
    private List<Carrec> aCa;
    private List<Departament> aDep;
    private boolean mode_insercio = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColumnes();
        tvTipusContacte.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Contacte> observable, Contacte oldValue, Contacte newValue) -> {
            if (newValue != null) {

                tfCodi.setText(String.valueOf(newValue.getCodi_ctte()));
                tfNom.setText(newValue.getNom());
                tfCog1.setText(newValue.getCog1());
                tfCog2.setText(newValue.getCog2());
                tfTel1.setText(newValue.getTel1());
                tfTel2.setText(newValue.getTel2());
                tfFax.setText(newValue.getFax());
                tfEmail.setText(newValue.getEmail());
                chkPrincipal.setSelected(newValue.isPrincipal());
                cbCli.getSelectionModel().select(newValue.getClient());                
                cbCa.getSelectionModel().select(newValue.getCarrec());
                cbDep.getSelectionModel().select(newValue.getDep());                
            }
        });
        Platform.runLater(() -> {
            //Obtenim els usuaris
            helperCon = new MasterModel(emf, Contacte.class);
            helperCli = new MasterModel(emf, Client.class);
            helperCa = new MasterModel(emf, Carrec.class);
            helperDep = new MasterModel(emf, Departament.class);
            inicia();
        });

        tvTipusContacte.requestFocus();
    }

    public void inicia() {
        
        refrescaTaula(FIRST);
        if (tvTipusContacte.getItems().isEmpty()) {
            btnNou.setDisable(false);
            btnGuardar.setDisable(true);
            btnEliminar.setDisable(true);
            btnCancelar.setDisable(true);
        } else {
            btnNou.setDisable(false);
            btnGuardar.setDisable(false);
            btnEliminar.setDisable(false);
            btnCancelar.setDisable(true);
        }
    }
    
    private void carregaCb(){
        carregaForeign();
        configuraCb();                
    }
    
    private void configuraCb(){
        cbCli.getItems().addAll(aClient);
        cbCa.getItems().addAll(aCa);
        cbDep.getItems().addAll(aDep);
    }

    private void configuraColumnes() {
        colCodi.setCellValueFactory(new PropertyValueFactory<>("codi_ctte"));        
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCog1.setCellValueFactory(new PropertyValueFactory<>("cog1"));
        colCog2.setCellValueFactory(new PropertyValueFactory<>("cog2"));
        colTel1.setCellValueFactory(new PropertyValueFactory<>("tel1"));
        colTel2.setCellValueFactory(new PropertyValueFactory<>("tel2"));
        colFax.setCellValueFactory(new PropertyValueFactory<>("fax"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPrincipal.setCellValueFactory(new PropertyValueFactory<>("principal"));        
        colCli.setCellValueFactory(new PropertyValueFactory<>("cli"));    
        colCa.setCellValueFactory(new PropertyValueFactory<>("ca"));    
        colDep.setCellValueFactory(new PropertyValueFactory<>("dep"));    
    }

    private void netejaTf(){
        tfCodi.clear();
        tfNom.clear();
        tfCog1.clear();
        tfCog2.clear();
        tfTel1.clear();
        tfTel2.clear();
        tfFax.clear();
        tfEmail.clear();
        cbCli.getSelectionModel().select(null);
        cbCa.getSelectionModel().select(null);
        cbDep.getSelectionModel().select(null);
        chkPrincipal.setSelected(false);
    }
    private void refrescaTaula() {
        tvTipusContacte.getItems().removeAll();
        tvTipusContacte.getItems().setAll(getContactes());
        if (tvTipusContacte.getItems().isEmpty()) {
            netejaTf();
            btnEliminar.setDisable(true);
            btnGuardar.setDisable(true);
        }
    }

    private void refrescaTaula(int index) {
        refrescaTaula();
        tvTipusContacte.requestFocus();
        tvTipusContacte.getSelectionModel().select(index);
        tvTipusContacte.getFocusModel().focus(index);
    }

    private void refrescaTaula(boolean last) {
        refrescaTaula();
        tvTipusContacte.getSelectionModel().selectLast();
    }

    @FXML
    private void btnNouOnAction (ActionEvent event) {
        inserir();
    }

    @FXML
    private void btnCancelarOnAction (ActionEvent event) {
        cancelar();
    }

    @FXML
    private void btnEliminarOnAction (ActionEvent event) {
        eliminar();
    }

    @FXML
    private void btnGuardarOnAction (ActionEvent event) {
        guardar();
    }
    
    public void inserir() {
        netejaTf();
        tfNom.requestFocus();
        btnNou.setDisable(true);
        btnGuardar.setDisable(false);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(false);
        mode_insercio = true;
    }
    
    public void cancelar() {
        if (!tvTipusContacte.getItems().isEmpty()) {
            Contacte item = tvTipusContacte.getSelectionModel().getSelectedItem();
            tfCodi.setText(String.valueOf(item.getCodi_ctte()));
            tfNom.setText(String.valueOf(item.getNom()));
            tfCog1.setText(String.valueOf(item.getCog1()));;
            tfCog2.setText(String.valueOf(item.getCog2()));
            tfTel1.setText(String.valueOf(item.getTel1()));
            tfTel2.setText(String.valueOf(item.getTel2()));;
            tfFax.setText(String.valueOf(item.getFax()));
            tfEmail.setText(String.valueOf(item.getEmail()));;
            cbCli.getSelectionModel().select(item.getClient());
            cbCa.getSelectionModel().select(item.getCarrec());
            cbDep.getSelectionModel().select(item.getDep());
            chkPrincipal.setSelected(item.isPrincipal());            
                           
            btnNou.setDisable(false);
            btnGuardar.setDisable(false);
            btnEliminar.setDisable(false);

        } else {
            netejaTf();
            btnNou.setDisable(false);
            btnGuardar.setDisable(true);
            btnEliminar.setDisable(true);
        }
        btnCancelar.setDisable(true);
        mode_insercio = false;
    }
    
    public void eliminar () {
        if (!tvTipusContacte.getItems().isEmpty()) {
            if (advertir("Està segur d'eliminar l'element?") == ButtonType.YES) {
                Contacte c = tvTipusContacte.getSelectionModel().getSelectedItem();
                if (c != null) {
                    int indexActual = tvTipusContacte.getItems().indexOf(c);
                    int nouIndex = indexActual - 1;
                    if (indexActual == FIRST)
                        nouIndex = FIRST;
                    helperCon.eliminar(c,true);
                    refrescaTaula(nouIndex);
                }
            }
        }
    }

    public void guardar () {
        if(!tfNom.getText().isEmpty()){
            boolean last = false;
            int index = -1;
            if (mode_insercio) {
                Contacte c = new Contacte();
                c.setCodi_ctte(0);
                c.setNom(tfNom.getText());
                c.setCog1(tfNom1.getText());
                helperCon.afegir(c,true);
                mode_insercio = false;
                last = true;
            } else {
                Contacte c = tvTipusContacte.getSelectionModel().getSelectedItem();
                index = tvTipusContacte.getSelectionModel().getSelectedIndex();
                a.setCodi_adre(Integer.parseInt(tfCodi.getText()));    
                a.setNom_adre(tfNom.getText());
                a.setCp(tfCp.getText());
                a.setCli(cbCli.getSelectionModel().getSelectedItem());
                a.setPais(cbPais.getSelectionModel().getSelectedItem());
                a.setProv(cbProv.getSelectionModel().getSelectedItem());
                a.setVia(cbVia.getSelectionModel().getSelectedItem());

                helperCon.actualitzar(c,true);
            }

            if (last)
                refrescaTaula(last);
            else
                refrescaTaula(index);

            btnNou.setDisable(false);
            btnGuardar.setDisable(false);
            btnEliminar.setDisable(false);
            btnCancelar.setDisable(true);
        } else {
            advertir("El camp <nom> és obligatori");                
        }        
    }    
    
    private Integer trobaEquiv(ArrayList a, int codi){
        
        
        return null;
    }
    
    private void carregaForeign(){
        aClient = (ArrayList) helperCli.getAll();
        aCa = (ArrayList) helperCa.getAll();
        aDep = (ArrayList) helperDep.getAll();                 
    }
    
    /**
     * Obté una llista completa de tots els usuaris.
     * @return 
     */
    private ObservableList<Contacte> getContactes() {
        ArrayList<Contacte> llista = (ArrayList) helperCon.getAll();        
        ObservableList<Contacte> llistaCo = FXCollections.observableArrayList(llista);
        
        return llistaCo;
    }    
}