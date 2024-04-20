package SIstemaMercado;

import java.util.Map;

interface SistemaSupermercadoInterface {
    public void adicionarProduto(Produto produto);
    public void removerProduto(int codigo);
    public Produto buscarProduto(int codigo);
    public Map<Integer, Produto> listarProdutos();
    public double calcularTotal();
    public void finalizarCompra();
    public void adicionarCliente(Cliente cliente);
    public void removerCliente(String nome);
}
