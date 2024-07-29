package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Livro;
import service.LivroService;

import java.io.IOException;
import java.util.List;

/**
 * Classe principal da aplicação JavaFX.
 */
public class MainApp extends Application {

    private LivroService livroService = new LivroService();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BiblioJava");

        ListView<String> listView = new ListView<>();
        Button btnListarLivros = new Button("Listar Livros");

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

        VBox vbox = new VBox(btnListarLivros, listView);
        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
