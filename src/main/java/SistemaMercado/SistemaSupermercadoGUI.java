package SistemaMercado;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class SistemaSupermercadoGUI extends JFrame {
    private CaixaImpl caixa;

    private JTextField inputProduto;
    private JTextField inputCliente;
    private JComboBox<Categoria> comboBoxCategorias;
    private JTextArea textAreaProdutos;
    private JTextArea textAreaCarrinho;
    private JLabel labelPrecoProduto;

    public SistemaSupermercadoGUI(CaixaImpl caixa) {
        super("Sistema de Supermercado");
        this.caixa = caixa;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));

        JLabel labelProduto = new JLabel("Produto:");
        panel.add(labelProduto);

        inputProduto = new JTextField();
        panel.add(inputProduto);

        JLabel labelCliente = new JLabel("Cliente:");
        panel.add(labelCliente);

        inputCliente = new JTextField();
        panel.add(inputCliente);

        JLabel labelCategoria = new JLabel("Categoria:");
        panel.add(labelCategoria);

        comboBoxCategorias = new JComboBox<>(Categoria.values());
        panel.add(comboBoxCategorias);

        JButton btnAdicionarProduto = new JButton("Adicionar Produto");
        btnAdicionarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });
        panel.add(btnAdicionarProduto);

        JButton btnRemoverProduto = new JButton("Remover Produto");
        btnRemoverProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerProduto();
            }
        });
        panel.add(btnRemoverProduto);

        JButton btnListarProdutosCategoria = new JButton("Listar Produtos por Categoria");
        btnListarProdutosCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProdutosPorCategoria();
            }
        });
        panel.add(btnListarProdutosCategoria);

        JButton btnAdicionarCliente = new JButton("Adicionar Cliente");
        btnAdicionarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCliente();
            }
        });
        panel.add(btnAdicionarCliente);

        JButton btnFinalizarCompra = new JButton("Finalizar Compra");
        btnFinalizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarCompra();
            }
        });
        panel.add(btnFinalizarCompra);

        textAreaProdutos = new JTextArea();
        panel.add(new JScrollPane(textAreaProdutos));

        textAreaCarrinho = new JTextArea();
        panel.add(new JScrollPane(textAreaCarrinho));

        labelPrecoProduto = new JLabel("Preço: -");
        panel.add(labelPrecoProduto);

        inputProduto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                atualizarPrecoProduto();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                atualizarPrecoProduto();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                atualizarPrecoProduto();
            }
        });

        add(panel);
        setVisible(true);
    }

    private void adicionarProduto() {
        String nomeProduto = inputProduto.getText();
        Produto produto = caixa.buscarProdutoPorNome(nomeProduto);
        if (produto != null) {
            caixa.adicionarProdutoAoCarrinho(produto);
            atualizarTextAreaCarrinho(caixa.getCarrinho());
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerProduto() {
        String nomeProduto = inputProduto.getText();
        Produto produto = caixa.buscarProdutoPorNome(nomeProduto);
        if (produto != null) {
            caixa.removerProdutoDoCarrinho(produto);
            atualizarTextAreaCarrinho(caixa.getCarrinho());
        } else {
            JOptionPane.showMessageDialog(this, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarProdutosPorCategoria() {
        Categoria categoriaSelecionada = (Categoria) comboBoxCategorias.getSelectedItem();
        Collection<Produto> produtos = caixa.listarProdutosPorCategoria(categoriaSelecionada);
        atualizarTextAreaProdutos(produtos);
    }

    private void adicionarCliente() {
        String nomeCliente = inputCliente.getText();
        Cliente cliente = new Cliente(nomeCliente, "");
        caixa.adicionarCliente(cliente);
        JOptionPane.showMessageDialog(this, "Cliente adicionado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void finalizarCompra() {
        caixa.finalizarCompra();
        JOptionPane.showMessageDialog(this, "Compra finalizada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        atualizarTextAreaCarrinho(caixa.getCarrinho());
    }

    private void atualizarTextAreaProdutos(Collection<Produto> produtos) {
        textAreaProdutos.setText(""); // Limpa o texto atual
        if (produtos.isEmpty()) {
            textAreaProdutos.append("Nenhum produto encontrado.");
        } else {
            for (Produto produto : produtos) {
                textAreaProdutos.append(String.format("Código: %d, Nome: %s, Preço: %.2f\n",
                        produto.getCodigo(), produto.getNome(), produto.getPreco()));
            }
        }
    }

    private void atualizarTextAreaCarrinho(Collection<Produto> produtos) {
        textAreaCarrinho.setText(""); // Limpa o texto atual
        if (produtos.isEmpty()) {
            textAreaCarrinho.append("Carrinho vazio.");
        } else {
            for (Produto produto : produtos) {
                textAreaCarrinho.append(String.format("Código: %d, Nome: %s, Preço: %.2f\n",
                        produto.getCodigo(), produto.getNome(), produto.getPreco()));
            }
        }
    }

    private void atualizarPrecoProduto() {
        String nomeProduto = inputProduto.getText();
        Produto produto = caixa.buscarProdutoPorNome(nomeProduto);
        if (produto != null) {
            labelPrecoProduto.setText("Preço: " + produto.getPreco());
        } else {
            labelPrecoProduto.setText("Preço: -");
        }
    }

    public static void main(String[] args) {
        CaixaImpl caixa = new CaixaImpl();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SistemaSupermercadoGUI(caixa);
            }
        });
    }
}
