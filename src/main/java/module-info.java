module es.amangil.lasmaravillasdelmundo {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.instrument;
    requires java.persistence;
    requires java.sql;
    
    opens es.amangil.lasmaravillasdelmundo.entities;
    opens es.amangil.lasmaravillasdelmundo to javafx.fxml;
    exports es.amangil.lasmaravillasdelmundo;
}
