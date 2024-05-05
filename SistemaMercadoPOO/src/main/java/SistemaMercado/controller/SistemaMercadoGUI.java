package SistemaMercado.controller;

import SistemaMercado.Categoria;
import SistemaMercado.Cliente;
import SistemaMercado.Produto;
import SistemaMercado.TipoPagamento;
import SistemaMercado.Exception.EstoqueVazioException;
import SistemaMercado.Exception.PagamentoInvalidoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class SistemaMercadoGUI extends JFrame {
    private SistemaMercadoLogic logic;
    private JTextField inputProduto;
    private JTextField inputCliente;
    private JTextArea textAreaCarrinho;
    private JComboBox<Categoria> comboBoxCategoria;
    private JComboBox<TipoPagamento> comboBoxPagamento;
    private JLabel labelTotal;

    public SistemaMercadoGUI(String clientesFilePath, String produtosFilePath) {
        super("Sistema de Caixa de Supermercado");
        this.logic = new SistemaMercadoLogic(clientesFilePath, produtosFilePath);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 10, 10));

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
                try {
                    finalizarCompra();
                } catch (EstoqueVazioException ex) {
                    throw new RuntimeException(ex);
                } catch (PagamentoInvalidoException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(btnFinalizarCompra);

        JLabel labelCliente = new JLabel("Nome do Cliente:");
        panel.add(labelCliente);

        inputCliente = new JTextField();
        panel.add(inputCliente);

        JButton btnAdicionarCliente = new JButton("Adicionar Cliente");
        btnAdicionarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCliente();
            }
        });
        panel.add(btnAdicionarCliente);

        JButton btnRemoverCliente = new JButton("Remover Cliente");
        btnRemoverCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerCliente();
            }
        });
        panel.add(btnRemoverCliente);

        JButton btnBuscarProduto = new JButton("Buscar Produto");
        btnBuscarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProduto();
            }
        });
        panel.add(btnBuscarProduto);

        JButton btnListarProdutosPorCategoria = new JButton("Listar Produtos por Categoria");
        btnListarProdutosPorCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProdutosPorCategoria();
            }
        });
        panel.add(btnListarProdutosPorCategoria);

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

    private void finalizarCompra() throws EstoqueVazioException, PagamentoInvalidoException {
        TipoPagamento tipoPagamento = (TipoPagamento) comboBoxPagamento.getSelectedItem();
        logic.setTipoPagamento(tipoPagamento);
        logic.finalizarCompra();
        JOptionPane.showMessageDialog(this, "Compra finalizada!");
        atualizarCarrinho();
        atualizarTotalCompra();
    }

    private void adicionarCliente() {
        String nomeCliente = inputCliente.getText();
        String enderecoCliente = JOptionPane.showInputDialog(this, "Digite o endereço do cliente:");
        logic.adicionarCliente(new Cliente(nomeCliente, enderecoCliente));
        JOptionPane.showMessageDialog(this, "Cliente adicionado!");
    }


    private void removerCliente() {
        String nomeCliente = inputCliente.getText();
        logic.removerCliente(nomeCliente);
        JOptionPane.showMessageDialog(this, "Cliente removido!");
    }

    private void buscarProduto() {
        String nomeProduto = inputProduto.getText();
        Categoria categoria = (Categoria) comboBoxCategoria.getSelectedItem();
        Produto produto = logic.buscarProdutoPorNomeECategoria(nomeProduto, categoria);
        if (produto != null) {
            JOptionPane.showMessageDialog(this, "Produto encontrado: " + produto.getNome());
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado!");
        }
    }

    private void listarProdutosPorCategoria() {
        Categoria categoria = (Categoria) comboBoxCategoria.getSelectedItem();
        Collection<Produto> produtos = logic.listarProdutosPorCategoria(categoria);
        StringBuilder mensagem = new StringBuilder("Produtos da categoria " + categoria + ":\n");
        for (Produto produto : produtos) {
            mensagem.append("- ").append(produto.getNome()).append("\n");
        }
        JOptionPane.showMessageDialog(this, mensagem.toString());
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
        String clientesFilePath = "clientes.txt";
        String produtosFilePath = "produtos.txt";
        SwingUtilities.invokeLater(() -> new SistemaMercadoGUI(clientesFilePath, produtosFilePath));
    }
}
