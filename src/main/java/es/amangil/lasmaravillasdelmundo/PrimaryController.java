package es.amangil.lasmaravillasdelmundo;

import es.amangil.lasmaravillasdelmundo.entities.Maravilla;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.persistence.Query;

public class PrimaryController implements Initializable {
    
    private Maravilla maravillaSeleccionada;
    @FXML
    private TableView<Maravilla> tableViewMaravilla;
    @FXML
    private TableColumn<Maravilla, String> columnNombre;
    @FXML
    private TableColumn<Maravilla, String> columnNombreCreador;
    @FXML
    private TableColumn<Maravilla, String> columnEstadoDeLaMaravilla;
    @FXML
    private TableColumn<Maravilla, String> columnLocalizacion;
    @FXML
    private TableColumn<Maravilla, String> columnFechaDeConstruccion;
    @FXML
    private TableColumn<Maravilla, String> columnProvincia;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldLocalizacion;
    @FXML
    private TextField textFieldNombreCreador;
    @FXML
    private AnchorPane rootContactosView;
    
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        System.out.println("ijkqshgdfiuasjhgdfasjkihd");
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnNombreCreador.setCellValueFactory(new PropertyValueFactory<>("nombreCreador"));
////        columnNombre.setCellValueFactory(new PropertyValueFactory<>("apellido"));
////        columnLocalizacion.setCellValueFactory(new PropertyValueFactory<>("localizacion"));
////        columnFechaDeConstruccion.setCellValueFactory(new PropertyValueFactory<>("fechaDeConstruccion"));
//        columnLocalizacion.setCellValueFactory(
//                    cellData -> {
//                        SimpleStringProperty property = new SimpleStringProperty();
//                        if (cellData.getValue().getLocalizacion() != null) {
//                            property.setValue(cellData.getValue().getLocalizacion().getNombre());
//                        }
//                        return property;
//                    });
        tableViewMaravilla.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    maravillaSeleccionada = newValue;
                    if (maravillaSeleccionada != null) {
                        textFieldNombre.setText(maravillaSeleccionada.getNombre());
                        textFieldNombreCreador.setText(maravillaSeleccionada.getNombreCreador());
//                        textFieldLocalizacion.setText(String.valueOf(maravillaSeleccionada.getLocalizacion()));  
                    } else {
                        textFieldNombre.setText("");
                        textFieldNombreCreador.setText("");
//                        textFieldLocalizacion.setText("");
                    }
                });
        cargarTodasPersonas();
    }

    private void cargarTodasPersonas() {
        Query queryMaravillaFindAll = App.em.createNamedQuery("Maravilla.findAll");
        List<Maravilla> listaMaravilla = queryMaravillaFindAll.getResultList();
        tableViewMaravilla.setItems(FXCollections.observableArrayList(listaMaravilla));
    }
    
    @FXML
    private void onActionButtonGuardar2(ActionEvent event) {
        if (maravillaSeleccionada != null) {
            maravillaSeleccionada.setNombre(textFieldNombre.getText());
            maravillaSeleccionada.setNombreCreador(textFieldNombreCreador.getText());
//            maravillaSeleccionada.setNombre(textFieldLocalizacion.getText());
            App.em.getTransaction().begin();
            App.em.merge(maravillaSeleccionada);
            App.em.getTransaction().commit();
            
            int numFilaSeleccionada = tableViewMaravilla.getSelectionModel().getSelectedIndex();
            tableViewMaravilla.getItems().set(numFilaSeleccionada, maravillaSeleccionada);
            TablePosition pos = new TablePosition(tableViewMaravilla, numFilaSeleccionada, null);
            tableViewMaravilla.getFocusModel().focus(pos);
            tableViewMaravilla.requestFocus();
        }
    }
    
    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
        try {
            App.setRoot("secondary");
            SecondaryController secondaryController = (SecondaryController)App.fxmlLoader.getController();
            maravillaSeleccionada = new Maravilla();
            secondaryController.setMaravilla(maravillaSeleccionada, true);
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    @FXML
    private void onActionButtonSuprimir(ActionEvent event) {
        if(maravillaSeleccionada != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar");
            alert.setHeaderText("¿Desea surpimir el siguiente registro?");
            alert.setContentText(maravillaSeleccionada.getNombre());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                App.em.getTransaction().begin();
                App.em.remove(maravillaSeleccionada);
                App.em.getTransaction().commit();
                tableViewMaravilla.getItems().remove(maravillaSeleccionada);
                tableViewMaravilla.getFocusModel().focus(null);
                tableViewMaravilla.requestFocus();
            } else {
                int numFilaSeleccionada = tableViewMaravilla.getSelectionModel().getSelectedIndex();
                tableViewMaravilla.getItems().set(numFilaSeleccionada, maravillaSeleccionada);
                TablePosition pos = new TablePosition(tableViewMaravilla, numFilaSeleccionada, null);
                tableViewMaravilla.getFocusModel().focus(pos);
                tableViewMaravilla.requestFocus();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion");
            alert.setHeaderText("Debe seleccionar un registro");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void onActionButtonEditar(ActionEvent event) {
        if(maravillaSeleccionada != null) {
            try {
                App.setRoot("secondary");
                SecondaryController secondaryController = (SecondaryController)App.fxmlLoader.getController();
                secondaryController.setMaravilla(maravillaSeleccionada, false);
            } catch (IOException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion");
            alert.setHeaderText("Debe seleccionar un registro");
            alert.showAndWait();
        }
    }
    
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
