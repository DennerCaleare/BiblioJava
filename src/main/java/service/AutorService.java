package service;

import dao.FileUtil;
import dao.GutendexAPI;
import model.Autor;
import model.Livro;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe de serviço para manipulação de autores.
 */
public class AutorService {

    private GutendexAPI gutendexAPI;

    public AutorService() {
        this.gutendexAPI = new GutendexAPI();
    }

    /**
     * Lista todos os autores registrados.
     *
     * @return uma lista de todos os autores registrados
     * @throws IOException se ocorrer um erro ao carregar os autores
     */
    public List<Autor> listarTodosOsAutores() throws IOException {
        return FileUtil.carregarAutores();
    }

    /**
     * Lista autores vivos em um ano específico.
     *
     * @param anoInicial o ano inicial para filtrar os autores
     * @param anoFinal o ano final para filtrar os autores
     * @return uma lista de autores vivos no ano especificado
     * @throws IOException se ocorrer um erro ao carregar os autores
     */
    public List<Autor> listarAutoresVivosEmAno(int anoInicial, int anoFinal) throws IOException {
        List<Autor> autores = listarTodosOsAutores();
        return autores.stream()
                .filter(autor -> autor.getDataNascimento().getYear() + 1900 <= anoFinal && autor.getDataNascimento().getYear() + 1900 >= anoInicial)
                .collect(Collectors.toList());
    }

    /**
     * Busca e registra um autor pelo nome utilizando a API Gutendex.
     *
     * @param nomeAutor o nome do autor a ser buscado
     * @throws Exception se ocorrer um erro na requisição HTTP
     */
    public void buscarERegistrarAutor(String nomeAutor) throws Exception {
        List<Livro> livros = gutendexAPI.buscarLivrosPorTitulo(nomeAutor);
        if (!livros.isEmpty()) {
            Autor autor = livros.get(0).getAutor();
            List<Autor> autores = listarTodosOsAutores();
            if (autores.stream().noneMatch(a -> a.getNome().equals(autor.getNome()))) {
                autores.add(autor);
                FileUtil.salvarAutores(autores);
            }
        }
    }

    /**
     * Busca detalhes de um autor pelo nome utilizando a API Gutendex.
     *
     * @param nomeAutor o nome do autor a ser buscado
     * @return o autor detalhado ou null se não encontrado
     * @throws Exception se ocorrer um erro na requisição HTTP
     */
    public Autor buscarAutorDetalhado(String nomeAutor) throws Exception {
        List<Livro> livros = gutendexAPI.buscarLivrosPorTitulo(nomeAutor);
        return livros.isEmpty() ? null : livros.get(0).getAutor();
    }
}
