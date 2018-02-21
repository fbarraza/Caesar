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
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
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
        tvTipusPais.requestFocus();
    }
    
    public void inicia() {
        helperPa = new MasterModel<Pais>(this.getEmf(), Pais.class);
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
        colCodi.setCellValueFactory(new PropertyValueFactory<>("codi"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    }

    private void refrescaTaula() {
        tvTipusPais.getItems().removeAll();
        tvTipusPais.getItems().setAll(helper.getAll());
        if (tvTipusPais.getItems().isEmpty()) {
            tfCodi.clear();
            tfNom.clear();
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
            tfAbreviatura.setText(item.getNom());
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

    @Override
    public void eliminar () {
        if (!tvTipusPais.getItems().isEmpty()) {
            


            if (advertir("Està segur d'eliminar l'element?") == ButtonType.OK) {
                Pais p = tvTipusPais.getSelectionModel().getSelectedItem();
                if (p != null) {
                    int indexActual = tvTipusPais.getItems().indexOf(t);
                    int nouIndex = indexActual - 1;
                    if (indexActual == FIRST)
                        nouIndex = FIRST;
                    helperPa.esborrar(p.getCodi_pais());
                    refrescaTaula(nouIndex);
                }
            }
        }
    }

    @Override
    public void guardar () {
        if (!tfNom.getText().isEmpty()) {
            boolean last = false;
            int index = -1;
            if (mode_insercio) {
                TipusVia t = new TipusVia();
                t.setNom(tfNom.getText());
                helper.insert(t);
                mode_insercio = false;
                last = true;
            } else {
                TipusVia t = tvTipusVia.getSelectionModel().getSelectedItem();
                index = tvTipusVia.getSelectionModel().getSelectedIndex();
                t.setNom(tfNom.getText());

                helper.update(t);
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
            Utilitats.alertaWarning("El camp <nom> és obligatori");
        }
    }    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
