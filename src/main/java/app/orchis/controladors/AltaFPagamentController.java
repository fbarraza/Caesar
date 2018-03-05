/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.FormaPagament;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author m15
 */
public class AltaFPagamentController extends MasterController implements Initializable{

    @FXML private TableView<FormaPagament> tvTipusPagament;
    @FXML private TableColumn<FormaPagament, Integer> colCodi;    
    @FXML private TableColumn<FormaPagament, String> colNom;
    @FXML private TableColumn<FormaPagament, String> colFormula;
    @FXML private TextField tfCodi;    
    @FXML private TextField tfNom;
    @FXML private TextField tfFormula;

    @FXML private Button btnNou, btnGuardar, btnEliminar, btnCancelar;

    private static final int FIRST = 0;
    private MasterModel<FormaPagament> helperFp;
    private boolean mode_insercio = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColumnes();
        tvTipusPagament.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends FormaPagament> observable, FormaPagament oldValue, FormaPagament newValue) -> {
            if (newValue != null) {
                tfCodi.setText(String.valueOf(newValue.getCodi_pag()));                
                tfNom.setText(newValue.getNom());
                tfFormula.setText(newValue.getFormula());
            }
        });
        Platform.runLater(() -> {
            //Obtenim els usuaris
            helperFp = new MasterModel(emf, FormaPagament.class);
            inicia();            
        });        


        tvTipusPagament.requestFocus();
    }
    
    public void inicia() {
        
        refrescaTaula(FIRST);
        if (tvTipusPagament.getItems().isEmpty()) {
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
        colCodi.setCellValueFactory(new PropertyValueFactory<>("codi_pag"));        
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colFormula.setCellValueFactory(new PropertyValueFactory<>("formula"));
    }

    private void refrescaTaula() {
        tvTipusPagament.getItems().removeAll();
        tvTipusPagament.getItems().setAll(getFpagaments());
        if (tvTipusPagament.getItems().isEmpty()) {
            tfCodi.clear();
            tfNom.clear();        
            tfFormula.clear();
            btnEliminar.setDisable(true);
            btnGuardar.setDisable(true);
        }
    }

    private void refrescaTaula(int index) {
        refrescaTaula();
        tvTipusPagament.requestFocus();
        tvTipusPagament.getSelectionModel().select(index);
        tvTipusPagament.getFocusModel().focus(index);
    }

    private void refrescaTaula(boolean last) {
        refrescaTaula();
        tvTipusPagament.getSelectionModel().selectLast();
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
        tfFormula.clear();
        tfNom.requestFocus();
        btnNou.setDisable(true);
        btnGuardar.setDisable(false);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(false);
        mode_insercio = true;
    }
    
    public void cancelar() {
        if (!tvTipusPagament.getItems().isEmpty()) {
            FormaPagament item = tvTipusPagament.getSelectionModel().getSelectedItem();
            tfCodi.setText(String.valueOf(item.getCodi_pag()));
            tfNom.setText(item.getNom());
            tfFormula.setText(item.getFormula());
            btnNou.setDisable(false);
            btnGuardar.setDisable(false);
            btnEliminar.setDisable(false);

        } else {
            tfCodi.clear();
            tfNom.clear();
            tfFormula.clear();
            btnNou.setDisable(false);
            btnGuardar.setDisable(true);
            btnEliminar.setDisable(true);
        }
        btnCancelar.setDisable(true);
        mode_insercio = false;
    }
    
    public void eliminar () {
        if (!tvTipusPagament.getItems().isEmpty()) {
            if (advertir("Està segur d'eliminar l'element?") == ButtonType.YES) {
                FormaPagament f = tvTipusPagament.getSelectionModel().getSelectedItem();
                if (f != null) {
                    int indexActual = tvTipusPagament.getItems().indexOf(f);
                    int nouIndex = indexActual - 1;
                    if (indexActual == FIRST)
                        nouIndex = FIRST;
                    helperFp.eliminar(f,true);
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
                FormaPagament f = new FormaPagament();
                f.setCodi_pag(0);
                f.setNom(tfNom.getText());
                f.setFormula(tfFormula.getText());
                helperFp.afegir(f,true);
                mode_insercio = false;
                last = true;
            } else {
                FormaPagament f = tvTipusPagament.getSelectionModel().getSelectedItem();
                index = tvTipusPagament.getSelectionModel().getSelectedIndex();
                f.setCodi_pag(Integer.parseInt(tfCodi.getText()));
                f.setNom(tfNom.getText());
                f.setFormula(tfFormula.getText());

                helperFp.actualitzar(f,true);
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
    private ObservableList<FormaPagament> getFpagaments() {
        ArrayList<FormaPagament> llista = (ArrayList) helperFp.getAll();        
        ObservableList<FormaPagament> llistaPag = FXCollections.observableArrayList(llista);
        
        return llistaPag;
    }    
}
