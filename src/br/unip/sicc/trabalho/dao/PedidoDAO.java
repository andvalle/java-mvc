/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unip.sicc.trabalho.dao;

import br.unip.sicc.trabalho.model.TipoPedido;
import br.unip.sicc.trabalho.model.Pedido;
import java.util.List;

/**
 *
 * @author user
 */
public interface PedidoDAO {
    
public List getTodos() throws DaoException;

 public Pedido getPorId(Integer id) throws DaoException;

public List getPedidoPorTipo(TipoPedido tipo) throws DaoException;

public void incluir(Pedido pedido) throws DaoException;

public void excluir(Pedido pedido) throws DaoException;

public void atualizar(Pedido pedido) throws DaoException;

    public Pedido getPedidoPorTipo(int id);
    

}
