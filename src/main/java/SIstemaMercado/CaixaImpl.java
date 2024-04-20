package SIstemaMercado;

import java.util.List;
import java.util.Map;

public class CaixaImpl {
    private Map<Integer, Produto> estoque;
    private Map<String, Cliente> clientes;
    private List<Produto> carrinho;
    private TipoPagamento tipoPagamento;

    void adicionarProduto(Produto produto) { }
    void removerProduto(int codigo) { }
    Produto buscarProduto(int codigo) { }
    Map<Integer, Produto> listarProdutos() { }
    double calcularTotal() { }
    void finalizarCompra() { }
    void adicionarCliente(Cliente cliente) { }
    void removerCliente(String nome) { }
    void setTipoPagamento(TipoPagamento tipoPagamento) { }
    TipoPagamento getTipoPagamento() { }
}