package ui;

import dao.GutendexAPI;
import model.Livro;

import java.util.List;

/**
 * Classe para testar a integração com a API Gutendex.
 */
public class TesteGutendexAPI {
    public static void main(String[] args) {
        GutendexAPI api = new GutendexAPI();
        try {
            List<Livro> livros = api.buscarLivrosPorTitulo("Banana");
            for (Livro livro : livros) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor().getNome());
                System.out.println("Idioma: " + livro.getIdioma());
                System.out.println("Downloads: " + livro.getDownloads());
                System.out.println("------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
