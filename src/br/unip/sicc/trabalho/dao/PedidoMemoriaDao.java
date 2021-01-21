/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unip.sicc.trabalho.dao;

import br.unip.sicc.trabalho.model.TipoPedido;
import br.unip.sicc.trabalho.model.Pedido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class PedidoMemoriaDao implements PedidoDAO {

    
    private List<Pedido> pedidos;
   
    
    public PedidoMemoriaDao(){
        pedidos= new ArrayList ();
        pedidos.add(new Pedido (12, 12, "ABC", TipoPedido.CHEQUE));
        pedidos.add(new Pedido (2, 1, "ABxC", TipoPedido.DEBITO));
    }

     public void incluir(Pedido pedidoF) throws DaoException {
        if (pedidoF != null) {
            Integer contador = null;
            pedidoF.setId(contador++);
            pedidos.add(pedidoF);
        } else {
            throw new DaoException("Pedido nulo");
        }
    }

    @Override
    public void atualizar(Pedido pedido) throws DaoException {
        if (pedido != null) {
            for (Pedido pedidoAtual : pedidos) {
                if (pedidoAtual.getId() == pedido.getId()) {
                    int indice = pedidos.indexOf(pedidoAtual);
                    pedidos.set(indice, pedido);
                    break;
                }
            }
        } else {
            throw new DaoException("Pedido nulo");
        }
    }

    @Override
    public void excluir(Pedido pedido) throws DaoException {
        if (pedido!= null) {
            pedidos.remove(pedido);
        } else {
            throw new DaoException("Pedido nulo");
        }
    }

    @Override
    public Pedido getPorId(Integer id) throws DaoException {
        Pedido pedido= null;
        for (Pedido pedidoAtual :pedidos) {
            if (pedidoAtual.getId() == id) {
                pedido= pedidoAtual;
                break;
            }
        }
        return pedido;
    }

    public List<Pedido> getPorTipo(TipoPedido tipo) throws DaoException {
        List<Pedido> pessoaFiltrados = new ArrayList<>();
        for (Pedido pedidoAtual : pedidos) {
            if (pedidoAtual.getTipo() == tipo) {
                pessoaFiltrados.add(pedidoAtual);
            }
        }
        return pessoaFiltrados;
    }

    @Override
    public List<Pedido> getTodos() throws DaoException {
        return pedidos;
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

    
    




 