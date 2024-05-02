package SistemaMercado;

import java.util.ArrayList;
import java.util.List;

public class Carrinho   {
    private List<Produto> produtos = new ArrayList<>();

        public double calcularTotal() {
            double total = 0.0;
            for (Produto p : produtos) {
                total += p.getPreco();
            }
            return total;
    }
}
