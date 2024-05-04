package SistemaMercado;

import java.util.*;

public class CaixaImpl implements SistemaSupermercadoInterface {

    private Map<String, Cliente> clientes;
    private Map<Integer, Produto> estoque;
    private List<Produto> carrinho;
    private TipoPagamento tipoPagamento;
    private List<Produto> produtosDisponiveis;

    public CaixaImpl() {
        clientes = new HashMap<>();
        estoque = new HashMap<>();
        carrinho = new ArrayList<>();
        tipoPagamento = null;
        produtosDisponiveis = new ArrayList<>();

        adicionarProdutosDisponiveis();
    }

    private void adicionarProdutosDisponiveis() {
        Produto cerveja = new Produto(101, "Cerveja", 5.99, Categoria.BEBIDA);
        Produto refrigerante = new Produto(102, "Refrigerante", 3.49, Categoria.BEBIDA);
        Produto suco = new Produto(103, "Suco", 2.99, Categoria.BEBIDA);
        produtosDisponiveis.add(cerveja);
        produtosDisponiveis.add(refrigerante);
        produtosDisponiveis.add(suco);

        Produto sabonete = new Produto(201, "Sabonete", 1.99, Categoria.HIGIENE);
        Produto shampoo = new Produto(202, "Shampoo", 7.49, Categoria.HIGIENE);
        Produto pastaDental = new Produto(203, "Pasta Dental", 2.99, Categoria.HIGIENE);
        produtosDisponiveis.add(sabonete);
        produtosDisponiveis.add(shampoo);
        produtosDisponiveis.add(pastaDental);
    }

    @Override
    public void adicionarProduto(Produto produto) {
        estoque.put(produto.getCodigo(), produto);
    }

    @Override
    public void removerProduto(int codigo) {
        estoque.remove(codigo);
    }

    @Override
    public Produto buscarProduto(int codigo) throws ProdutoNaoExisteException {
        Produto produto = estoque.get(codigo);
        if (produto == null) {
            throw new ProdutoNaoExisteException("Produto com código " + codigo + " não encontrado");
        }
        return produto;
    }

    @Override
    public Collection<Produto> listarProdutos() {
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

    public Produto buscarProdutoPorNome(String nomeProduto) {
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
        List<Produto> produtosPorCategoria = new ArrayList<>();
        for (Produto produto : estoque.values()) {
            if (produto.getCategoria() == categoria) {
                produtosPorCategoria.add(produto);
            }
        }
        return produtosPorCategoria;
    }


    public Produto buscarProdutoPorNomeECategoria(String nomeProduto, Categoria categoria) {
        for (Produto produto : estoque.values()) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto) && produto.getCategoria() == categoria) {
                return produto;
            }
        }
        return null;
    }


    public void adicionarProdutoCategoriaBebida(Produto produto) {
        adicionarProdutoNaCategoria(produto, Categoria.BEBIDA);
    }

    public void adicionarProdutoCategoriaHigiene(Produto produto) {
        adicionarProdutoNaCategoria(produto, Categoria.HIGIENE);
    }

    public void adicionarProdutoCategoriaEnlatados(Produto produto) {
        adicionarProdutoNaCategoria(produto, Categoria.ENLATADOS);
    }

    public void adicionarProdutoCategoriaHortifruti(Produto produto) {
        adicionarProdutoNaCategoria(produto, Categoria.HORTIFRUTI);
    }

    public void adicionarProdutoCategoriaLimpeza(Produto produto) {
        adicionarProdutoNaCategoria(produto, Categoria.LIMPEZA);
    }

    private void adicionarProdutoNaCategoria(Produto produto, Categoria categoria) {
        if (estoque == null) {
            estoque = new HashMap<>();
        }
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo");
        }
        produto.setCategoria(categoria);
        estoque.put(produto.getCodigo(), produto);
    }

    public Collection<Cliente> listarClientes() {
        if (clientes == null || clientes.isEmpty()) {
            return Collections.emptyList();
        }
        return clientes.values();
    }
}
