/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.MasterModel;
import app.orchis.model.Via;
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
public class AltaViaController extends MasterController implements Initializable{

    @FXML private TableView<Via> tvTipusVia;
    @FXML private TableColumn<Via, Integer> colCodi;
    @FXML private TableColumn<Via, String> colTipus;
    @FXML private TextField tfCodi;    
    @FXML private TextField tfTipus;

    @FXML private Button btnNou, btnGuardar, btnEliminar, btnCancelar;

    private static final int FIRST = 0;
    private MasterModel<Via> helperVia;
    private boolean mode_insercio = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColumnes();
        tvTipusVia.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Via> observable, Via oldValue, Via newValue) -> {
            if (newValue != null) {
                tfCodi.setText(String.valueOf(newValue.getCodi_via()));
                tfTipus.setText(newValue.getTipus_via());
            }
        });
        Platform.runLater(() -> {
            //Obtenim els usuaris
            helperVia = new MasterModel(emf, Via.class);
            inicia();            
        });        


        tvTipusVia.requestFocus();
    }
    
    public void inicia() {
        
        refrescaTaula(FIRST);
        if (tvTipusVia.getItems().isEmpty()) {
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
        colCodi.setCellValueFactory(new PropertyValueFactory<>("codi_via"));
        colTipus.setCellValueFactory(new PropertyValueFactory<>("tipus_via"));
    }

    private void refrescaTaula() {
        tvTipusVia.getItems().removeAll();
        tvTipusVia.getItems().setAll(getVies());
        if (tvTipusVia.getItems().isEmpty()) {
            tfCodi.clear();
            tfTipus.clear();
            btnEliminar.setDisable(true);
            btnGuardar.setDisable(true);
        }
    }

    private void refrescaTaula(int index) {
        refrescaTaula();
        tvTipusVia.requestFocus();
        tvTipusVia.getSelectionModel().select(index);
        tvTipusVia.getFocusModel().focus(index);
    }

    private void refrescaTaula(boolean last) {
        refrescaTaula();
        tvTipusVia.getSelectionModel().selectLast();
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
        tfTipus.requestFocus();
        btnNou.setDisable(true);
        btnGuardar.setDisable(false);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(false);
        mode_insercio = true;
    }
    
    public void cancelar() {
        if (!tvTipusVia.getItems().isEmpty()) {
            Via item = tvTipusVia.getSelectionModel().getSelectedItem();
            tfCodi.setText(String.valueOf(item.getCodi_via()));
            tfTipus.setText(item.getTipus_via());            
            btnNou.setDisable(false);
            btnGuardar.setDisable(false);
            btnEliminar.setDisable(false);

        } else {
            tfCodi.clear();
            tfTipus.clear();
            btnNou.setDisable(false);
            btnGuardar.setDisable(true);
            btnEliminar.setDisable(true);
        }
        btnCancelar.setDisable(true);
        mode_insercio = false;
    }
    
    public void eliminar () {
        if (!tvTipusVia.getItems().isEmpty()) {
            if (advertir("Està segur d'eliminar l'element?") == ButtonType.YES) {
                Via v = tvTipusVia.getSelectionModel().getSelectedItem();
                if (v != null) {
                    int indexActual = tvTipusVia.getItems().indexOf(v);
                    int nouIndex = indexActual - 1;
                    if (indexActual == FIRST)
                        nouIndex = FIRST;
                    helperVia.eliminar(v,true);
                    refrescaTaula(nouIndex);
                }
            }
        }
    }

    public void guardar () {
        if (!tfTipus.getText().isEmpty()) {
            boolean last = false;
            int index = -1;
            if (mode_insercio) {
                Via v = new Via();
                v.setCodi_via(0);
                v.setTipus_via(tfTipus.getText());                
                helperVia.afegir(v,true);
                mode_insercio = false;
                last = true;
            } else {
                Via v = tvTipusVia.getSelectionModel().getSelectedItem();
                index = tvTipusVia.getSelectionModel().getSelectedIndex();
                v.setTipus_via(tfTipus.getText());                

                helperVia.actualitzar(v,true);
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
    
    /**
     * Obté una llista completa de tots els usuaris.
     * @return 
     */
    private ObservableList<Via> getVies() {
        ArrayList<Via> llista = (ArrayList) helperVia.getAll();        
        ObservableList<Via> llistaVi = FXCollections.observableArrayList(llista);
        
        return llistaVi;
    }    
}
