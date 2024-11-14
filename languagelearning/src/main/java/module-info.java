module com.language {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.language to javafx.fxml;
    exports com.language;
}
