/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Adreca;
import app.orchis.model.Client;
import app.orchis.model.MasterModel;
import app.orchis.model.Impost;
import app.orchis.model.Pais;
import app.orchis.model.Provincia;
import app.orchis.model.Via;
import static app.orchis.utils.Alertes.advertir;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author m15
 */
public class AltaAdrecaController extends MasterController implements Initializable{

    @FXML private TableView<Adreca> tvTipusAdreca;
    @FXML private TableColumn<Adreca, String> colCodi;
    @FXML private TableColumn<Adreca, String> colNom;
    @FXML private TableColumn<Adreca, String> colPob;
    @FXML private TableColumn<Adreca, String> colCp;
    @FXML private TableColumn<Adreca, Integer> colCli;
    @FXML private TableColumn<Adreca, Integer> colPais;
    @FXML private TableColumn<Adreca, Integer> colProv;
    @FXML private TableColumn<Adreca, Integer> colVia;    
    @FXML private TextField tfCodi;
    @FXML private TextField tfNom;
    @FXML private TextField tfPob;
    @FXML private TextField tfCp;
    @FXML private ComboBox<String> cbCli;
    @FXML private ComboBox<String> cbPais;
    @FXML private ComboBox<String> cbProv;
    @FXML private ComboBox<String> cbVia;
    

    @FXML private Button btnNou, btnGuardar, btnEliminar, btnCancelar;

