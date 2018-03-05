/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.MasterModel;
import app.orchis.model.Pais;
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
public class AltaPaisController extends MasterController implements Initializable{

    @FXML private TableView<Pais> tvTipusPais;
    @FXML private TableColumn<Pais, Integer> colCodi;
    @FXML private TableColumn<Pais, String> colAbreviatura;
    @FXML private TableColumn<Pais, String> colNom;
    @FXML private TextField tfCodi;
    @FXML private TextField tfAbreviatura;
    @FXML private TextField tfNom;

    @FXML private Button btnNou, btnGuardar, btnEliminar, btnCancelar;

    private static final int FIRST = 0;
    private MasterModel<Pais> helperPa;
    private boolean mode_insercio = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColumnes();
        tvTipusPais.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Pais> observable, Pais oldValue, Pais newValue) -> {
            if (newValue != null) {
                tfCodi.setText(String.valueOf(newValue.getCodi_pais()));
                tfAbreviatura.setText(newValue.getAbreviatura());
                tfNom.setText(newValue.getNom());
            }
        });
        Platform.runLater(() -> {
            //Obtenim els usuaris
            helperPa = new MasterModel(emf, Pais.class);
            inicia();            
        });        


        tvTipusPais.requestFocus();
    }
    
    public void inicia() {
        
        refrescaTaula(FIRST);
        if (tvTipusPais.getItems().isEmpty()) {
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
        colCodi.setCellValueFactory(new PropertyValueFactory<>("codi_pais"));
        colAbreviatura.setCellValueFactory(new PropertyValueFactory<>("abreviatura"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    }

    private void refrescaTaula() {
        tvTipusPais.getItems().removeAll();
        tvTipusPais.getItems().setAll(getPaisos());
        if (tvTipusPais.getItems().isEmpty()) {
            tfCodi.clear();
            tfNom.clear();
            tfAbreviatura.clear();
            btnEliminar.setDisable(true);
            btnGuardar.setDisable(true);
        }
    }

    private void refrescaTaula(int index) {
        refrescaTaula();
        tvTipusPais.requestFocus();
        tvTipusPais.getSelectionModel().select(index);
        tvTipusPais.getFocusModel().focus(index);
    }

    private void refrescaTaula(boolean last) {
        refrescaTaula();
        tvTipusPais.getSelectionModel().selectLast();
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
        tfAbreviatura.clear();
        tfNom.requestFocus();
        btnNou.setDisable(true);
        btnGuardar.setDisable(false);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(false);
        mode_insercio = true;
    }
    
    public void cancelar() {
        if (!tvTipusPais.getItems().isEmpty()) {
            Pais item = tvTipusPais.getSelectionModel().getSelectedItem();
            tfCodi.setText(String.valueOf(item.getCodi_pais()));
            tfAbreviatura.setText(item.getAbreviatura());
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
        if (!tvTipusPais.getItems().isEmpty()) {
            if (advertir("Està segur d'eliminar l'element?") == ButtonType.YES) {
                Pais p = tvTipusPais.getSelectionModel().getSelectedItem();
                if (p != null) {
                    int indexActual = tvTipusPais.getItems().indexOf(p);
                    int nouIndex = indexActual - 1;
                    if (indexActual == FIRST)
                        nouIndex = FIRST;
                    helperPa.eliminar(p,true);
                    refrescaTaula(nouIndex);
                }
            }
        }
    }

    public void guardar () {
        if (!tfAbreviatura.getText().isEmpty()) {
            if(!tfNom.getText().isEmpty()){
                boolean last = false;
                int index = -1;
                if (mode_insercio) {
                    Pais p = new Pais();
                    p.setCodi_pais(0);
                    p.setAbreviatura(tfAbreviatura.getText());
                    p.setNom(tfNom.getText());
                    helperPa.afegir(p,true);
                    mode_insercio = false;
                    last = true;
                } else {
                    Pais p = tvTipusPais.getSelectionModel().getSelectedItem();
                    index = tvTipusPais.getSelectionModel().getSelectedIndex();
                    p.setAbreviatura(tfAbreviatura.getText());
                    p.setNom(tfNom.getText());

                    helperPa.actualitzar(p,true);
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
    private ObservableList<Pais> getPaisos() {
        ArrayList<Pais> llista = (ArrayList) helperPa.getAll();        
        ObservableList<Pais> llistaPa = FXCollections.observableArrayList(llista);
        
        return llistaPa;
    }    
}
