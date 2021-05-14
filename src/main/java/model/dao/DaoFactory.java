/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

/**
 *
 * @author lucribeiro
 */
public class DaoFactory {
    public static AlbumDaoJDBC novoAlbumDao() throws Exception{
        return new AlbumDaoJDBC();
    }
}
