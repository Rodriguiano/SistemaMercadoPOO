package SistemaMercado;

import SistemaMercado.Exception.EstoqueVazioException;
import SistemaMercado.Exception.PagamentoInvalidoException;
import SistemaMercado.Exception.ProdutoNaoExisteException;

import java.util.Collection;

interface SistemaSupermercadoInterface {

    void adicionarProduto(Produto produto);

    void removerProduto(int codigo) throws ProdutoNaoExisteException;

    Produto buscarProduto(int codigo) throws ProdutoNaoExisteException;

    Collection<Produto> listarProdutos() throws EstoqueVazioException;

    double calcularTotal();

    void finalizarCompra() throws EstoqueVazioException, PagamentoInvalidoException;

    void adicionarCliente(Cliente cliente);

    void removerCliente(String nome);
}
