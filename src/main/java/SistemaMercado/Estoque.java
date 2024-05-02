package SistemaMercado;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<String, Integer> produtos;

    public Estoque() {
        this.produtos = new HashMap<>();
    }

    public void adicionarProduto(String nomeProduto, int quantidade) {
        int estoqueAtual = produtos.getOrDefault(nomeProduto, 0);
        produtos.put(nomeProduto, estoqueAtual + quantidade);
    }

    public void removerProduto(String nomeProduto, int quantidade) {
        int estoqueAtual = produtos.getOrDefault(nomeProduto, 0);
        int novoEstoque = estoqueAtual - quantidade;
        if (novoEstoque <= 0) {
            produtos.remove(nomeProduto);
        } else {
            produtos.put(nomeProduto, novoEstoque);
        }
    }

    public int verificarEstoque(String nomeProduto) {
        return produtos.getOrDefault(nomeProduto, 0);
    }

    public void exibirEstoque() {
        System.out.println("Estoque:");
        for (Map.Entry<String, Integer> entry : produtos.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
