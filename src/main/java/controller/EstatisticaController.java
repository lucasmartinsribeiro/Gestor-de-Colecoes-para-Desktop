
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.dao.ConnFactory;
import start.App;


public class EstatisticaController implements Initializable {

    @FXML
    private Label txtTextoEstatistica;
    
    @FXML
    private PieChart graficoPizza;
    @FXML
    private Button btnVoltar;
    
    private Connection conexao;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            insereStatus();
        } catch (SQLException ex) {
            Logger.getLogger(EstatisticaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void insereStatus() throws SQLException{
        
        int tA = 0;
        int tF = 0;
        
        conexao = ConnFactory.getConnection();
        PreparedStatement ps = conexao.prepareStatement("SELECT COUNT(*) as AlbumA FROM album WHERE status = 'Adquirido'");
        
        ResultSet resultadoA = ps.executeQuery();
        
        if(resultadoA.next()){
            tA = resultadoA.getInt("AlbumA");
        }
        
        PreparedStatement ps2 = conexao.prepareStatement("SELECT COUNT(*) as AlbumA FROM album WHERE status = 'Faltante'");
        
        ResultSet resultadoF = ps2.executeQuery();
        
        if(resultadoF.next()){
            tF = resultadoF.getInt("AlbumA");
        }
        
        ObservableList<PieChart.Data> lista = FXCollections.observableArrayList(
                     new PieChart.Data("Adquiridos", tA),
                     new PieChart.Data("Faltantes", tF)
        ); 
      
        graficoPizza.setData(lista);
    }
    
    @FXML
    private void btnVoltarOnAction(ActionEvent event) throws IOException {
        App.setRoot("Principal");
    }
    
}
