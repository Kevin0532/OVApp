module com.example.openov {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.openov to javafx.fxml;
    exports com.example.openov;
}