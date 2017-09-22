/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Funcionario.Funcionario;
import java.util.Map;

/**
 *
 * @author x01k
 */
public interface FuncionarioDAOInterface {
    public void buscaFuncionario(String nome);
    public boolean inserirFuncionario(Funcionario funcionario);
    public boolean updateFuncionario(Funcionario funcionario);
    public boolean deletaFuncionario(Funcionario funcionario);
}
