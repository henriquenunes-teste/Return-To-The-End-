module com.code {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.code to javafx.fxml;
    exports com.code;
}
