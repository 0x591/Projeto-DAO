/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Propriedades;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author x01k
 */
public class Propriedades {

    // Editar as informações do arquivo de config
    public static void escrever(String driver, String server, String database, String username, String password) {
        Properties prop = new Properties();
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("configDB.properties");

            prop.setProperty("driver", driver);
            prop.setProperty("server", server);
            prop.setProperty("database", database);
            prop.setProperty("username", username);
            prop.setProperty("password", password);

            prop.store(outputStream, null);
        } catch (IOException e) {
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }
    // Método que retorna a instancia de Properties
    public static Properties propriedade() {
        Properties p = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("configDB.properties");
            p.load(inputStream);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
            }
        }
        return p;
    }
}
