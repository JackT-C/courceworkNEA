module com.example.csnea {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    opens com.example.csnea  to javafx.fxml;
    exports com.example.csnea;
}