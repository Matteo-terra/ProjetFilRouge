module com.filrouge.projet_filrouge {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.xml.bind;
    requires java.desktop;
    requires java.sql;

    opens com.filrouge.projet_filrouge to javafx.fxml;
    exports com.filrouge.projet_filrouge;
    exports com.filrouge.projet_filrouge.controller;
    opens com.filrouge.projet_filrouge.controller to javafx.fxml, java.xml.bind;

    opens com.filrouge.projet_filrouge.model to javafx.fxml, java.xml.bind;
    exports com.filrouge.projet_filrouge.model;
}