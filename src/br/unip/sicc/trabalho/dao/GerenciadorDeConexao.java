/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unip.sicc.trabalho.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class GerenciadorDeConexao {
    private static final String URL
            = "jdbc:mysql://127.0.0.1:3306/test?zeroDateTimeBehavior=convertToNull";
    private static final String USUARIO = "aluno";
    private static final String SENHA = "unip";

    public static Connection getConnection() throws DaoException {
        Connection connection = null;
        try {
            connection
                    = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException ex) {
            throw new DaoException(
                    "Não foi possivel conectar ao banco de dados",
                    ex);
        }
        return connection;
    }

    public static void fechar(Connection connection) throws DaoException {
        try {
            connection.close();
        } catch (Exception ex) {
            throw new DaoException(
                    "Não foi possivel desconectar ao banco de dados",
                    ex);
        }
    }
 
    public static void fechar(Connection connection,
            Statement statement) throws DaoException {
        try {
            
            statement.close();

        } catch (Exception ex) {
            throw new DaoException(
                    "Não foi possivel desconectar ao banco de dados",
                    ex);
        } finally {
            GerenciadorDeConexao.fechar(connection);
        }
    }

    public static void fechar(Connection connection,
            Statement statement,
            ResultSet resultSet) throws DaoException {
        try {
            resultSet.close();
        } catch (Exception ex) {
            throw new DaoException(
                    "Não foi possivel desconectar ao banco de dados",
                    ex);
        } finally {
            GerenciadorDeConexao.fechar(connection, statement);
        }
    }

}


