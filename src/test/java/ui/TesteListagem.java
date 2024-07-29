package ui;

import model.Autor;
import model.Livro;
import service.AutorService;
import service.LivroService;

import java.io.IOException;
import java.util.List;

/**
 * Classe para testar a listagem de livros e autores.
 */
public class TesteListagem {
    public static void main(String[] args) {
        LivroService livroService = new LivroService();
        AutorService autorService = new AutorService();

        try {
            // Listar todos os livros
            List<Livro> livros = livroService.listarTodosOsLivros();
            System.out.println("Lista de Livros:");
            for (Livro livro : livros) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor().getNome());
                System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
                System.out.println("Idioma: " + livro.getIdioma());
                System.out.println("------");
            }

            // Listar todos os autores
            List<Autor> autores = autorService.listarTodosOsAutores();
            System.out.println("Lista de Autores:");
            for (Autor autor : autores) {
                System.out.println("Nome: " + autor.getNome());
                System.out.println("Nacionalidade: " + autor.getNacionalidade());
                System.out.println("------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
