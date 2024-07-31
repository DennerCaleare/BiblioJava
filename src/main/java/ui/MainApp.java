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
        Button btnTop10Livros = new Button("Top 10 Livros Mais Baixados");
        TextField txtBuscarTitulo = new TextField();
        txtBuscarTitulo.setPromptText("Buscar Livro pelo Título");
        Button btnBuscarLivro = new Button("Buscar Livro");
        ComboBox<String> comboBuscarIdioma = new ComboBox<>();
        comboBuscarIdioma.getItems().addAll("Inglês", "Português", "Espanhol");
        comboBuscarIdioma.setPromptText("Buscar Livros por Idioma");
        Button btnBuscarPorIdioma = new Button("Buscar por Idioma");
        TextField txtAnoInicial = new TextField();
        txtAnoInicial.setPromptText("Ano Inicial");
        TextField txtAnoFinal = new TextField();
        txtAnoFinal.setPromptText("Ano Final");
        Button btnBuscarPorAno = new Button("Buscar por Intervalo de Anos");
        TextField txtDetalhesLivro = new TextField();
        txtDetalhesLivro.setPromptText("Título do Livro para Detalhes");
        Button btnDetalhesLivro = new Button("Ver Detalhes do Livro");
        TextField txtDetalhesAutor = new TextField();
        txtDetalhesAutor.setPromptText("Nome do Autor para Detalhes");
        Button btnDetalhesAutor = new Button("Ver Detalhes do Autor");
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

        btnTop10Livros.setOnAction(event -> {
            try {
                List<Livro> livros = livroService.listarTop10LivrosMaisBaixados();
                listView.getItems().clear();
                for (Livro livro : livros) {
                    listView.getItems().add(livro.getTitulo() + " - " + livro.getAutor().getNome());
                }
            } catch (Exception e) {
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
            String idioma = comboBuscarIdioma.getValue();
            if (idioma == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecione um idioma para buscar.", ButtonType.OK);
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

        btnBuscarPorAno.setOnAction(event -> {
            String anoInicialStr = txtAnoInicial.getText();
            String anoFinalStr = txtAnoFinal.getText();
            if (anoInicialStr.isEmpty() || anoFinalStr.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, insira ambos os anos para buscar.", ButtonType.OK);
                alert.showAndWait();
            } else {
                try {
                    int anoInicial = Integer.parseInt(anoInicialStr);
                    int anoFinal = Integer.parseInt(anoFinalStr);
                    List<Autor> autores = autorService.listarAutoresVivosEmAno(anoInicial, anoFinal);
                    listView.getItems().clear();
                    for (Autor autor : autores) {
                        listView.getItems().add(autor.getNome() + " - " + autor.getNacionalidade());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Ano inválido. Por favor, insira números inteiros.", ButtonType.OK);
                    alert.showAndWait();
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

        btnDetalhesLivro.setOnAction(event -> {
            String titulo = txtDetalhesLivro.getText();
            if (titulo.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, insira um título para buscar detalhes.", ButtonType.OK);
                alert.showAndWait();
            } else {
                try {
                    Livro livro = livroService.buscarLivroDetalhado(titulo);
                    if (livro != null) {
                        listView.getItems().clear();
                        listView.getItems().add("Título: " + livro.getTitulo());
                        listView.getItems().add("Autor: " + livro.getAutor().getNome());
                        listView.getItems().add("Ano de Publicação: " + livro.getAnoPublicacao());
                        listView.getItems().add("Idioma: " + livro.getIdioma());
                        listView.getItems().add("Downloads: " + livro.getDownloads());
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Livro não encontrado.", ButtonType.OK);
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnDetalhesAutor.setOnAction(event -> {
            String nomeAutor = txtDetalhesAutor.getText();
            if (nomeAutor.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, insira um nome de autor para buscar detalhes.", ButtonType.OK);
                alert.showAndWait();
            } else {
                try {
                    Autor autor = autorService.buscarAutorDetalhado(nomeAutor);
                    if (autor != null) {
                        listView.getItems().clear();
                        listView.getItems().add("Nome: " + autor.getNome());
                        listView.getItems().add("Data de Nascimento: " + autor.getDataNascimento());
                        listView.getItems().add("Nacionalidade: " + autor.getNacionalidade());
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Autor não encontrado.", ButtonType.OK);
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vbox = new VBox(
                btnListarLivros,
                btnListarAutores,
                btnTop10Livros,
                txtBuscarTitulo,
                btnBuscarLivro,
                comboBuscarIdioma,
                btnBuscarPorIdioma,
                txtAnoInicial,
                txtAnoFinal,
                btnBuscarPorAno,
                txtDetalhesLivro,
                btnDetalhesLivro,
                txtDetalhesAutor,
                btnDetalhesAutor,
                btnMediaDownloadsPorAutor,
                listView
        );
        Scene scene = new Scene(vbox, 400, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
