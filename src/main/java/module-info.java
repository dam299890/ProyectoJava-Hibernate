module com.example.fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires org.apache.poi.ooxml;

    opens com.example.modelo;
    opens com.example.controlador to javafx.fxml;
    exports com.example.controlador;
}