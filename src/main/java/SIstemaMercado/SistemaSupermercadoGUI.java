package SIstemaMercado;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SistemaSupermercadoGUI extends Application {
    private CaixaImpl caixa;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Supermercado");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label labelProduto = new Label("Produto:");
        GridPane.setConstraints(labelProduto, 0, 0);

        TextField inputProduto = new TextField();
        inputProduto.setPromptText("Nome do produto");
        GridPane.setConstraints(inputProduto, 1, 0);

        Label labelCliente = new Label("Cliente:");
        GridPane.setConstraints(labelCliente, 0, 1);

        TextField inputCliente = new TextField();
        inputCliente.setPromptText("Nome do cliente");
        GridPane.setConstraints(inputCliente, 1, 1);

        Button btnAdicionarProduto = new Button("Adicionar Produto");
        GridPane.setConstraints(btnAdicionarProduto, 2, 0);

        Button btnRemoverProduto = new Button("Remover Produto");
        GridPane.setConstraints(btnRemoverProduto, 3, 0);

        Button btnAdicionarCliente = new Button("Adicionar Cliente");
        GridPane.setConstraints(btnAdicionarCliente, 2, 1);

        Button btnRemoverCliente = new Button("Remover Cliente");
        GridPane.setConstraints(btnRemoverCliente, 3, 1);

        Button btnFinalizarCompra = new Button("Finalizar Compra");
        GridPane.setConstraints(btnFinalizarCompra, 1, 2);

        grid.getChildren().addAll(labelProduto, inputProduto, labelCliente, inputCliente,
                btnAdicionarProduto, btnRemoverProduto, btnAdicionarCliente, btnRemoverCliente,
                btnFinalizarCompra);

        Scene scene = new Scene(grid, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
