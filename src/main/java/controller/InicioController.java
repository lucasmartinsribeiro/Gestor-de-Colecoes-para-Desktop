
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import start.App;


public class InicioController implements Initializable {

    @FXML
    private Button btnNext;
    @FXML
    private Label lblGestorColecao;
    @FXML
    private Label lblAbelLucas;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnNextOnAction(ActionEvent event) throws IOException {
        App.setRoot("Principal");
    }
    
}
