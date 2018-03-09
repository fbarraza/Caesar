/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Departament;
import app.orchis.model.MasterModel;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *  Controlador mestre, tots els controladors l'hereten
 * @author m15
 */
public class AltaDepartamentController extends MasterController implements Initializable{
    //Vars  
            
    @FXML private TextField tfCodi, tfNom;
    @FXML private Button btnNou, btnGuardar, btnEliminar, btnCancelar;
    @FXML private TableView<Departament> tvDepartaments;
    @FXML private TableColumn<Departament, Integer> colCodi;
    @FXML private TableColumn<Departament, String> colNom;
    
    private static final int FIRST = 0;
    private MasterModel<Departament> helperDepartament;
    private boolean mode_insercio = false;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColumnes();
        tvDepartaments.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Departament> observable, Departament oldValue, Departament newValue) -> {
            if (newValue != null) {
                tfCodi.setText(String.valueOf(newValue.getCodi_dep()));
                tfNom.setText(newValue.getNom());
            }
        });
        Platform.runLater(() -> {
            //Obtenim els usuaris
            helperDepartament = new MasterModel(emf, Departament.class);
            inicia();            
        });        

        tvDepartaments.requestFocus();
    }
    
    
    //Getters and Setters
    private void configuraColumnes() {
        colCodi.setCellValueFactory(new PropertyValueFactory<>("codi_dep"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    }
    
    public void inicia() {
        
        refrescaTaula(FIRST);
        if (tvDepartaments.getItems().isEmpty()) {
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
    
    private void refrescaTaula() {
        tvDepartaments.getItems().removeAll();
        tvDepartaments.getItems().setAll(getCarrecs());
        if (tvDepartaments.getItems().isEmpty()) {
            tfCodi.clear();
            tfNom.clear();
            btnEliminar.setDisable(true);
            btnGuardar.setDisable(true);
        }
    }
    
    private void refrescaTaula(int index) {
        refrescaTaula();
        tvDepartaments.requestFocus();
        tvDepartaments.getSelectionModel().select(index);
        tvDepartaments.getFocusModel().focus(index);
    }
    
    private void refrescaTaula(boolean last) {
        refrescaTaula();
        tvDepartaments.getSelectionModel().selectLast();
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
        tfNom.requestFocus();
        btnNou.setDisable(true);
        btnGuardar.setDisable(false);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(false);
        mode_insercio = true;
    }
    
    public void cancelar() {
        if (!tvDepartaments.getItems().isEmpty()) {
            Departament item = tvDepartaments.getSelectionModel().getSelectedItem();
            tfCodi.setText(String.valueOf(item.getCodi_dep()));
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
        if (!tvDepartaments.getItems().isEmpty()) {
            if (advertir("Està segur d'eliminar l'element?") == ButtonType.YES) {
                Departament d = tvDepartaments.getSelectionModel().getSelectedItem();
                if (d != null) {
                    int indexActual = tvDepartaments.getItems().indexOf(d);
                    int nouIndex = indexActual - 1;
                    if (indexActual == FIRST)
                        nouIndex = FIRST;
                    helperDepartament.eliminar(d,true);
                    refrescaTaula(nouIndex);
                }
            }
        }
    }

    public void guardar () {
        if (!tfNom.getText().isEmpty()) {
            boolean last = false;
            int index = -1;
            if (mode_insercio) {
                Departament d = new Departament();
                d.setCodi_dep(0);
                d.setNom(tfNom.getText());      
                helperDepartament.afegir(d,true);
                mode_insercio = false;
                last = true;
            } else {
                Departament d = tvDepartaments.getSelectionModel().getSelectedItem();
                index = tvDepartaments.getSelectionModel().getSelectedIndex();
                d.setNom(tfNom.getText());                

                helperDepartament.actualitzar(d,true);
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
     * Obté una llista completa de tots els càrrecs.
     * @return 
     */
    private ObservableList<Departament> getCarrecs() {
        ArrayList<Departament> llista = (ArrayList) helperDepartament.getAll();        
        ObservableList<Departament> llistaDe = FXCollections.observableArrayList(llista);
        
        return llistaDe;
    }
}