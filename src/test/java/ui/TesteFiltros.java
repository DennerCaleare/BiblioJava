package ui;

import dao.FileUtil;
import model.Autor;
import model.Livro;
import service.AutorService;
import service.LivroService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe para testar os filtros e consultas específicas.
 */
public class TesteFiltros {
    public static void main(String[] args) {
        LivroService livroService = new LivroService();
        AutorService autorService = new AutorService();

        try {
            // Adicionar e salvar dados de teste
            adicionarDadosDeTeste();

            // Listar livros por idioma
            List<Livro> livrosPorIdioma = livroService.listarLivrosPorIdioma("Português");
            System.out.println("Livros em Português:");
            for (Livro livro : livrosPorIdioma) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor().getNome());
                System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
                System.out.println("Idioma: " + livro.getIdioma());
                System.out.println("------");
            }

            // Listar autores vivos em um determinado ano
            List<Autor> autoresVivosEmAno = autorService.listarAutoresVivosEmAno(1900);
            System.out.println("Autores vivos em 1900:");
            for (Autor autor : autoresVivosEmAno) {
                System.out.println("Nome: " + autor.getNome());
                System.out.println("Nacionalidade: " + autor.getNacionalidade());
                System.out.println("------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adiciona dados de teste ao sistema.
     *
     * @throws IOException se ocorrer um erro ao salvar os dados
     */
    private static void adicionarDadosDeTeste() throws IOException {
        // Criar lista de autores
        List<Autor> autores = new ArrayList<>();
        autores.add(new Autor("Machado de Assis", new Date(1839 - 1900, 6, 21), "Brasileiro"));
        autores.add(new Autor("Jorge Amado", new Date(1912 - 1900, 8, 10), "Brasileiro"));
        autores.add(new Autor("Clarice Lispector", new Date(1920 - 1900, 12, 10), "Brasileira"));
        autores.add(new Autor("José Saramago", new Date(1922 - 1900, 11, 16), "Português"));
        autores.add(new Autor("Fernando Pessoa", new Date(1888 - 1900, 6, 13), "Português"));

        // Salvar autores em arquivo
        FileUtil.salvarAutores(autores);

        // Criar lista de livros
        List<Livro> livros = new ArrayList<>();
        livros.add(new Livro("Dom Casmurro", autores.get(0), 1899, "Português", 0));
        livros.add(new Livro("Gabriela, Cravo e Canela", autores.get(1), 1958, "Português", 0));
        livros.add(new Livro("A Hora da Estrela", autores.get(2), 1977, "Português", 0));
        livros.add(new Livro("Ensaio Sobre a Cegueira", autores.get(3), 1995, "Português", 0));
        livros.add(new Livro("Mensagem", autores.get(4), 1934, "Português", 0));
        livros.add(new Livro("Memórias Póstumas de Brás Cubas", autores.get(0), 1881, "Português", 0));
        livros.add(new Livro("Capitães da Areia", autores.get(1), 1937, "Português", 0));
        livros.add(new Livro("A Paixão Segundo G.H.", autores.get(2), 1964, "Português", 0));
        livros.add(new Livro("O Evangelho Segundo Jesus Cristo", autores.get(3), 1991, "Português", 0));
        livros.add(new Livro("Livro do Desassossego", autores.get(4), 1982, "Português", 0));

        // Salvar livros em arquivo
        FileUtil.salvarLivros(livros);
    }
}
