package SistemaMercado;

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
        if (estoque == null || estoque.isEmpty()) {
            return null;
        }
        return estoque.get(codigo);
    }

    @Override
    public Collection<Produto> listarProdutos() {
        if (estoque == null || estoque.isEmpty()) {
            return Collections.emptyList();
        }
        return estoque.values();
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
        if (carrinho == null || carrinho.isEmpty()) {
            System.out.println("O carrinho está vazio. Adicione produtos antes de finalizar a compra.");
            return;
        }
        double total = calcularTotal();
        System.out.println("Total da compra: " + total);
        if (tipoPagamento == null) {
            System.out.println("Tipo de pagamento não definido. Defina um tipo de pagamento antes de finalizar a compra.");
            return;
        }
        System.out.println("Pagamento realizado por: " + tipoPagamento);
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
        if (nome instanceof String) {
            String nomeCliente = (String) nome;
            if (clientes == null) {
                clientes = new HashMap<>();
            }
            clientes.remove(nomeCliente);
        } else {
            throw new IllegalArgumentException("Tipo de nome inválido. Deve ser uma instância de String.");
        }
    }
    public void setTipoPagamento (TipoPagamento tipoPagamento){
        if (tipoPagamento == null) {
            throw new IllegalArgumentException("O pagamento não pode ser nulo");
        }
        this.tipoPagamento = tipoPagamento;
    }

    TipoPagamento getTipoPagamento () {
        return tipoPagamento;
    }
    public Produto buscarProdutoPorNome(String nomeProduto) {
        if (estoque == null || estoque.isEmpty()) {
            return null;
        }
        for (Produto produto : estoque.values()) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                return produto;
            }
        }
        return null;
    }

    public void adicionarProdutoAoCarrinho(Produto produto) {
        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }
        carrinho.add(produto);
    }

    public List<Produto> getCarrinho() {
        return carrinho;
    }

    public void removerProdutoDoCarrinho(Produto produto) {
        if (carrinho != null) {
            carrinho.remove(produto);
        }
    }

    public Collection<Produto> listarProdutosPorCategoria(Categoria categoria) {
        if (estoque == null || estoque.isEmpty()) {
            return Collections.emptyList();
        }
        List<Produto> produtosPorCategoria = new ArrayList<>();
        for (Produto produto : estoque.values()) {
            if (produto.getCategoria() == categoria) {
                produtosPorCategoria.add(produto);
            }
        }
        return produtosPorCategoria;
    }
}


