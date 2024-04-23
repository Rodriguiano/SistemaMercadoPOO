package SIstemaMercado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaixaImpl implements SistemaSupermercadoInterface {

    private Map<Integer, Produto> estoque;

    private Map<String, Cliente> clientes;

    private List<Produto> carrinho;

    private TipoPagamento tipoPagamento;

    public void adicionarProduto(Produto produto) {
        if (estoque == null) {
            estoque = new HashMap<>();
        }
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo");
        }
        estoque.put(produto.getCodigo(), produto);
    }

    public void removerProduto(int codigo) {
        if (estoque == null) {
            estoque = new HashMap<>();
        }
        estoque.remove(codigo);
    }

    public Produto buscarProduto(int codigo) {
        if (estoque == null) {
            estoque = new HashMap<>();
        }
        return estoque.get(codigo);
    }

    public Map<Integer, Produto> listarProdutos() {
        if (estoque == null) {
            estoque = new HashMap<>();
        }
        return new HashMap<>(estoque);
    }

    public double calcularTotal() {
        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }
        double total = 0.0;
        for (Produto produto : carrinho) {
            total += produto.getPreco();
        }
        return total;
    }

    public void finalizarCompra() {
        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }
        carrinho.clear();
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