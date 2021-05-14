
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.Album;
import model.dao.AlbumDaoJDBC;
import model.dao.DaoFactory;
import start.App;


public class PrincipalController implements Initializable {

    @FXML
    private Button btnIncluir;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    @FXML
    private TextField txtFiltro;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnEstatistica;
    
    @FXML
    private TableColumn<Album, String> tblColNome;
    @FXML
    private TableColumn<Album, String> tblColArtista;
    @FXML
    private TableColumn<Album, String> tblColGenero;
    @FXML
    private TableColumn<Album, ImageView> tblColFoto;
    @FXML
    private TableColumn<Album, String> tblColStatus;
    @FXML
    private TableView<Album> tblAlbum;

    private List<Album> listaAlbuns;
    private ObservableList<Album> observableListAlbuns;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarAlbuns("");
    }

    @FXML
    private void btnIncluirOnAction(ActionEvent event) throws IOException {
        App.setRoot("Formulario");
    }

    @FXML
    private void btnEditarOnAction(ActionEvent event) throws IOException {
        
        
        Album albumSelecionado = tblAlbum.selectionModelProperty().getValue().getSelectedItem();
        FormularioController.setAlbumSelecionado(albumSelecionado);
        
        if(albumSelecionado != null){
            App.setRoot("Formulario");
        }
        
    }

    @FXML
    private void btnExcluirOnAction(ActionEvent event) throws Exception {
        Album albumSelecionado = tblAlbum.selectionModelProperty().getValue().getSelectedItem();
        AlbumDaoJDBC dao = DaoFactory.novoAlbumDao();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Aviso");
        alert.setContentText("Confirma exclusão de " + albumSelecionado.getNome() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                dao.excluir(albumSelecionado);
            } catch (Exception e) {
                String mensagem = "Ocorreu um erro " + e.getMessage();

                Alert alertErro = new Alert(Alert.AlertType.INFORMATION);
                alertErro.setTitle("Aviso");
                alertErro.setContentText(mensagem);
                alertErro.showAndWait();
            }
        }
        carregarAlbuns("");
    }

    @FXML
    private void btnFiltrarOnAction(ActionEvent event) {
        carregarAlbuns(txtFiltro.getText());
    }

    @FXML
    private void btnLimparOnAction(ActionEvent event) {
        carregarAlbuns("");
        txtFiltro.clear();
    }

    @FXML
    private void btnEstatisticaOnAction(ActionEvent event) throws IOException {
        App.setRoot("Estatistica");
    }
    
    public void carregarAlbuns(String param) {
        tblColNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tblColArtista.setCellValueFactory(new PropertyValueFactory<>("Artista"));
        tblColGenero.setCellValueFactory(new PropertyValueFactory<>("Genero"));
        tblColFoto.setCellValueFactory(new PropertyValueFactory<>("Foto"));
        tblColStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        try {
            AlbumDaoJDBC dao = DaoFactory.novoAlbumDao();
            listaAlbuns = dao.listar(param);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        observableListAlbuns = FXCollections.observableArrayList(listaAlbuns);
        tblAlbum.setItems(observableListAlbuns);
    }

    

}
