package SistemaMercado.controller;

import SistemaMercado.Categoria;
import SistemaMercado.Produto;
import SistemaMercado.TipoPagamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class SistemaMercadoGUI extends JFrame {
    private SistemaMercadoLogic logic;
    private JTextField inputProduto;
    private JTextArea textAreaCarrinho;
    private JComboBox<Categoria> comboBoxCategoria;
    private JComboBox<TipoPagamento> comboBoxPagamento;
    private JLabel labelTotal;

    public SistemaMercadoGUI() {
        super("Sistema de Caixa de Supermercado");
        this.logic = new SistemaMercadoLogic();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));

        JLabel labelProduto = new JLabel("Nome do Produto:");
        panel.add(labelProduto);

        inputProduto = new JTextField();
        panel.add(inputProduto);

        JLabel labelCategoria = new JLabel("Categoria:");
        panel.add(labelCategoria);

        comboBoxCategoria = new JComboBox<>(Categoria.values());
        panel.add(comboBoxCategoria);

        JButton btnAdicionarProduto = new JButton("Adicionar ao Carrinho");
        btnAdicionarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProdutoAoCarrinho();
            }
        });
        panel.add(btnAdicionarProduto);

        textAreaCarrinho = new JTextArea(10, 30);
        textAreaCarrinho.setEditable(false);
        JScrollPane scrollPaneCarrinho = new JScrollPane(textAreaCarrinho);
        panel.add(scrollPaneCarrinho);

        JButton btnRemoverProduto = new JButton("Remover do Carrinho");
        btnRemoverProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerProdutoDoCarrinho();
            }
        });
        panel.add(btnRemoverProduto);

        labelTotal = new JLabel("Total da Compra: R$ 0.00");
        panel.add(labelTotal);

        JLabel labelPagamento = new JLabel("Método de Pagamento:");
        panel.add(labelPagamento);

        comboBoxPagamento = new JComboBox<>(TipoPagamento.values());
        panel.add(comboBoxPagamento);

        JButton btnFinalizarCompra = new JButton("Finalizar Compra");
        btnFinalizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarCompra();
            }
        });
        panel.add(btnFinalizarCompra);

        add(panel);
        setVisible(true);
    }

    private void adicionarProdutoAoCarrinho() {
        String nomeProduto = inputProduto.getText();
        Categoria categoria = (Categoria) comboBoxCategoria.getSelectedItem();
        Produto produto = logic.buscarProdutoPorNomeECategoria(nomeProduto, categoria);
        if (produto != null) {
            logic.adicionarProdutoAoCarrinho(produto);
            atualizarCarrinho();
            atualizarTotalCompra();
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado!");
        }
    }

    private void removerProdutoDoCarrinho() {
        String nomeProduto = inputProduto.getText();
        Categoria categoria = (Categoria) comboBoxCategoria.getSelectedItem();
        Produto produto = logic.buscarProdutoPorNomeECategoria(nomeProduto, categoria);
        if (produto != null) {
            logic.removerProdutoDoCarrinho(produto);
            atualizarCarrinho();
            atualizarTotalCompra();
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado!");
        }
    }

    private void finalizarCompra() {
        TipoPagamento tipoPagamento = (TipoPagamento) comboBoxPagamento.getSelectedItem();
        logic.setTipoPagamento(tipoPagamento);
        logic.finalizarCompra();
        JOptionPane.showMessageDialog(this, "Compra finalizada!");
        atualizarCarrinho();
        atualizarTotalCompra();
    }

    private void atualizarCarrinho() {
        Collection<Produto> carrinho = logic.getCarrinho();
        textAreaCarrinho.setText("Carrinho:\n");
        for (Produto produto : carrinho) {
            textAreaCarrinho.append(String.format("- %s (R$ %.2f)\n", produto.getNome(), produto.getPreco()));
        }
    }

    private void atualizarTotalCompra() {
        double total = logic.calcularTotal();
        labelTotal.setText(String.format("Total da Compra: R$ %.2f", total));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SistemaMercadoGUI::new);
    }
}
