package br.com.tecif.model;

import java.sql.*;

//Classe de conexao ao BD
public class Conexao {
    
    public static Connection conector() {
        java.sql.Connection conexao = null;
        //Carregar o drive do MySQL
        String driver = "com.mysql.cj.jdbc.Driver";
        //Endereço do BD, usuário e senha
        String url = "jdbc:mysql://localhost:3307/tecif?characterEncoding=utf-8";
        String user = "dba";
        String password = "Tecif@123456";
        //Tentar conectar ao BD
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        //Retornar mensagem caso de erro    
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
