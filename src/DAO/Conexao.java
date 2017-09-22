/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static Propriedades.Propriedades.propriedade;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author x01k
 */
public class Conexao {

    public static Connection criarConexao() throws ClassNotFoundException, SQLException {
        String driver = propriedade().getProperty("driver");
        Class.forName(driver);

        String server = propriedade().getProperty("server"),
                database = propriedade().getProperty("database"),
                url = "jdbc:mysql://"
                + server + "/"
                + database,
                username = propriedade().getProperty("username"),
                password = propriedade().getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }
}
