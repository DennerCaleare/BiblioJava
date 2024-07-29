package ui;

import dao.FileUtil;
import model.Autor;
import model.Livro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe para testar a persistência de dados com arquivos locais.
 */
public class TesteFileUtil {
    public static void main(String[] args) {
        try {
            // Criar lista de livros para teste
            List<Livro> livros = new ArrayList<>();
            Autor autor1 = new Autor("Machado de Assis", new Date(), "Brasileiro");
            Livro livro1 = new Livro("Dom Casmurro", autor1, 1902, "Português", 0);
            livros.add(livro1);

            // Salvar livros em arquivo
            FileUtil.salvarLivros(livros);

            // Carregar livros de arquivo
            List<Livro> livrosCarregados = FileUtil.carregarLivros();
            for (Livro livro : livrosCarregados) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor().getNome());
                System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
                System.out.println("Idioma: " + livro.getIdioma());
                System.out.println("------");
            }

            // Criar lista de autores para teste
            List<Autor> autores = new ArrayList<>();
            Autor autor2 = new Autor("Jorge Amado", new Date(), "Brasileiro");
            autores.add(autor2);

            // Salvar autores em arquivo
            FileUtil.salvarAutores(autores);

            // Carregar autores de arquivo
            List<Autor> autoresCarregados = FileUtil.carregarAutores();
            for (Autor autor : autoresCarregados) {
                System.out.println("Nome: " + autor.getNome());
                System.out.println("Nacionalidade: " + autor.getNacionalidade());
                System.out.println("------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
