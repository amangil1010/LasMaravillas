package es.amangil.lasmaravillasdelmundo;

import es.amangil.lasmaravillasdelmundo.entities.Localizacion;
import es.amangil.lasmaravillasdelmundo.entities.Maravilla;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javax.persistence.Query;
import javax.persistence.RollbackException;

public class SecondaryController implements Initializable{

    private Maravilla maravilla;
    
    private boolean nuevaMaravilla;
    
    private static final char DESTRUIDA ='D';
    private static final char RUINAS ='R';
    private static final char BUEN_ESTADO ='B';

    private static final String CARPETA_FOTOS = "Fotos";
    
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldNombreCreador;
    @FXML
    private ComboBox<Localizacion> comboBoxLocalizacion;
    @FXML
    private DatePicker datePickerFechaDeConstruccion;
    @FXML
    private RadioButton radioButtonDestruida;
    @FXML
    private RadioButton radioButtonRuinas;
    @FXML
    private RadioButton radioButtonBuenEstado;
    @FXML
    private TextField textFieldPrecioEntrada;
    @FXML
    private CheckBox checkBoxSigueExistiendo;
    @FXML
    private ImageView imageViewFoto;
    @FXML
    private BorderPane rootSecondary;
    @FXML
    private ToggleGroup estadoMaravillaGroup;
    
    
    public void initialize(URL url, ResourceBundle rb){
    }
    
    public void setMaravilla(Maravilla maravilla, boolean nuevaMaravilla) {
        App.em.getTransaction().begin();
        if (!nuevaMaravilla){
            this.maravilla = App.em.find(Maravilla.class, maravilla.getId());
        } else {
            this.maravilla = maravilla;
        }
        this.nuevaMaravilla = nuevaMaravilla;
        
        mostrarDatos();
    }
    
