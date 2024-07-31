package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Autor;
import model.Livro;
import service.AutorService;
import service.LivroService;

import java.io.IOException;
import java.util.List;

/**
 * Classe principal da aplicação JavaFX.
 */
public class MainApp extends Application {

    private LivroService livroService = new LivroService();
    private AutorService autorService = new AutorService();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BiblioJava");

        ListView<String> listView = new ListView<>();
        Button btnListarLivros = new Button("Listar Livros");
        Button btnListarAutores = new Button("Listar Autores");
        TextField txtBuscarTitulo = new TextField();
        txtBuscarTitulo.setPromptText("Buscar Livro pelo Título");
        Button btnBuscarLivro = new Button("Buscar Livro");

        btnListarLivros.setOnAction(event -> {
            try {
                List<Livro> livros = livroService.listarTodosOsLivros();
                listView.getItems().clear();
                for (Livro livro : livros) {
                    listView.getItems().add(livro.getTitulo() + " - " + livro.getAutor().getNome());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnListarAutores.setOnAction(event -> {
            try {
                List<Autor> autores = autorService.listarTodosOsAutores();
                listView.getItems().clear();
                for (Autor autor : autores) {
                    listView.getItems().add(autor.getNome() + " - " + autor.getNacionalidade());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnBuscarLivro.setOnAction(event -> {
            String titulo = txtBuscarTitulo.getText();
            if (titulo.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, insira um título para buscar.", ButtonType.OK);
                alert.showAndWait();
            } else {
                try {
                    List<Livro> livros = livroService.buscarLivrosPorTitulo(titulo);
                    listView.getItems().clear();
                    for (Livro livro : livros) {
                        listView.getItems().add(livro.getTitulo() + " - " + livro.getAutor().getNome());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vbox = new VBox(btnListarLivros, btnListarAutores, txtBuscarTitulo, btnBuscarLivro, listView);
        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
