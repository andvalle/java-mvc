/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unip.sicc.trabalho.view;

import br.unip.sicc.trabalho.model.TipoPedido;
import br.unip.sicc.trabalho.model.Pedido;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class PedidoTableModel implements TableModel {
 private List<Pedido> pedido;

    public PedidoTableModel(List<Pedido> pedido) {
        this.pedido= pedido;
    }

     @Override
    public int getRowCount() {
        return pedido.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Id";
            case 1:
                return "Tipo";
            case 2:
                return "Codigo";
            case 3:
                return "QTD DE ITEN";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return TipoPedido.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
        }
        return void.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pedido pedidoAtual = pedido.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pedidoAtual.getId();
            case 1:
                return pedidoAtual.getTipo();
            case 2:
                return pedidoAtual.getCodigo();
            case 3:
                return pedidoAtual.getQtdeItens();
        }
        return "";
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }

   public Pedido getPedido(int linha) {
        return pedido.get(linha);
    }

    public void setPedidos(List<Pedido> pedido) {
        this.pedido = pedido;
    }

}
