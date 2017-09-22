/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Funcionario.Funcionario;
import static Telas.TelaPrincipal.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author x01k
 */
public class FuncionarioDAO implements FuncionarioDAOInterface {

    private Connection conexao = null;
    private Statement query;
    private String sql;

    public FuncionarioDAO() {
        try {
            this.conexao = Conexao.criarConexao();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean inserirFuncionario(Funcionario funcionario) {
        //INSERT INTO `funcionario` (`id`, `nome`, `sobrenome`, `cargo`, `salario`) VALUES (NULL, 'a', 'a', 'a', '1');
        sql = "INSERT INTO `funcionario` VALUES (NULL,?, ?, ?, ?)";
        try {
            PreparedStatement query = conexao.prepareStatement(sql);
            //query.setString(1, "NULL");
            query.setString(1, funcionario.getNome());
            query.setString(2, funcionario.getSobrenome());
            query.setString(3, funcionario.getCargo());
            query.setDouble(4, funcionario.getSalario());

            query.execute();
            query.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateFuncionario(Funcionario funcionario) {
        //UPDATE `funcionario` SET `nome` = 'jOSÉ' WHERE `funcionario`.`id` = 3;
        sql = "UPDATE `funcionario` SET `nome` = ?, `sobrenome` = ?, `cargo` = ?, `salario` = ? WHERE `funcionario`.`id` = ? ";
        try {
            PreparedStatement query = conexao.prepareStatement(sql);
            query.setString(1, funcionario.getNome());
            query.setString(2, funcionario.getSobrenome());
            query.setString(3, funcionario.getCargo());
            query.setDouble(4, funcionario.getSalario());
            query.setLong(5, funcionario.getId());

            query.execute();
            query.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletaFuncionario(Funcionario funcionario) {
        //DELETE FROM `funcionario` WHERE `funcionario`.`id` = 6
        sql = "DELETE FROM `funcionario` WHERE `funcionario`.`id` = ?";
        PreparedStatement query;
        try {
            query = conexao.prepareStatement(sql);
            query.setLong(1, funcionario.getId());
            query.execute();
            query.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void buscaFuncionario(String nomePes) {
        Map<String, Integer> result = new HashMap<>();

        sql = "SELECT id, nome, sobrenome, cargo, salario FROM funcionario WHERE nome LIKE '%" + nomePes + "%' ";
        try {
            query = conexao.createStatement();
            ResultSet rs = query.executeQuery(sql);

            rs.last();
            int tamanho = rs.getRow();
            modelo.setNumRows(tamanho);
            rs.beforeFirst();

            String id;
            String nome;
            String sobrenome;
            String cargo;
            Double salario;

            while (rs.next()) {
                for (int i = 0; i < tamanho; i++) {
                    id = String.valueOf(rs.getLong("id"));
                    nome = rs.getString("nome");
                    sobrenome = rs.getString("sobrenome");
                    cargo = rs.getString("cargo");
                    salario = rs.getDouble("salario");

                    modelo.setValueAt(id, i, 0);
                    modelo.setValueAt(nome, i, 1);
                    modelo.setValueAt(sobrenome, i, 2);
                    modelo.setValueAt(cargo, i, 3);
                    modelo.setValueAt(salario, i, 4);
                    rs.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
