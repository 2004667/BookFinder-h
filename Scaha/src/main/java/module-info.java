module com.example.scaha {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.example.scaha to javafx.fxml;
    exports com.example.scaha;
}