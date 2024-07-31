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
import java.util.Map;

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
        TextField txtBuscarIdioma = new TextField();
        txtBuscarIdioma.setPromptText("Buscar Livros por Idioma");
        Button btnBuscarPorIdioma = new Button("Buscar por Idioma");
        Button btnMediaDownloadsPorAutor = new Button("Média Downloads por Autor");

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

        btnBuscarPorIdioma.setOnAction(event -> {
            String idioma = txtBuscarIdioma.getText();
            if (idioma.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, insira um idioma para buscar.", ButtonType.OK);
                alert.showAndWait();
            } else {
                try {
                    List<Livro> livros = livroService.listarLivrosPorIdioma(idioma);
                    listView.getItems().clear();
                    for (Livro livro : livros) {
                        listView.getItems().add(livro.getTitulo() + " - " + livro.getAutor().getNome());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnMediaDownloadsPorAutor.setOnAction(event -> {
            try {
                Map<Autor, Double> mediaDownloadsPorAutor = livroService.calcularMediaDownloadsPorAutor();
                listView.getItems().clear();
                for (Map.Entry<Autor, Double> entry : mediaDownloadsPorAutor.entrySet()) {
                    Autor autor = entry.getKey();
                    Double mediaDownloads = entry.getValue();
                    listView.getItems().add(autor.getNome() + " - Média de Downloads: " + mediaDownloads);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        VBox vbox = new VBox(
                btnListarLivros,
                btnListarAutores,
                txtBuscarTitulo,
                btnBuscarLivro,
                txtBuscarIdioma,
                btnBuscarPorIdioma,
                btnMediaDownloadsPorAutor,
                listView
        );
        Scene scene = new Scene(vbox, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
