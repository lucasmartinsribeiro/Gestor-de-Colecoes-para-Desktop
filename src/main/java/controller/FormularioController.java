
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Album;
import model.dao.AlbumDaoJDBC;
import model.dao.DaoFactory;
import start.App;


public class FormularioController implements Initializable {
    
    @FXML
    private Label lblNomeAlbum;
    @FXML
    private Label lblArtista;
    @FXML
    private Label lblGenero;
    @FXML
    private Label lblStatus;
    
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtArtista;
    @FXML
    private TextField txtGenero;
    private TextField txtStatus;
    
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGravar;
    @FXML
    private ImageView imgFoto;
    
    @FXML
    private ComboBox cmbStatus;
    
    private String firstPath;
    private String urlImagem;
    
    private static Album albumSelecionado;
    
    private ObservableList<String> lista = FXCollections.observableArrayList("Adquirido", "Faltante");
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        File file = new File(".//src/main/java/Imagem/semfoto.jpg");
        Image image = new Image(file.toURI().toString());
        imgFoto.setImage(image); //para exibir no ImageView
        
        cmbStatus.setValue("");
        cmbStatus.setItems(lista);
        
       if(albumSelecionado != null){
        txtNome.setText(albumSelecionado.getNome());
        txtArtista.setText(albumSelecionado.getArtista());
        txtGenero.setText(albumSelecionado.getGenero());
        String nomeImagem = albumSelecionado.getFoto();
        
        
        File arquivo = new File(albumSelecionado.getFoto());
        Image imagem = new Image(arquivo.toURI().toString());
        imgFoto.setImage(imagem);
        
       }
       
       imgFoto.setOnMouseClicked((MouseEvent e)->{
                selecionaFoto();
                
            });
        }
        

    @FXML
    private void btnCancelarOnAction(ActionEvent event) throws IOException {
        App.setRoot("Principal");
        
        albumSelecionado = null;
    }

    @FXML
    private void btnGravarOnAction(ActionEvent event) throws IOException, Exception {
        
        Album album = new Album();
        
        album.setNome(txtNome.getText());
        album.setArtista(txtArtista.getText());
        album.setGenero(txtGenero.getText());
        album.setFoto(urlImagem);
        
        album.setStatus(cmbStatus.getSelectionModel().getSelectedItem().toString());
        
        AlbumDaoJDBC dao = DaoFactory.novoAlbumDao();
        if(albumSelecionado == null){
            dao.incluir(album);
        } else {
            album.setId(albumSelecionado.getId());
            dao.editar(album);
            albumSelecionado = null;
        }
        
        App.setRoot("Principal");
    }
    
    public void selecionaFoto(){
        
    
    FileChooser fileChooser = new FileChooser();
    
    if (firstPath != null) {
        File path = new File(firstPath);
        fileChooser.initialDirectoryProperty().set(path);
    }

    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.jpg"));
    Window OwnStage = null;
    File selectFile = fileChooser.showOpenDialog(OwnStage);
 
        if (selectFile != null) {
            String path = selectFile.getPath();
            int len = path.lastIndexOf("/"); //no detec return -1
            if (len == -1) {
                len = path.lastIndexOf("\\");
            }
            
            firstPath = path.substring(0, len);
            urlImagem = path;
            
        }
        
        File file = new File(urlImagem);
        Image image = new Image(file.toURI().toString());
        imgFoto.setImage(image);
/*
        FileChooser f = new FileChooser();
        
        f.getExtensionFilters().add(new ExtensionFilter("Imagens", "*.jpg", "*.png", "*.jpeg"));
        
        File arquivo = f.showOpenDialog(new Stage());
        
        if(arquivo != null){
            imgFoto.setImage(new Image("file:///"+arquivo.getAbsolutePath()));
            
        }*/
        
    }
    public static Album getAlbumSelecionado() {
        return albumSelecionado;
    }

    public static void setAlbumSelecionado(Album albumSelecionado) {
        FormularioController.albumSelecionado = albumSelecionado;
    }
    
    
}
