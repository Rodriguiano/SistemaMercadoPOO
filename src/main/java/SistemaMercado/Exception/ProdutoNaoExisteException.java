package SistemaMercado.Exception;

public class ProdutoNaoExisteException extends Exception {
    public ProdutoNaoExisteException(String msg){
        super(msg);
    }
}