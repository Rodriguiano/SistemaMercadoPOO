package SistemaMercado;

import java.util.Collection;

interface SistemaSupermercadoInterface {

    void adicionarProduto(Produto produto);

    void removerProduto(int codigo);

    Produto buscarProduto(int codigo) throws ProdutoNaoExisteException;

    Collection<Produto> listarProdutos();

    double calcularTotal();

    void finalizarCompra();

    void adicionarCliente(Cliente cliente);

    void removerCliente(String nome);
}
