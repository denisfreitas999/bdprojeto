package br.com.projetoloja.dal;

import java.sql.*;
/**
 *
 * @author Denisson
 */
public class ModuloConexao {
    //Método que estabelece a conexão com o banco de dados
    public static Connection conector(){
        //Criação da variável conexão
        Connection conexao = null;
        //Carregar o drive correspondente ao tipo de banco de dados
        String driver = "com.mysql.cj.jdbc.Driver";
        //Criação de variáveis para armazenamento de informações referente ao 
        //Banco de dados
        String url = "jdbc:mysql://localhost:3306/projetodb";
        String user = "root";
        String password = "aluno";
        //Realizar a conexão com o banco de dados
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password);
            return conexao;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
