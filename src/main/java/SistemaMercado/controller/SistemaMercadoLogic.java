package SistemaMercado.controller;

import SistemaMercado.CaixaImpl;
import SistemaMercado.Categoria;
import SistemaMercado.Produto;
import SistemaMercado.TipoPagamento;

import java.util.Collection;

public class SistemaMercadoLogic {
    private CaixaImpl caixa;

    public SistemaMercadoLogic() {
        this.caixa = new CaixaImpl();
    }

    public void adicionarProdutoAoCarrinho(Produto produto) {
        caixa.adicionarProdutoAoCarrinho(produto);
    }

    public void removerProdutoDoCarrinho(Produto produto) {
        caixa.removerProdutoDoCarrinho(produto);
    }

    public void finalizarCompra() {
        caixa.finalizarCompra();
    }

    public Collection<Produto> getCarrinho() {
        return caixa.getCarrinho();
    }

    public double calcularTotal() {
        return caixa.calcularTotal();
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        caixa.setTipoPagamento(tipoPagamento);
    }

    public Produto buscarProdutoPorNomeECategoria(String nomeProduto, Categoria categoria) {
        return caixa.buscarProdutoPorNomeECategoria(nomeProduto, categoria);
    }
}