    private static final int FIRST = 0;
    private MasterModel<Adreca> helperAd;
    private MasterModel<Client> helperCl;
    private MasterModel<Pais> helperPa;
    private MasterModel<Provincia> helperPr;
    private MasterModel<Via> helperVia;
    private ArrayList<Client> aClient;
    private ArrayList<Pais> aPais;
    private ArrayList<Provincia> aProv;
    private ArrayList<Via> aVia;
    private boolean mode_insercio = false;
    private Map mClient = new HashMap<String, Integer>();
    private Map mClient2 = new HashMap<Integer, String>();
    private Map mPais = new HashMap<String, Integer>();
    private Map mPais2 = new HashMap<Integer, String>();
    private Map mProv = new HashMap<String, Integer>();
    private Map mProv2 = new HashMap<Integer, String>();
    private Map mVia = new HashMap<String, Integer>();
    private Map mVia2 = new HashMap<Integer, String>();    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColumnes();
        tvTipusAdreca.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Adreca> observable, Adreca oldValue, Adreca newValue) -> {
            if (newValue != null) {                
                tfCodi.setText(String.valueOf(newValue.getCodi_adre()));                
                tfNom.setText(newValue.getNom_adre());
                tfPob.setText(newValue.getPoblacio());
                tfCp.setText(newValue.getCp());
                cbCli.getSelectionModel().select((String) mClient2.get(newValue.getCodi_cli()));
                cbPais.getSelectionModel().select(newValue.getCodi_pais());
                cbProv.getSelectionModel().select(String.valueOf(newValue.getCodi_prov()));
                cbVia.getSelectionModel().select(String.valueOf(newValue.getCodi_via()));
            }
        });
        Platform.runLater(() -> {
            //Obtenim els usuaris
            helperAd = new MasterModel(emf, Adreca.class);
            helperCl = new MasterModel(emf, Client.class);
            helperPa = new MasterModel(emf, Pais.class);
            helperPr = new MasterModel(emf, Provincia.class);
            helperVia = new MasterModel(emf, Via.class);
            inicia();    
            carregaCb();            
        });        

        tvTipusAdreca.requestFocus();
    }
    
    public void inicia() {
        
        refrescaTaula(FIRST);
        if (tvTipusAdreca.getItems().isEmpty()) {
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
        //Vars                        
        ObservableList<String> dataCl = FXCollections.observableArrayList();
        ObservableList<String> dataPa = FXCollections.observableArrayList();
        ObservableList<String> dataPr = FXCollections.observableArrayList();
        ObservableList<String> dataVi = FXCollections.observableArrayList();

        //Clients
        for (int i = 0; i < aClient.size(); i++) {
            dataCl.add(aClient.get(i).getNom_jur());
            mClient.put(aClient.get(i).getNom_jur(),aClient.get(i).getCodi_cli());
            mClient2.put(aClient.get(i).getCodi_cli(),aClient.get(i).getNom_jur());
        }
        cbCli.setItems(dataCl);        
        
        //Països
        for (int i = 0; i < aPais.size(); i++) {
            dataPa.add(aPais.get(i).getNom());
            mPais.put(aPais.get(i).getNom(),aPais.get(i).getCodi_pais());
            mPais2.put(aPais.get(i).getCodi_pais(),aPais.get(i).getNom());            
        }
        cbPais.setItems(dataPa);
        
        
        //Provincies
        for (int i = 0; i < aProv.size(); i++) {
            dataPr.add(aProv.get(i).getNom());
            mProv.put(aProv.get(i).getNom(),aProv.get(i).getCodi_prov());
            mProv2.put(aProv.get(i).getCodi_prov(),aProv.get(i).getNom());            
        }
        cbProv.setItems(dataPr);        
        
        //Vies
        for (int i = 0; i < aVia.size(); i++) {
            dataVi.add(aVia.get(i).getTipus_via());
            mVia.put(aVia.get(i).getTipus_via(),aClient.get(i).getCodi_via());
            mVia2.put(aVia.get(i).getCodi_via(),aVia.get(i).getTipus_via());            
        }
        cbVia.setItems(dataVi);                                             
        
    }

    private void configuraColumnes() {
        colCodi.setCellValueFactory(new PropertyValueFactory<>("codi_adre"));        
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_adre"));
        colPob.setCellValueFactory(new PropertyValueFactory<>("poblacio"));
        colCp.setCellValueFactory(new PropertyValueFactory<>("cp"));
        colCli.setCellValueFactory(new PropertyValueFactory<>("codi_cli"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("codi_pais"));
        colProv.setCellValueFactory(new PropertyValueFactory<>("codi_prov"));        
        colVia.setCellValueFactory(new PropertyValueFactory<>("codi_via"));    
    }

    private void netejaTf(){
        tfCodi.clear();
        tfNom.clear();
        tfPob.clear();
        tfCp.clear();
        cbCli.getSelectionModel().selectFirst();
        cbPais.getSelectionModel().selectFirst();
        cbProv.getSelectionModel().selectFirst();
        cbVia.getSelectionModel().selectFirst();
    }
    private void refrescaTaula() {
        tvTipusAdreca.getItems().removeAll();
        tvTipusAdreca.getItems().setAll(getAdreces());
        if (tvTipusAdreca.getItems().isEmpty()) {
            netejaTf();
            btnEliminar.setDisable(true);
            btnGuardar.setDisable(true);
        }
    }

    private void refrescaTaula(int index) {
        refrescaTaula();
        tvTipusAdreca.requestFocus();
        tvTipusAdreca.getSelectionModel().select(index);
        tvTipusAdreca.getFocusModel().focus(index);
    }

    private void refrescaTaula(boolean last) {
        refrescaTaula();
        tvTipusAdreca.getSelectionModel().selectLast();
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
        if (!tvTipusAdreca.getItems().isEmpty()) {
            Adreca item = tvTipusAdreca.getSelectionModel().getSelectedItem();
            tfCodi.setText(String.valueOf(item.getCodi_adre()));
            tfNom.setText(item.getNom_adre());
            tfPob.setText(item.getPoblacio());
            tfCp.setText(item.getCp());
            //cbCli.setText(String.valueOf(item.getCodi_cli()));
            //cbPais.setText(String.valueOf(item.getCodi_pais()));
            //cbProv.setText(String.valueOf(item.getCodi_prov()));
            //cbVia.setText(String.valueOf(item.getCodi_via()));
                        
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
        if (!tvTipusAdreca.getItems().isEmpty()) {
            if (advertir("Està segur d'eliminar l'element?") == ButtonType.YES) {
                Adreca a = tvTipusAdreca.getSelectionModel().getSelectedItem();
                if (a != null) {
                    int indexActual = tvTipusAdreca.getItems().indexOf(a);
                    int nouIndex = indexActual - 1;
                    if (indexActual == FIRST)
                        nouIndex = FIRST;
                    helperAd.eliminar(a,true);
                    refrescaTaula(nouIndex);
                }
            }
        }
    }

    public void guardar () {
        if (!tfPob.getText().isEmpty()) {
            if(!tfNom.getText().isEmpty()){
                boolean last = false;
                int index = -1;
                if (mode_insercio) {
                    Adreca a = new Adreca();
                    a.setCodi_adre(0);                    
                    a.setNom_adre(tfNom.getText());
                    a.setCp(tfCp.getText());
                    a.setCodi_cli(Integer.parseInt(tfCp.getText()));
                    //a.setCodi_pais(Integer.parseInt(cbPais.getText()));
                    //a.setCodi_prov(Integer.parseInt(cbProv.getText()));
                    //a.setCodi_via(Integer.parseInt(cbVia.getText()));
                    helperAd.afegir(a,true);
                    mode_insercio = false;
                    last = true;
                } else {
                    Adreca a = tvTipusAdreca.getSelectionModel().getSelectedItem();
                    index = tvTipusAdreca.getSelectionModel().getSelectedIndex();
                    a.setCodi_adre(Integer.parseInt(tfCodi.getText()));    
                    a.setNom_adre(tfNom.getText());
                    a.setCp(tfCp.getText());
                    a.setCodi_cli(Integer.parseInt(tfCp.getText()));
                    //a.setCodi_pais(Integer.parseInt(cbPais.getText()));
                    //a.setCodi_prov(Integer.parseInt(cbProv.getText()));
                    //a.setCodi_via(Integer.parseInt(cbVia.getText()));

                    helperAd.actualitzar(a,true);
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
                advertir("El camp <abreviatura> és obligatori");                
            }
        } else {
            advertir("El camp <nom> és obligatori");
        }
    }    
    
    private Integer trobaEquiv(ArrayList a, int codi){
        
        
        return null;
    }
    
    private void carregaForeign(){
        aClient = (ArrayList) helperCl.getAll();
        aPais = (ArrayList) helperPa.getAll();
        aProv = (ArrayList) helperPr.getAll();
        aVia = (ArrayList) helperVia.getAll();                     
    }
    
    /**
     * Obté una llista completa de tots els usuaris.
     * @return 
     */
    private ObservableList<Adreca> getAdreces() {
        ArrayList<Adreca> llista = (ArrayList) helperAd.getAll();        
        ObservableList<Adreca> llistaAd = FXCollections.observableArrayList(llista);
        
        return llistaAd;
    }    
}
