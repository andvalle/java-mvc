package br.unip.sicc.trabalho.view;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import br.unip.sicc.trabalho.dao.DaoException;
import br.unip.sicc.trabalho.dao.DaoException;
import br.unip.sicc.trabalho.model.GerenciadorPedido;
import br.unip.sicc.trabalho.model.Pedido;
import br.unip.sicc.trabalho.model.TipoPedido;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane; 
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
 
public class PainelBuscaPedido extends JPanel {

    private JLabel lbFiltro;
    private JComboBox cbFiltroTipo;
    private JButton btBuscar, btSelecionar, btExcluir;
    private JTable tbPedidos;
    private GerenciadorPedido gerenciador;

    private static PainelBuscaPedido instance;

    private PainelBuscaPedido () {
        gerenciador = GerenciadorPedido.getInstance();
        this.setLayout(new BorderLayout());
        JPanel painelFiltro = criaPainelFiltro();
        JScrollPane painelTabela = criaPainelTabela();
        JPanel painelBotoes = criaPainelBotoes();
        this.add(painelFiltro, BorderLayout.NORTH);
        this.add(painelTabela, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.SOUTH);
    }

    public static PainelBuscaPedido getInstance() {
        if (instance == null) {
            instance = new PainelBuscaPedido();
        }
        return instance;
    }

    private JPanel criaPainelFiltro() {
        JPanel painel = new JPanel();
        lbFiltro = new JLabel("Tipo");
        TipoPedido[] tipos = TipoPedido.values();
        cbFiltroTipo = new JComboBox(tipos);
        cbFiltroTipo.insertItemAt("TODOS", 0);
        cbFiltroTipo.setSelectedIndex(0);
        btBuscar = new JButton("Busca");
        btBuscar.setMnemonic(KeyEvent.VK_B);
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object itemSelecionado = cbFiltroTipo.getSelectedItem();
                TipoPedido tipoSelecionado = null;
                if (itemSelecionado instanceof TipoPedido) {
                    tipoSelecionado
                            = (TipoPedido) itemSelecionado;
                }
                try {
                    List<Pedido> listaFiltrada
                            = gerenciador.getPorTipo(tipoSelecionado);
                    PedidoTableModel model
                            = (PedidoTableModel) tbPedidos.getModel();
                    model.setPedidos(listaFiltrada);
                    atualizaTabela();
                } catch (DaoException ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possível realizar a busca",
                            "Busca", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        painel.add(lbFiltro);
        painel.add(cbFiltroTipo);
        painel.add(btBuscar);
        return painel;
    }

    private JPanel criaPainelBotoes() {
        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btSelecionar = new JButton("Selecionar");
        btSelecionar.setMnemonic(KeyEvent.VK_S);
        btSelecionar.addActionListener(new SelecionarListener());
        btExcluir = new JButton("Excluir");
        btExcluir.setMnemonic(KeyEvent.VK_X);
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pedido pedido= getPedidoSelecionado();
                if (pedido == null) {
                    JOptionPane.showMessageDialog(null, "Selecione uma pedido",
                            "Selecione", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String mensagem = "Confirma a exclusão do pedido"
                        + pedido.getId();
                int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Confirmação",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                try {
                    if (opcao == JOptionPane.OK_OPTION) {
                        gerenciador.excluir(pedido);
                        atualizaTabela();
                    }
                } catch (DaoException ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possível realizar a exclusão",
                            "Exclusão", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        painel.add(btSelecionar);
        painel.add(btExcluir);
        return painel;
    }

    private JScrollPane criaPainelTabela() {
        PedidoTableModel pedidoTableModel = null;
        try {
            pedidoTableModel = new PedidoTableModel(gerenciador.getTodas());
        } catch (DaoException ex) {
            System.out.println("Mostrar erro");
        }
        tbPedidos = new JTable(pedidoTableModel);
        tbPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(tbPedidos);
        return scroll;
    }

    private class SelecionarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Pedido pedido = getPedidoSelecionado();
            if (pedido == null) {
                JOptionPane.showMessageDialog(null, "Selecione um pedido",
                        "Sobre", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            PainelCadastroPedido.getInstance().setPedido(pedido);
        }
    }


    private Pedido getPedidoSelecionado() {
        int linhaSelecionada = tbPedidos.getSelectedRow();
        Pedido pedidoSelecionado = null;
        if (linhaSelecionada == -1) {
            System.out.println("Nenhuma linha selecionada");
        } else {
            PedidoTableModel tableModel
                    = (PedidoTableModel) tbPedidos.getModel();
            pedidoSelecionado
                    = tableModel.getPedido(linhaSelecionada);
        }
        return pedidoSelecionado;
    }

    void atualizaTabela() {
        tbPedidos.repaint();
        tbPedidos.revalidate();
    }

}
