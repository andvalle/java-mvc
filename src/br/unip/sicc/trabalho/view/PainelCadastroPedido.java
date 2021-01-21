/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unip.sicc.trabalho.view;


import br.unip.sicc.trabalho.dao.DaoException;
import br.unip.sicc.trabalho.model.GerenciadorPedido;
import br.unip.sicc.trabalho.model.TipoPedido;
import br.unip.sicc.trabalho.model.Pedido;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class PainelCadastroPedido extends JPanel {
    
    
    private JLabel lbId, lbTipo,
            lbcodigo, lbQtddeitens;
    private JTextField txid, txcodigo;
    private JComboBox cbTipo;
    private JFormattedTextField txId;
    private JButton btCancelar, btOk;
    private Pedido pedido;
    private GerenciadorPedido gerenciador;

    private static PainelCadastroPedido instance;

    private PainelCadastroPedido() {
        inicializar();
    }


    public static PainelCadastroPedido getInstance() {
        if (instance == null) {
            instance = new PainelCadastroPedido();
        }
        return instance;
    }

    private void inicializar() {
        gerenciador = GerenciadorPedido.getInstance();
        this.setLayout(new BorderLayout());
        JPanel painelCampos = criaPainelCampos();
        JPanel painelBotoes = criaPainelBotoes();
        this.add(painelCampos, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.SOUTH);
    }

    private JPanel criaPainelCampos() {
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(7, 2, 5, 5));
        lbId = new JLabel("Id:");
        txId = new JFormattedTextField(NumberFormat.getIntegerInstance());        
        txId.setEditable(false);
        lbTipo = new JLabel("Tipo:");
        TipoPedido[] tipos = TipoPedido.values();
        cbTipo = new JComboBox(tipos);
        lbcodigo= new JLabel("Codigo :");
        
        lbQtddeitens = new JLabel("qtd de itens:");
       
        painel.add(lbId);
        painel.add(txId);
        painel.add(lbTipo);
        painel.add(cbTipo);
        painel.add(lbcodigo);
        painel.add(lbQtddeitens);
     
        
        return painel;
    }

    private JPanel criaPainelBotoes() {
        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btCancelar = new JButton("Cancelar");
        btCancelar.setMnemonic(KeyEvent.VK_C);
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPedido(pedido);
            }
        });
        btOk = new JButton("Ok");
        btOk.setMnemonic(KeyEvent.VK_O);
        btOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Pedido pedidoEditado = getPedidoFEditado();
                    gerenciador.salvar(pedidoEditado);
                    pedido = pedidoEditado;
                    PainelBuscaPedido.getInstance().atualizaTabela();
                } catch (DaoException ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel confirmar a operacao",
                            "Confirmaçãor", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        painel.add(btCancelar);
        painel.add(btOk);
        return painel;
    }

  public void setPedido(Pedido pedido) {
        if (pedido != null) {
            this.pedido = pedido;
            txId.setValue(pedido.getId());
            cbTipo.setSelectedItem(pedido.getTipo());
            lbcodigo.setText(pedido.getCodigo());
            lbQtddeitens.setText(pedido.toString());
        } else {
            JOptionPane.showConfirmDialog(this, "Pedido nulo", "Erro",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }
    Pedido getPedidoFEditado(){
        Pedido pedidoEditado = new Pedido();
        pedidoEditado.setId((Integer)txId.getValue());
        TipoPedido tipo = (TipoPedido) cbTipo.getSelectedItem();
        pedidoEditado.setTipo(tipo);
        pedidoEditado.setCodigo(lbcodigo.getText());
       
        return pedidoEditado;
    }

}


