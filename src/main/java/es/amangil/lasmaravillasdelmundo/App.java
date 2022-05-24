package es.amangil.lasmaravillasdelmundo;

import es.amangil.lasmaravillasdelmundo.entities.Maravilla;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class App extends Application {

    private static Scene scene;
    public static EntityManager em;
    public static FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws IOException {
        //Conexion con la base de datos
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("MaravillasDelMundo");
            em = emf.createEntityManager();
        } catch(PersistenceException ex){
            Logger.getLogger(App.class.getName()).log(Level.WARNING, ex.getMessage(),ex);
        }
        
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
        
//        Maravilla m = new Maravilla(0,"Pepe","Ramos");
//        em.getTransaction().begin();
//        em.persist(m);
//        em.getTransaction().commit();
    }
    
    
    @Override
    public void stop() throws Exception {
        em.close();
        try {
            DriverManager.getConnection("jdbc:derby:BDMaravillasMundo;create=true");
        }catch (SQLException ex) {
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}