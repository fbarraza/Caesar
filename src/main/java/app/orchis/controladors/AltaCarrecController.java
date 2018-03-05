/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Carrec;
import app.orchis.model.MasterModel;;
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
public class AltaCarrecController extends MasterController implements Initializable{
    //Vars    
            
    @FXML private TextField tfCodi, tfNom;
    @FXML private Button btnNou, btnGuardar, btnEliminar, btnCancelar;
    @FXML private TableView<Carrec> tvAltaCarrec;
    @FXML private TableColumn<Carrec, Integer> colCodi;
    @FXML private TableColumn<Carrec, String> colNom;
    
    private static final int FIRST = 0;
    private MasterModel<Carrec> helperAltaCarrec;
    private boolean mode_insercio = false;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColumnes();
        tvAltaCarrec.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Carrec> observable, Carrec oldValue, Carrec newValue) -> {
            if (newValue != null) {
                tfCodi.setText(String.valueOf(newValue.getCodi_carrec()));
                tfNom.setText(newValue.getNom());
            }
        });
        Platform.runLater(() -> {
            //Obtenim els usuaris
            helperAltaCarrec = new MasterModel(emf, Carrec.class);
            inicia();            
        });        

        tvAltaCarrec.requestFocus();
    }
    
    
    //Getters and Setters
    private void configuraColumnes() {
        colCodi.setCellValueFactory(new PropertyValueFactory<>("codi_carrec"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_carrec"));
    }
    
    public void inicia() {
        
        refrescaTaula(FIRST);
        if (tvAltaCarrec.getItems().isEmpty()) {
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
        tvAltaCarrec.getItems().removeAll();
        tvAltaCarrec.getItems().setAll(getCarrecs());
        if (tvAltaCarrec.getItems().isEmpty()) {
            tfCodi.clear();
            tfNom.clear();
            btnEliminar.setDisable(true);
            btnGuardar.setDisable(true);
        }
    }
    
    private void refrescaTaula(int index) {
        refrescaTaula();
        tvAltaCarrec.requestFocus();
        tvAltaCarrec.getSelectionModel().select(index);
        tvAltaCarrec.getFocusModel().focus(index);
    }
    
    private void refrescaTaula(boolean last) {
        refrescaTaula();
        tvAltaCarrec.getSelectionModel().selectLast();
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
        if (!tvAltaCarrec.getItems().isEmpty()) {
            Carrec item = tvAltaCarrec.getSelectionModel().getSelectedItem();
            tfCodi.setText(String.valueOf(item.getCodi_carrec()));
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
        if (!tvAltaCarrec.getItems().isEmpty()) {
            if (advertir("Està segur d'eliminar l'element?") == ButtonType.YES) {
                Carrec c = tvAltaCarrec.getSelectionModel().getSelectedItem();
                if (c != null) {
                    int indexActual = tvAltaCarrec.getItems().indexOf(c);
                    int nouIndex = indexActual - 1;
                    if (indexActual == FIRST)
                        nouIndex = FIRST;
                    helperAltaCarrec.eliminar(c,true);
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
                Carrec c = new Carrec();
                c.setCodi_carrec(0);
                c.setNom(tfNom.getText());                
                helperAltaCarrec.afegir(c,true);
                mode_insercio = false;
                last = true;
            } else {
                Carrec c = tvAltaCarrec.getSelectionModel().getSelectedItem();
                index = tvAltaCarrec.getSelectionModel().getSelectedIndex();
                c.setNom(tfNom.getText());                

                helperAltaCarrec.actualitzar(c,true);
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
    private ObservableList<Carrec> getCarrecs() {
        ArrayList<Carrec> llista = (ArrayList) helperAltaCarrec.getAll();        
        ObservableList<Carrec> llistaCa = FXCollections.observableArrayList(llista);
        
        return llistaCa;
    }
}