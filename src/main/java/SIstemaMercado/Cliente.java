package SIstemaMercado;

public class Cliente {
    private String nome;
    private int enderecoID;
    private Carrinho carrinho;

    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.enderecoID = enderecoID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEnderecoID() {
        return enderecoID;
    }

    public void setEnderecoID(int enderecoID) {
        this.enderecoID = enderecoID;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }
}