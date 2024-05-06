package SistemaMercado.controller;

import SistemaMercado.Categoria;
import SistemaMercado.Cliente;
import SistemaMercado.Produto;
import SistemaMercado.TipoPagamento;
import SistemaMercado.Exception.EstoqueVazioException;
import SistemaMercado.Exception.PagamentoInvalidoException;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class SistemaMercadoLogic {
    private File clientesFile;
    private File produtosFile;
    private List<Cliente> clientes;
    private List<Produto> produtos;
    private List<Produto> carrinho;
    private TipoPagamento tipoPagamento;

    public SistemaMercadoLogic(String clientesFilePath, String produtosFilePath) {
        this.clientesFile = new File(clientesFilePath);
        this.produtosFile = new File(produtosFilePath);
        this.clientes = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.carrinho = new ArrayList<>();
        this.tipoPagamento = null;
        // Carregue os clientes e produtos do arquivo ao inicializar a lógica do sistema
        carregarClientes();
        carregarProdutos();
    }

    // Métodos para clientes
    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
        salvarClientes();
        JOptionPane.showMessageDialog(null, "Cliente adicionado!");
    }

    public void removerCliente(String nomeCliente) {
        Cliente cliente = pesquisarCliente(nomeCliente);
        if (cliente != null) {
            clientes.remove(cliente);
            salvarClientes();
        }
    }

    public Cliente pesquisarCliente(String nomeCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nomeCliente)) {
                return cliente;
            }
        }
        return null;
    }

    public Collection<Cliente> listarClientes() {
        return Collections.unmodifiableList(clientes);
    }

    // Métodos para produtos
    public void adicionarProdutoPorCategoria(Categoria categoria, Produto produto) {
        produtos.add(produto);
        salvarProdutos();
    }

    public Collection<Produto> listarProdutosPorCategoria(Categoria categoria) {
        List<Produto> produtosPorCategoria = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.getCategoria() == categoria) {
                produtosPorCategoria.add(produto);
            }
        }
        return Collections.unmodifiableList(produtosPorCategoria);
    }

    // Métodos para carrinho
    public void adicionarProdutoAoCarrinho(Produto produto) {
        carrinho.add(produto);
    }

    public void removerProdutoDoCarrinho(Produto produto) {
        carrinho.remove(produto);
    }

    public Collection<Produto> getCarrinho() {
        return Collections.unmodifiableList(carrinho);
    }

    // Métodos para finalizar compra
    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public void finalizarCompra() throws EstoqueVazioException, PagamentoInvalidoException {
        // Lógica para finalizar a compra
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto produto : carrinho) {
            total += produto.getPreco();
        }
        return total;
    }

    // Outros métodos...
    private void carregarClientes() {
        // Lógica para carregar os clientes do arquivo
    }

    private void salvarClientes() {
        // Lógica para salvar os clientes no arquivo
    }

    private void carregarProdutos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(produtosFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String nome = parts[1];
                    Categoria categoria = Categoria.valueOf(parts[3]);
                    double preco = Double.parseDouble(parts[2]);
                    produtos.add(new Produto(id, nome, preco, categoria));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarProdutos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(produtosFile))) {
            for (Produto produto : produtos) {
                writer.write(produto.getCodigo() + "," + produto.getNome() + "," + produto.getPreco() + "," + produto.getCategoria() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Produto buscarProdutoPorNomeECategoria(String nomeProduto, Categoria categoria) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto) && produto.getCategoria() == categoria) {
                return produto;
            }
        }
        return null;
    }

}
