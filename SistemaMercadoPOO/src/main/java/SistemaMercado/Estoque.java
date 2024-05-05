package SistemaMercado;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<Integer, Produto> produtos;

    public Estoque() {
        this.produtos = new HashMap<>();
        adicionarProdutosDisponiveis();
    }

    private void adicionarProdutosDisponiveis() {
        Produto cerveja = new Produto(101, "Cerveja", 5.99, Categoria.BEBIDA);
        Produto refrigerante = new Produto(102, "Refrigerante", 3.49, Categoria.BEBIDA);
        Produto suco = new Produto(103, "Suco", 2.99, Categoria.BEBIDA);
        produtos.put(cerveja.getCodigo(), cerveja);
        produtos.put(refrigerante.getCodigo(), refrigerante);
        produtos.put(suco.getCodigo(), suco);

        Produto sabonete = new Produto(201, "Sabonete", 1.99, Categoria.HIGIENE);
        Produto shampoo = new Produto(202, "Shampoo", 7.49, Categoria.HIGIENE);
        Produto pastaDental = new Produto(203, "Pasta Dental", 2.99, Categoria.HIGIENE);
        produtos.put(sabonete.getCodigo(), sabonete);
        produtos.put(shampoo.getCodigo(), shampoo);
        produtos.put(pastaDental.getCodigo(), pastaDental);
    }

    public Produto buscarProduto(int codigo) {
        return produtos.get(codigo);
    }

    public void adicionarProduto(Produto produto) {
        produtos.put(produto.getCodigo(), produto);
    }

    public void removerProduto(int codigo) {
        produtos.remove(codigo);
    }
}
