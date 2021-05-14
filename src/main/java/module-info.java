module start {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens controller to javafx.fxml;
    exports controller;
     
    opens model to javafx.fxml;
    exports model;
    
    exports start;
}
