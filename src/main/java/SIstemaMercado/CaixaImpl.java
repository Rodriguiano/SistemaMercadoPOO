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