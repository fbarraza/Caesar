/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.MasterModel;
import app.orchis.model.Operacio;
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
public class AltaOperacioController extends MasterController implements Initializable{

    @FXML private TableView<Operacio> tvTipusOperacio;
    @FXML private TableColumn<Operacio, Integer> colCodi;    
    @FXML private TableColumn<Operacio, String> colNom;
    @FXML private TextField tfCodi;    
    @FXML private TextField tfNom;

    @FXML private Button btnNou, btnGuardar, btnEliminar, btnCancelar;

    private static final int FIRST = 0;
    private MasterModel<Operacio> helperOp;
    private boolean mode_insercio = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColumnes();
        tvTipusOperacio.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Operacio> observable, Operacio oldValue, Operacio newValue) -> {
            if (newValue != null) {
                tfCodi.setText(String.valueOf(newValue.getCodi_op()));                
                tfNom.setText(newValue.getNom());
            }
        });
        Platform.runLater(() -> {
            //Obtenim els usuaris
            helperOp = new MasterModel(emf, Operacio.class);
            inicia();            
        });        


        tvTipusOperacio.requestFocus();
    }
    
    public void inicia() {
        
        refrescaTaula(FIRST);
        if (tvTipusOperacio.getItems().isEmpty()) {
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
        colCodi.setCellValueFactory(new PropertyValueFactory<>("codi_op"));        
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    }

    private void refrescaTaula() {
        tvTipusOperacio.getItems().removeAll();
        tvTipusOperacio.getItems().setAll(getOperacions());
        if (tvTipusOperacio.getItems().isEmpty()) {
            tfCodi.clear();
            tfNom.clear();            
            btnEliminar.setDisable(true);
            btnGuardar.setDisable(true);
        }
    }

    private void refrescaTaula(int index) {
        refrescaTaula();
        tvTipusOperacio.requestFocus();
        tvTipusOperacio.getSelectionModel().select(index);
        tvTipusOperacio.getFocusModel().focus(index);
    }

    private void refrescaTaula(boolean last) {
        refrescaTaula();
        tvTipusOperacio.getSelectionModel().selectLast();
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
        tfNom.requestFocus();
        btnNou.setDisable(true);
        btnGuardar.setDisable(false);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(false);
        mode_insercio = true;
    }
    
    public void cancelar() {
        if (!tvTipusOperacio.getItems().isEmpty()) {
            Operacio item = tvTipusOperacio.getSelectionModel().getSelectedItem();
            tfCodi.setText(String.valueOf(item.getCodi_op()));
            tfNom.setText(item.getNom());
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
        if (!tvTipusOperacio.getItems().isEmpty()) {
            if (advertir("Està segur d'eliminar l'element?") == ButtonType.YES) {
                Operacio o = tvTipusOperacio.getSelectionModel().getSelectedItem();
                if (o != null) {
                    int indexActual = tvTipusOperacio.getItems().indexOf(o);
                    int nouIndex = indexActual - 1;
                    if (indexActual == FIRST)
                        nouIndex = FIRST;
                    helperOp.eliminar(o,true);
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
                Operacio o = new Operacio();
                o.setCodi_op(0);
                o.setNom(tfNom.getText());
                helperOp.afegir(o,true);
                mode_insercio = false;
                last = true;
            } else {
                Operacio o = tvTipusOperacio.getSelectionModel().getSelectedItem();
                index = tvTipusOperacio.getSelectionModel().getSelectedIndex();
                o.setNom(tfNom.getText());

                helperOp.actualitzar(o,true);
            }

            if (last)
                refrescaTaula(last);
            else
                refrescaTaula(index);

            btnNou.setDisable(false);
            btnGuardar.setDisable(false);
            btnEliminar.setDisable(false);
            btnCancelar.setDisable(true);
        }  else {
            advertir("El camp <nom> és obligatori");
        }
    }    
    
    /**
     * Obté una llista completa de tots els usuaris.
     * @return 
     */
    private ObservableList<Operacio> getOperacions() {
        ArrayList<Operacio> llista = (ArrayList) helperOp.getAll();        
        ObservableList<Operacio> llistaOp = FXCollections.observableArrayList(llista);
        
        return llistaOp;
    }    
}
