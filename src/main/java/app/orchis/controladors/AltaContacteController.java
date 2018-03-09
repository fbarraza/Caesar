/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Contacte;
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
    private TableView<Contacte> tvContacte;
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
    private TableColumn<Contacte, Boolean> Principal;
    @FXML
    private TableColumn<Contacte, String> colClient;
    @FXML
    private TableColumn<Contacte, String> colCarrec;
    @FXML
    private TableColumn<Contacte, String> colDepartament;

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
    private ComboBox cmbClient;
    @FXML
    private ComboBox cmbCarrec;
    @FXML
    private ComboBox cmbDepartament;

    @FXML
    private CheckBox chkPrincipal;

    @FXML
    private Button btnNou, btnGuardar, btnEliminar, btnCancelar;

    private static final int FIRST = 0;
    private MasterModel<Contacte> helperCon;
    private boolean mode_insercio = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configuraColumnes();
        tvContacte.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Contacte> observable, Contacte oldValue, Contacte newValue) -> {
            if (newValue != null) {

                tfCodi.setText(String.valueOf(newValue.getCodi_ctte()));
                tfNom.setText(newValue.getNom());
                tfCog1.setText(newValue.getCog1());
                tfCog2.setText(newValue.getCog2());
                tfTel1.setText(newValue.getTel1());
                tfTel2.setText(newValue.getTel2());
                tfFax.setText(newValue.getFax());
                tfEmail.setText(newValue.getEmail());

            }
        });
        Platform.runLater(() -> {
            //Obtenim els usuaris
            helperCon = new MasterModel(emf, Contacte.class);
            inicia();
        });

        tvContacte.requestFocus();
    }

    public void inicia() {

        refrescaTaula(FIRST);
        if (tvContacte.getItems().isEmpty()) {
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
        colCodi.setCellValueFactory(new PropertyValueFactory<>("codi_ctte"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCog1.setCellValueFactory(new PropertyValueFactory<>("cog1"));
        colCog2.setCellValueFactory(new PropertyValueFactory<>("cog2"));
        colTel1.setCellValueFactory(new PropertyValueFactory<>("tel1"));
        colTel2.setCellValueFactory(new PropertyValueFactory<>("tel2"));
        colFax.setCellValueFactory(new PropertyValueFactory<>("fax"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void refrescaTaula() {
        tvContacte.getItems().removeAll();
        tvContacte.getItems().setAll(getImpostos());
        if (tvContacte.getItems().isEmpty()) {
            tfCodi.clear();
            tfNom.clear();
            tfCog1.clear();
            tfCog2.clear();
            tfTel1.clear();
            tfTel2.clear();
            tfFax.clear();
            tfEmail.clear();
            /////////////////////////////////////////////
            btnEliminar.setDisable(true);
            btnGuardar.setDisable(true);
        }
    }

    private void refrescaTaula(int index) {
        refrescaTaula();
        tvContacte.requestFocus();
        tvContacte.getSelectionModel().select(index);
        tvContacte.getFocusModel().focus(index);
    }

    private void refrescaTaula(boolean last) {
        refrescaTaula();
        tvContacte.getSelectionModel().selectLast();
    }

    @FXML
    private void btnNouOnAction(ActionEvent event) {
        inserir();
    }

    @FXML
    private void btnCancelarOnAction(ActionEvent event) {
        cancelar();
    }

    @FXML
    private void btnEliminarOnAction(ActionEvent event) {
        eliminar();
    }

    @FXML
    private void btnGuardarOnAction(ActionEvent event) {
      //  guardar();
    }

    public void inserir() {
        tfCodi.clear();
        tfNom.clear();
        tfCog1.clear();
        tfCog2.clear();
        tfTel1.clear();
        tfTel2.clear();
        tfFax.clear();
        tfEmail.clear();
        /////////////////////////////////////////////
        tfNom.requestFocus();
        btnNou.setDisable(true);
        btnGuardar.setDisable(false);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(false);
        mode_insercio = true;
    }

    public void cancelar() {
        if (!tvContacte.getItems().isEmpty()) {
            Contacte item = tvContacte.getSelectionModel().getSelectedItem();
            tfCodi.setText(String.valueOf(item.getCodi_ctte()));
            tfNom.setText(item.getNom());
            tfCog1.setText(item.getCog1());
            tfCog2.setText(item.getCog2());
            tfTel1.setText(item.getTel1());
            tfTel2.setText(item.getTel2());
            tfFax.setText(item.getFax());
            tfEmail.setText(item.getEmail());

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

    public void eliminar() {
        if (!tvContacte.getItems().isEmpty()) {
            if (advertir("Està segur d'eliminar l'element?") == ButtonType.YES) {
                Contacte con = tvContacte.getSelectionModel().getSelectedItem();
                if (con != null) {
                    int indexActual = tvContacte.getItems().indexOf(con);
                    int nouIndex = indexActual - 1;
                    if (indexActual == FIRST) {
                        nouIndex = FIRST;
                    }
                    helperCon.eliminar(con, true);
                    refrescaTaula(nouIndex);
                }
            }
        }
    }

/*    public void guardar() {
        if (chkPrincipal.isSelected()) {
            if (!tfNom.getText().isEmpty()) {
                boolean last = false;
                int index = -1;
                if (mode_insercio) {
                    Contacte con = new Contacte();
                    con.setCodi_impost(0);
                    con.setNom(tfNom.getText());
                    con.setValor(Float.parseFloat(tfValor.getText()));
                    helperCon.afegir(con, true);
                    mode_insercio = false;
                    last = true;
                } else {
                    Contacte con = tvContacte.getSelectionModel().getSelectedItem();
                    index = tvContacte.getSelectionModel().getSelectedIndex();
                    con.setCodi_ctte(Integer.parseInt(tfCodi.getText()));
                    con.setNom(tfNom.getText());
                    con.setValor(Float.parseFloat(tfValor.getText()));

                    helperCon.actualitzar(con, true);
                }

                if (last) {
                    refrescaTaula(last);
                } else {
                    refrescaTaula(index);
                }

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
    }*/

    /**
     * Obté una llista completa de tots els usuaris.
     *
     * @return
     */
    private ObservableList<Contacte> getImpostos() {
        ArrayList<Contacte> llista = (ArrayList) helperCon.getAll();
        ObservableList<Contacte> llistaCon = FXCollections.observableArrayList(llista);

        return llistaCon;
    }
}
