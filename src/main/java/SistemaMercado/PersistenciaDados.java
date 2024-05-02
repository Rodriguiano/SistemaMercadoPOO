package SistemaMercado;

import java.io.*;
import java.util.Map;

public class PersistenciaDados {

    public void salvarEstoque(Map<Integer, Produto> estoque, String nomeArquivo) {
        try (FileOutputStream fos = new FileOutputStream(nomeArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(estoque);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Produto> carregarEstoque(String nomeArquivo) {
        Map<Integer, Produto> estoque = null;
        try (FileInputStream fis = new FileInputStream(nomeArquivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            estoque = (Map<Integer, Produto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return estoque;
    }
}
