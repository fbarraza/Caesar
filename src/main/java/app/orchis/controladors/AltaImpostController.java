/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.MasterModel;
import app.orchis.model.Impost;
import static app.orchis.utils.Alertes.advertir;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author m15
 */
public class AltaImpostController extends MasterController implements Initializable{

    @FXML private TableView<Impost> tvTipusImpost;
    @FXML private TableColumn<Impost, Integer> colCodi;
    @FXML private TableColumn<Impost, String> colNom;
    @FXML private TableColumn<Impost, Float> colValor;
    @FXML private TextField tfCodi;
    @FXML private TextField tfNom;
    @FXML private TextField tfValor;
    

    @FXML private Button btnNou, btnGuardar, btnEliminar, btnCancelar;

    private static final int FIRST = 0;
    private MasterModel<Impost> helperIm;
    private boolean mode_insercio = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColumnes();
        tvTipusImpost.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Impost> observable, Impost oldValue, Impost newValue) -> {
            if (newValue != null) {
                
                tfCodi.setText(String.valueOf(newValue.getCodi_impost()));                
                tfNom.setText(newValue.getNom());
                tfValor.setText(Float.toString(newValue.getValor()));                
            }
        });
        Platform.runLater(() -> {
            //Obtenim els usuaris
            helperIm = new MasterModel(emf, Impost.class);
            inicia();            
        });        


        tvTipusImpost.requestFocus();
    }
    
    public void inicia() {
        
        refrescaTaula(FIRST);
        if (tvTipusImpost.getItems().isEmpty()) {
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

    private void configuraColumnes() {
        colCodi.setCellValueFactory(new PropertyValueFactory<>("codi_impost"));        
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }

    private void refrescaTaula() {
        tvTipusImpost.getItems().removeAll();
        tvTipusImpost.getItems().setAll(getImpostos());
        if (tvTipusImpost.getItems().isEmpty()) {
            tfCodi.clear();
            tfNom.clear();
            tfValor.clear();
            btnEliminar.setDisable(true);
            btnGuardar.setDisable(true);
        }
    }

    private void refrescaTaula(int index) {
        refrescaTaula();
        tvTipusImpost.requestFocus();
        tvTipusImpost.getSelectionModel().select(index);
        tvTipusImpost.getFocusModel().focus(index);
    }

    private void refrescaTaula(boolean last) {
        refrescaTaula();
        tvTipusImpost.getSelectionModel().selectLast();
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
        tfCodi.clear();
        tfNom.clear();
        tfValor.clear();
        tfNom.requestFocus();
        btnNou.setDisable(true);
        btnGuardar.setDisable(false);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(false);
        mode_insercio = true;
    }
    
    public void cancelar() {
        if (!tvTipusImpost.getItems().isEmpty()) {
            Impost item = tvTipusImpost.getSelectionModel().getSelectedItem();
            tfCodi.setText(String.valueOf(item.getCodi_impost()));
            tfNom.setText(item.getNom());
            tfValor.setText(Float.toString(item.getValor()));            
            btnNou.setDisable(false);
            btnGuardar.setDisable(false);
            btnEliminar.setDisable(false);

        } else {
            tfCodi.clear();
            tfNom.clear();
            btnNou.setDisable(false);
            btnGuardar.setDisable(true);
            btnEliminar.setDisable(true);
        }
        btnCancelar.setDisable(true);
        mode_insercio = false;
    }
    
    public void eliminar () {
        if (!tvTipusImpost.getItems().isEmpty()) {
            if (advertir("Està segur d'eliminar l'element?") == ButtonType.YES) {
                Impost i = tvTipusImpost.getSelectionModel().getSelectedItem();
                if (i != null) {
                    int indexActual = tvTipusImpost.getItems().indexOf(i);
                    int nouIndex = indexActual - 1;
                    if (indexActual == FIRST)
                        nouIndex = FIRST;
                    helperIm.eliminar(i,true);
                    refrescaTaula(nouIndex);
                }
            }
        }
    }

    public void guardar () {
        if (!tfValor.getText().isEmpty()) {
            if(!tfNom.getText().isEmpty()){
                boolean last = false;
                int index = -1;
                if (mode_insercio) {
                    Impost i = new Impost();
                    i.setCodi_impost(0);                    
                    i.setNom(tfNom.getText());
                    i.setValor(Float.parseFloat(tfValor.getText()));                    
                    helperIm.afegir(i,true);
                    mode_insercio = false;
                    last = true;
                } else {
                    Impost i = tvTipusImpost.getSelectionModel().getSelectedItem();
                    index = tvTipusImpost.getSelectionModel().getSelectedIndex();
                    i.setCodi_impost(Integer.parseInt(tfCodi.getText()));                    
                    i.setNom(tfNom.getText());
                    i.setValor(Float.parseFloat(tfValor.getText()));   

                    helperIm.actualitzar(i,true);
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
    
    /**
     * Obté una llista completa de tots els usuaris.
     * @return 
     */
    private ObservableList<Impost> getImpostos() {
        ArrayList<Impost> llista = (ArrayList) helperIm.getAll();        
        ObservableList<Impost> llistaIm = FXCollections.observableArrayList(llista);
        
        return llistaIm;
    }    
}
