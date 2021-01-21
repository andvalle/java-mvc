package br.unip.sicc.trabalho.view;





import br.unip.sicc.trabalho.view.PainelBuscaPedido;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import br.unip.sicc.trabalho.model.GerenciadorPedido;
import br.unip.sicc.trabalho.view.PainelCadastroPedido;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TelaControleDePedido extends JFrame{
    
    private PainelCadastroPedido painelCadastro;
    private PainelBuscaPedido painelBusca;
    private GerenciadorPedido gerenciador;
    
    private static TelaControleDePedido instance;
    
    private TelaControleDePedido(){
        inicializar();
    }
    static TelaControleDePedido getInstance() {
        if(instance==null){
            instance = new TelaControleDePedido();
        }
        return instance;
    }
    
    

    private void inicializar() {
        gerenciador = GerenciadorPedido.getInstance();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 300);
        this.setTitle("Controle de Pedido");
        this.setLocationRelativeTo(null);
        this.montaMenu();
        painelCadastro = PainelCadastroPedido.getInstance();
        painelBusca = PainelBuscaPedido.getInstance();
        this.add(painelBusca, BorderLayout.CENTER);
        this.add(painelCadastro, BorderLayout.WEST);
        this.setVisible(true);
    }
    
    private void montaMenu() {
        JMenuBar barraDeMenu = new JMenuBar();
        JMenu menuPedido = new JMenu("Pedido");
        menuPedido.setMnemonic(KeyEvent.VK_L);
        JMenu menuAjuda = new JMenu("Ajuda");
        menuAjuda.setMnemonic(KeyEvent.VK_A);
        JMenuItem itemNovo = new JMenuItem("Novo");
        itemNovo.setMnemonic(KeyEvent.VK_N);
        itemNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painelCadastro.setPedido(gerenciador.getNovoPedido());
            }
        });
        String mensagemSobre;
        mensagemSobre = "Software desenvolvido na disciplona ALPOO por Andrew Valle";
        JMenuItem itemSobre = new JMenuItem("Sobre");
        itemSobre.setMnemonic(KeyEvent.VK_S);
        itemSobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, mensagemSobre,
                        "Sobre", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menuPedido.add(itemNovo);
        menuAjuda.add(itemSobre);
        barraDeMenu.add(menuPedido);
        barraDeMenu.add(menuAjuda);
        this.setJMenuBar(barraDeMenu);
        
    }
    
    public static void main(String[] args) {
        TelaControleDePedido tela = 
                TelaControleDePedido.getInstance();
    }


    
}
