package SIstemaMercado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SistemaSupermercadoGUI extends JFrame {
    private CaixaImpl caixa;

    private JTextField inputProduto;
    private JTextField inputCliente;
    private JComboBox<Categoria> comboBoxCategorias;

    public SistemaSupermercadoGUI() {
        super("Sistema de Supermercado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel labelProduto = new JLabel("Produto:");
        panel.add(labelProduto);

        inputProduto = new JTextField();
        panel.add(inputProduto);

        JLabel labelCliente = new JLabel("Cliente:");
        panel.add(labelCliente);

        inputCliente = new JTextField();
        panel.add(inputCliente);

        JLabel labelCategoria = new JLabel("Categoria:");
        panel.add(labelCategoria);

        comboBoxCategorias = new JComboBox<>(Categoria.values());
        panel.add(comboBoxCategorias);

        JButton btnAdicionarProduto = new JButton("Adicionar Produto");
        btnAdicionarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implemente a lógica para adicionar o produto
            }
        });
        panel.add(btnAdicionarProduto);

        JButton btnFinalizarCompra = new JButton("Finalizar Compra");
        btnFinalizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implemente a lógica para finalizar a compra
            }
        });
        panel.add(btnFinalizarCompra);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SistemaSupermercadoGUI();
            }
        });
    }
}
