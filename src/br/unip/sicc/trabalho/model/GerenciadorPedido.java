/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unip.sicc.trabalho.model;

import br.unip.sicc.trabalho.dao.DaoException;
import br.unip.sicc.trabalho.dao.PedidoDAO;
import br.unip.sicc.trabalho.dao.PedidoMemoriaDao;
import br.unip.sicc.trabalho.model.TipoPedido;
import br.unip.sicc.trabalho.model.Pedido;
import java.util.List;

/**
 *
 * @author user
 */
public class GerenciadorPedido {
    
    private static GerenciadorPedido instance;

    private GerenciadorPedido() {
    }

    public static GerenciadorPedido getInstance() {
        if (instance == null) {
            instance = new GerenciadorPedido();
        }
        return instance;
    }

 private PedidoDAO dao = new PedidoMemoriaDao();

    public Pedido getNovoPedido(){
        Pedido pedido = new Pedido();
        pedido.setId(new Integer(0));
        pedido.setTipo(TipoPedido.CHEQUE);
        pedido.setCodigo(new String());
        pedido.setQtdeItens(0);
       
        return pedido;
    }  
    
    
    public void salvar(Pedido pedido) throws DaoException {
        boolean ehNovo = pedido!= null && pedido.getId() != null
                && pedido.getId() > 0;
        if (ehNovo) {
            dao.incluir(pedido);
        } else {
            dao.atualizar(pedido);
        }
    }

    public void excluir(Pedido pessoa) throws DaoException {
        dao.excluir(pessoa);
    }

    public Pedido getPorId(int id) throws DaoException {
        return dao.getPedidoPorTipo(id);
    }

    public List<Pedido> getPorTipo(TipoPedido tipo) throws DaoException {
        if (tipo != null) {
            return dao.getPedidoPorTipo(tipo);
        } else {
            return dao.getTodos();
        }
    }

    public List<Pedido> getTodas() throws DaoException {
        return dao.getTodos();
    }

}
