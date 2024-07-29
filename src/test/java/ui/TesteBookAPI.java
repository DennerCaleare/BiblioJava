package ui;

import dao.BookAPI;
import model.Livro;

import java.util.List;

/**
 * Classe para testar a integração com a API Open Library.
 */
public class TesteBookAPI {
    public static void main(String[] args) {
        BookAPI api = new BookAPI();
        try {
            List<Livro> livros = api.buscarLivrosPorTitulo("Dom Casmurro");
            for (Livro livro : livros) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor().getNome());
                System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
                System.out.println("Idioma: " + livro.getIdioma());
                System.out.println("------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
