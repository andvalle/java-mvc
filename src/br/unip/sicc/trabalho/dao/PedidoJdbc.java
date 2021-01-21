/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unip.sicc.trabalho.dao;

import br.unip.sicc.trabalho.model.TipoPedido;
import br.unip.sicc.trabalho.model.Pedido;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class PedidoJdbc implements PedidoDAO{
    
    private static String SQL_SELECT_ALL =
            "SELECT ID, TIPO, CODIGO, QUANTIDADE "
          + " FROM TB_PEDIDOS;";
    @Override
    public List<Pedido> getTodos() throws DaoException {
        Connection connection = 
                GerenciadorDeConexao.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Pedido> listaDePedidos= new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL);
            rs = statement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("ID");
                String tipoStr = rs.getString("TIPO");
                TipoPedido tipo = TipoPedido.valueOf(tipoStr);
                String codigo = rs.getString("CODIGO: ");
                int  qtddeItens = rs.getInt("QUANTIDADE DE ITENS");
                Pedido pedido = 
                        new Pedido( id, qtddeItens,  codigo, tipo );
                listaDePedidos.add(pedido);
                
            }
        } catch (SQLException ex) {
            throw new DaoException(
                    "Não foi possivel selecionar",ex);
        }finally{
            GerenciadorDeConexao.fechar(connection, statement, rs);
        }
        return listaDePedidos;
    }
    private static String SQL_SELECT_POR_ID =
            "SELECT ID, TIPO, CODIGO, QTDDEITENS "
          + " FROM TB_PEDIDOS WHERE ID=?;";
    @Override
    public Pedido getPorId(Integer id) throws DaoException {
        Connection connection = 
                GerenciadorDeConexao.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        Pedido pedido = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_POR_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if(rs.next()){ //se tiver registro
                int qtddeItens = rs.getInt("QTDDEITENS: ");
                String tipoStr = rs.getString("TIPO");
                 String codigo = rs.getString("CODIGO: ");
                TipoPedido tipo = TipoPedido.valueOf(tipoStr);
               
                
                pedido = 
                        new Pedido(id, qtddeItens, codigo, tipo);
            }
        } catch (SQLException ex) {
            throw new DaoException(
                    "Não foi possivel selecionar",ex);
        }finally{
            GerenciadorDeConexao.fechar(connection, statement, rs);
        }
        return pedido;
    }
    private static String SQL_SELECT_POR_TIPO =
            "SELECT ID, TIPO, CODIGONOME, QTDDEITENSOME "
          + " FROM TB_PEDIDOS WHERE TIPO = ?;";

    public List<Pedido> getPorTipo(TipoPedido tipo) throws DaoException {
        Connection connection = 
                GerenciadorDeConexao.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Pedido> listaDePedidos= new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_SELECT_POR_TIPO);
            statement.setString(1, tipo.toString());
            rs = statement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("ID");
                     int qtddeItens = rs.getInt("QTDDEITENS: ");
                
                 String codigo = rs.getString("CODIGO: ");
                
               
                Pedido pedido = 
                        new Pedido(id, qtddeItens, codigo, tipo );
                listaDePedidos.add(pedido);
            }
        } catch (SQLException ex) {
            throw new DaoException(
                    "Não foi possivel selecionar",ex);
        }finally{
            GerenciadorDeConexao.fechar(connection, statement, rs);
        }
        return listaDePedidos;
    }

    private static String SQL_INSERT
            = "INSERT INTO TB_PEDIDOS (`TIPO`, `CODIGO`, QTDDEITENS`) "
            + "VALUES (?, ?, ?);";

    @Override
    public void incluir(Pedido pedido) throws DaoException {
        Connection connection = GerenciadorDeConexao.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, pedido.getTipo().toString());
            statement.setString(2, pedido.getCodigo());
            statement.setInt(3, pedido.getQtdeItens());
            int qtdeRegistros = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(
                    "Não foi possivel incluir",ex);
        }finally{
            GerenciadorDeConexao.fechar(connection, statement);
        }
    }
    private static String SQL_DELETE =
            "DELETE FROM TB_PEDIDOS WHERE ID = ?;";

    @Override
    public void excluir(Pedido pedido) throws DaoException {
        Connection connection = GerenciadorDeConexao.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, pedido.getId());
            int qtdeRegistros = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(
                    "Não foi possivel excluir",ex);
        }finally{
            GerenciadorDeConexao.fechar(connection, statement);
        }
    }
    private static String SQL_UPDATE =
            "UPDATE TB_PEDIDOS SET TIPO = ? , CODIGO = ? , QTDDEITENS = ?  WHERE ID = ?;";

    @Override
    public void atualizar(Pedido pedido) throws DaoException {
        Connection connection = GerenciadorDeConexao.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, pedido.getTipo().toString());
            statement.setString(2, pedido.getCodigo());
            statement.setInt(3, pedido.getQtdeItens());
            statement.setInt(7, pedido.getId());
            int qtdeRegistros = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(
                    "Não foi possivel atualizar",ex);
        }finally{
            GerenciadorDeConexao.fechar(connection, statement);
        }
    }
    private static PedidoJdbc instance;

    public static PedidoJdbc getInstance() {
        if (instance == null) {
            instance = new PedidoJdbc();
        }
        return instance;
    }

    private PedidoJdbc() {
    }

    @Override
    public List getPedidoPorTipo(TipoPedido tipo) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pedido getPedidoPorTipo(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}

