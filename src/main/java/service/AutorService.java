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
     * Lista autores que estavam vivos em um determinado ano.
     *
     * @param ano o ano para verificar os autores vivos
     * @return uma lista de autores que estavam vivos no ano especificado
     * @throws IOException se ocorrer um erro ao carregar os autores
     */
    public List<Autor> listarAutoresVivosEmAno(int ano) throws IOException {
        List<Autor> autores = listarTodosOsAutores();
        return autores.stream()
                .filter(autor -> autor.getDataNascimento() != null && autor.getDataNascimento().getYear() <= ano)
                .collect(Collectors.toList());
    }

    /**
     * Busca informações sobre um autor e registra suas obras no sistema.
     *
     * @param nomeAutor o nome do autor a ser buscado
     * @throws Exception se ocorrer um erro na busca ou no registro
     */
    public void buscarERegistrarAutor(String nomeAutor) throws Exception {
        List<Livro> livrosDoAutor = gutendexAPI.buscarLivrosPorTitulo(nomeAutor);
        List<Autor> autores = listarTodosOsAutores();
        boolean autorExistente = autores.stream().anyMatch(autor -> autor.getNome().equalsIgnoreCase(nomeAutor));

        if (!autorExistente && !livrosDoAutor.isEmpty()) {
            Autor novoAutor = livrosDoAutor.get(0).getAutor();
            autores.add(novoAutor);
            FileUtil.salvarAutores(autores);
        }

        // Adicionar livros do autor ao sistema
        List<Livro> livrosExistentes = FileUtil.carregarLivros();
        for (Livro livro : livrosDoAutor) {
            if (livrosExistentes.stream().noneMatch(l -> l.getTitulo().equalsIgnoreCase(livro.getTitulo()))) {
                livrosExistentes.add(livro);
            }
        }
        FileUtil.salvarLivros(livrosExistentes);
    }
}
