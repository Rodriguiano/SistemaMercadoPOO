package SistemaMercado;

import java.io.*;
import java.util.*;

public class Gravador {

    public void salvarEstoque(Map<Integer, Produto> estoque, String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Map.Entry<Integer, Produto> entry : estoque.entrySet()) {
                Produto produto = entry.getValue();
                writer.write(produto.getCodigo() + "," + produto.getNome() + "," + produto.getPreco() + "," + produto.getCategoria() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Produto> carregarEstoque(String nomeArquivo) {
        Map<Integer, Produto> estoque = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String nome = parts[1];
                    double preco = Double.parseDouble(parts[2]);
                    Categoria categoria = Categoria.valueOf(parts[3]);
                    Produto produto = new Produto(id, nome, preco, categoria);
                    estoque.put(id, produto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return estoque;
    }

    public void salvarClientes(List<Cliente> clientes, String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Cliente cliente : clientes) {
                writer.write(cliente.getNome() + "," + cliente.getEndereco() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> carregarClientes(String nomeArquivo) {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String nome = parts[0];
                    String endereco = parts[1];
                    Cliente cliente = new Cliente(nome, endereco);
                    clientes.add(cliente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