    private void mostrarDatos(){
        textFieldNombre.setText(maravilla.getNombre());
        textFieldNombreCreador.setText(maravilla.getNombreCreador());
        
        if (maravilla.getPrecioDeEntrada() != null) {
                textFieldPrecioEntrada.setText(String.valueOf(maravilla.getPrecioDeEntrada()));
            }    
        
        if (maravilla.getSigueExistiendo() != null) {
            checkBoxSigueExistiendo.setSelected(maravilla.getSigueExistiendo());
        }
        
        if (maravilla.getEstadoDeLaMaravilla() != null) {
            System.out.println(maravilla.getEstadoDeLaMaravilla());
            switch (maravilla.getEstadoDeLaMaravilla()) {
                case DESTRUIDA:
                    radioButtonDestruida.setSelected(true);
                    break;
                case RUINAS:
                    radioButtonRuinas.setSelected(true);
                    break;
                case BUEN_ESTADO:
                    radioButtonBuenEstado.setSelected(true);
                    break;
            }
        }
        
        if (maravilla.getFechaDeConstruccion() != null) {
            Date date = maravilla.getFechaDeConstruccion();
            Instant instant = date.toInstant();
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            LocalDate localDate = zdt.toLocalDate();
            datePickerFechaDeConstruccion.setValue(localDate);
        }
        
        Query queryLocalizacionFindAll = App.em.createNamedQuery("Localizacion.findAll");
        List<Localizacion> listLocalizacion = queryLocalizacionFindAll.getResultList();
        
        comboBoxLocalizacion.setItems(FXCollections.observableList(listLocalizacion));
        if (maravilla.getLocalizacion() != null){
            comboBoxLocalizacion.setValue(maravilla.getLocalizacion());
        }
        comboBoxLocalizacion.setCellFactory((ListView<Localizacion> l) -> new ListCell<Localizacion>() {
            @Override
            protected void updateItem(Localizacion localizacion, boolean empty) {
                super.updateItem(localizacion, empty);
                if (localizacion == null || empty){
                    setText("");
                } else {
                    setText(localizacion.getCodigo() + "-" + localizacion.getNombre());
                }
            }  
            
        });
    
        // 
        comboBoxLocalizacion.setConverter(new StringConverter<Localizacion>() {
                @Override
                public String toString(Localizacion localizacion) {
                if (localizacion == null){
                    return null;
                } else {
                    return localizacion.getCodigo() + "-" + localizacion.getNombre();
                }
                }
                
            @Override
            public Localizacion fromString(String userId){
                return null;
            }
        });
        
        if (maravilla.getFoto() != null) {
            String imageFileName = maravilla.getFoto();
            File file = new File(CARPETA_FOTOS + "/" + imageFileName);
            if (file.exists()){
                Image image = new Image(file.toURI().toString());
                imageViewFoto.setImage(image);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "No se encuentra la imagen");
                alert.showAndWait();
            }
        }
    }
        
        
        @FXML
        private void onActionButtonGuardar(ActionEvent event) {
            int numFilaSeleccionada;
            boolean errorFormato=false;
            
            maravilla.setNombre(textFieldNombre.getText());
            maravilla.setNombreCreador(textFieldNombreCreador.getText());
            
//            if (maravilla.getPrecioDeEntrada() != null) {
//                textFieldPrecioEntrada.setText(String.valueOf(maravilla.getPrecioDeEntrada()));
//            }
            
            maravilla.setPrecioDeEntrada(BigDecimal.valueOf(Double.valueOf(textFieldPrecioEntrada.getText()).doubleValue()));
            
            maravilla.setSigueExistiendo(checkBoxSigueExistiendo.isSelected());
            
            if (radioButtonDestruida.isSelected()) {
                maravilla.setEstadoDeLaMaravilla(DESTRUIDA);
            } else if (radioButtonRuinas.isSelected()) {
                maravilla.setEstadoDeLaMaravilla(RUINAS);
            } else if (radioButtonBuenEstado.isSelected()) {
                maravilla.setEstadoDeLaMaravilla(BUEN_ESTADO);
            }
            
            
        if (datePickerFechaDeConstruccion.getValue() != null){
            LocalDate localDate = datePickerFechaDeConstruccion.getValue();
            ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
            Instant instant = zonedDateTime.toInstant();
            Date date = Date.from(instant);
            maravilla.setFechaDeConstruccion(date);
        } else {
            maravilla.setFechaDeConstruccion(null);
        }
        
        maravilla.setLocalizacion(comboBoxLocalizacion.getValue());
        
        if (!errorFormato) {
            try {
                if (maravilla.getId() == null){
                    System.out.println("Guardando nueva persona en BD");
                    App.em.persist(maravilla);
                } else {
                    System.out.println("Actualizando persona en BD");
                    App.em.merge(maravilla);
                }
                App.em.getTransaction().commit();
                
                App.setRoot("primary");
            } catch (RollbackException ex){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("No se han podido guardar los cambios."
                        + "Compruebe que los datos cumplen los requisitos");
                alert.setContentText(ex.getLocalizedMessage());
                alert.showAndWait();
            } catch (IOException ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
    
        
        
    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
        App.em.getTransaction().rollback();
        
        try {
            App.setRoot("primary");
        } catch (IOException ex) {
            Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
        
        
    @FXML   
    private void onActionButtonExaminar(ActionEvent event){
            File carpetaFotos = new File(CARPETA_FOTOS);
            if (!carpetaFotos.exists()) {
                    carpetaFotos.mkdir();
            }
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar Imagen");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Imagenes (jpg, png)", "*.jpg", "*.png"),
                    new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
            );
            File file = fileChooser.showOpenDialog(rootSecondary.getScene().getWindow());
            if (file != null) {
                    try {
                            Files.copy(file.toPath(), new File(CARPETA_FOTOS + "/" + file.getName()).toPath());
                            maravilla.setFoto(file.getName());
                            Image image = new Image(file.toURI().toString());
                            imageViewFoto.setImage(image);
                    } catch (FileAlreadyExistsException ex) {
                            Alert alert = new Alert(Alert.AlertType.WARNING, "Nombre de archivo duplicado");
                            alert.showAndWait();
                    } catch (IOException ex) {
                            Alert alert = new Alert(Alert.AlertType.WARNING, "Nombre se ha podido guardar la imagen");
                            alert.showAndWait();
                    }
            }
    }
    
    @FXML
    private void onActionSuprimirFoto(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar supresion de imagen");
            alert.setHeaderText("??Desea SUPRIMIR el achivo asociado a la imagen, \n"
                    +	"quitar la foto pero MANTENER el archivo, \no CANCELAR la operacion?");
            alert.setContentText("Elija la opcion deseada:");

            ButtonType buttonTypeEliminar = new ButtonType("Suprimir");
            ButtonType buttonTypeMantener = new ButtonType("Mantener");
            ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeEliminar, buttonTypeMantener, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeEliminar) {
                    String imageFileName = maravilla.getFoto();
                    File file = new File(CARPETA_FOTOS + "/" + imageFileName);
                    if (file.exists()) {
                            file.delete();
                    }
                    maravilla.setFoto(null);
                    imageViewFoto.setImage(null);
            } else if (result.get() == buttonTypeMantener) {
                    maravilla.setFoto(null);
                    imageViewFoto.setImage(null);
            }
    }
    
    
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}