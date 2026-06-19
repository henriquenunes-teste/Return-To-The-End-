module com.code {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires json.simple;
    opens com.code to javafx.fxml;
    opens com.code.models to javafx.base;
    opens com.code.dao to javafx.base;
    opens com.code.utils to javafx.base;
    exports com.code;
}
