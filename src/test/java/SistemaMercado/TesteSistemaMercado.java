//package SistemaMercado;
//
//public class TesteSistemaMercado {
//    public static void main(String[] args) {
//
//        CaixaImpl caixa = new CaixaImpl();
//
//        caixa.adicionarProduto(new Produto(1, "Arroz", 10.0, Categoria.ENLATADOS));
//        caixa.adicionarProduto(new Produto(2, "Feijão", 8.0, Categoria.ENLATADOS));
//        caixa.adicionarProduto(new Produto(3, "Sabão em pó", 5.0, Categoria.LIMPEZA));
//
//        System.out.println("Produtos no estoque:");
//        for (Produto produto : caixa.listarProdutos()) {
//            System.out.println("Código: " + produto.getCodigo() + ", Nome: " + produto.getNome() +
//                    ", Preço: " + produto.getPreco() + ", Categoria: " + produto.getCategoria());
//        }
//
//        caixa.adicionarCliente(new Cliente("João", "Rua A"));
//
//        Produto arroz = caixa.buscarProduto(1);
//        Produto feijao = caixa.buscarProduto(2);
//        caixa.adicionarProdutoAoCarrinho(arroz);
//        caixa.adicionarProdutoAoCarrinho(feijao);
//
//        System.out.println("\nProdutos no carrinho:");
//        for (Produto produto : caixa.getCarrinho()) {
//            System.out.println("Código: " + produto.getCodigo() + ", Nome: " + produto.getNome() +
//                    ", Preço: " + produto.getPreco());
//        }
//
//        caixa.removerProdutoDoCarrinho(feijao);
//
//        // Listando produtos atualizados no carrinho
//        System.out.println("\nProdutos no carrinho após remoção:");
//        for (Produto produto : caixa.getCarrinho()) {
//            System.out.println("Código: " + produto.getCodigo() + ", Nome: " + produto.getNome() +
//                    ", Preço: " + produto.getPreco());
//        }
//
//        // Finalizando a compra
//        caixa.setTipoPagamento(TipoPagamento.DINHEIRO);
//        caixa.finalizarCompra();
//    }
//}
//
