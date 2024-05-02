package SistemaMercado;

import java.util.Collection;

interface SistemaSupermercadoInterface<String> {

    public void adicionarProduto(Produto produto);
    public void removerProduto(int codigo);
    public Produto buscarProduto(int codigo) throws ProdutoNaoExisteException;
    public Collection<Produto> listarProdutos();
    public double calcularTotal();
    public void finalizarCompra();
    public void adicionarCliente(Cliente cliente);
    public void removerCliente(String nome);
}
