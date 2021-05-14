
package model;

public class Album {
    private int id;
    private String nome;
    private String artista;
    private String genero;
    private String foto;
    private String status;
    
    public Album() {
       
    }

    public Album(String nome, String artista, String genero, String foto, String status) {
        this.nome = nome;
        this.artista = artista;
        this.genero = genero;
        this.foto = foto;
        this.status = status;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    @Override
    public String toString() {
        return id + " | " + nome + " | " + artista + " | " + genero + " | " + foto + " | " + status;
    }
}
