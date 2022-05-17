module com.example.csnea {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csnea to javafx.fxml;
    exports com.example.csnea;
}