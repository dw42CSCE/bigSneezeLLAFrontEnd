module com.language {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires json.simple;

    opens com.language to javafx.fxml;
    exports com.language;
}
