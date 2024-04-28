package SIstemaMercado;

import java.util.*;

public class CaixaImpl implements SistemaSupermercadoInterface {

    private Map<Integer, Produto> estoque;

    private Map<String, Cliente> clientes;

    private List<Produto> carrinho;

    private TipoPagamento tipoPagamento;

    @Override
    public void adicionarProduto(Produto produto) {
        if (estoque == null) {
            estoque = new HashMap<>();
        }
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo");
        }
        estoque.put(produto.getCodigo(), produto);
    }

    @Override
    public void removerProduto(int codigo) {
        if (estoque == null) {
            estoque = new HashMap<>();
        }
        estoque.remove(codigo);
    }


    @Override
    public Produto buscarProduto(int codigo) {
        return null;
    }

    @Override
    public Collection<Produto> listarProdutos() {
        return null;
    }

    @Override
    public double calcularTotal() {
        double total = 0;
        if (carrinho != null) {
            for (Produto produto : carrinho) {
                total += produto.getPreco();
            }
        }
        return total;
    }

    @Override
    public void finalizarCompra() {

    }

    public void adicionarCliente(Cliente cliente) {
        if (clientes == null) {
            clientes = new HashMap<>();
        }
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo");
        }
        clientes.put(cliente.getNome(), cliente);
    }

    @Override
    public void removerCliente(Object nome) {

    }

    public void removerCliente(String nome) {
        if (clientes == null) {
            clientes = new HashMap<>();
        }
        clientes.remove(nome);
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        if (tipoPagamento == null) {
            throw new IllegalArgumentException("O pagamento não pode ser nulo");
        }
        this.tipoPagamento = tipoPagamento;
    }

    TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }
}