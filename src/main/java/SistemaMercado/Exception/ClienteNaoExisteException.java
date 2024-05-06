package SistemaMercado.Exception;

public class ClienteNaoExisteException extends Exception {
    public ClienteNaoExisteException(String mensagem) {
        super(mensagem);
    }
}
