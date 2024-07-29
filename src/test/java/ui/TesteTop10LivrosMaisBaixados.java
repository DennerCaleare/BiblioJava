package ui;

import dao.GutendexAPI;
import model.Livro;

import java.util.List;

/**
 * Classe para testar a listagem dos top 10 livros mais baixados.
 */
public class TesteTop10LivrosMaisBaixados {
    public static void main(String[] args) {
        GutendexAPI api = new GutendexAPI();
        try {
            List<Livro> top10Livros = api.listarTop10LivrosMaisBaixados();
            System.out.println("Top 10 Livros Mais Baixados:");
            for (Livro livro : top10Livros) {
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
