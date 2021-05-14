
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Album;


public class AlbumDaoJDBC implements InterfaceDao<Album> {

    private Connection conn;

    public AlbumDaoJDBC() throws Exception {
        this.conn = ConnFactory.getConnection();
    }

    @Override
    public void incluir(Album entidade) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Album(nome, artista, genero, foto, status) VALUES(?, ?, ?, ?, ?) ");
        ps.setString(1, entidade.getNome());
        ps.setString(2, entidade.getArtista());
        ps.setString(3, entidade.getGenero());
        ps.setString(4, entidade.getFoto());
        ps.setString(5, entidade.getStatus());
        ps.execute();

    }

    @Override
    public void editar(Album entidade) throws Exception {
        PreparedStatement ps = conn.prepareStatement("UPDATE Album SET nome = ?, artista = ?, genero = ?, foto = ?, status = ? WHERE id = ?");
        ps.setString(1, entidade.getNome());
        ps.setString(2, entidade.getArtista());
        ps.setString(3, entidade.getGenero());
        ps.setString(4, entidade.getFoto());
        ps.setString(5, entidade.getStatus());
        ps.setInt(6, entidade.getId());
        ps.execute();
    }

    @Override
    public void excluir(Album entidade) throws Exception {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Album WHERE id = ?");
        ps.setInt(1, entidade.getId());
        ps.execute();
    }

    @Override
    public Album pesquisarPorId(int id) throws Exception {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Album WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Album a = null;
        while (rs.next()) {
            a = new Album();
            a.setId(rs.getInt("id"));
            a.setNome(rs.getString("nome"));
            a.setArtista(rs.getString("artista"));
            a.setGenero(rs.getString("genero"));
            a.setFoto(rs.getString("foto"));
        }

        return a;
    }

    @Override
    public List<Album> listar(String param) throws Exception {
        PreparedStatement ps = null;
        if(param == ""){
            ps = conn.prepareStatement("SELECT * FROM Album");
        } else {
            ps = conn.prepareStatement("SELECT * FROM Album WHERE nome like '%" + param + "%'");
        }
        ResultSet rs = ps.executeQuery();
        List<Album> lista = new ArrayList();
        while (rs.next()) {
            Album a = new Album();
            a.setId(rs.getInt("id"));
            a.setNome(rs.getString("nome"));
            a.setArtista(rs.getString("artista"));
            a.setGenero(rs.getString("genero"));
            a.setFoto(rs.getString("foto"));
            a.setStatus(rs.getString("status"));
            lista.add(a);
        }

        return lista;
    }

}
